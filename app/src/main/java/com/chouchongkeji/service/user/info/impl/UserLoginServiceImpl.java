package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.gift.item.ItemOrderDetailMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.gift.virtualItem.AppMessage;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.info.UserService;
import com.chouchongkeji.service.v3.MemberCardService;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.user.friend.FriendService;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.service.wxapi.WXResult;
import com.chouchongkeji.util.Constants;
import com.yichen.auth.properties.SecurityProperties;
import com.yichen.auth.service.ThirdAccService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Autowired
    private ThirdAccService thirdAccService;

    @Autowired
    private FireworksService fireworksService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private BpService bpService;

    @Autowired
    private ItemOrderDetailMapper itemOrderDetailMapper;

    @Autowired
    private MemberCardService memberCardService;

    /**
     * 微信授权登录
     *
     * @param client 1-android,2-ios,3-小程序
     * @param code   微信code
     * @return
     */
    @Override
    public Response wxLogin(Integer client, String code) {
        //换取openId
        WXResult result = WXCodeApi.getSession(client, code);
        // 如果换取openid失败
        if (result.getErrcode() != 0) {
            return ResponseFactory.errMsg(result.getErrcode(), result.getErrmsg());
        }
        //换取openId后登录
        Response response = WXCodeApi.login(result.getOpenid(),
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(),
                client == 3 ? 2 : 1);
        // 登录成功!
        if (!response.isSuccessful()) {
            String key = UUID.randomUUID().toString();
            mRedisTemplate.setString(key, result.getOpenid(), 300);
            if (client != 3) {
                mRedisTemplate.setString(result.getOpenid(), result.getAccess_token(), 600);
            }
            if (client == 1) {
                Map<String, String> map = new HashMap<>();
                map.put("key", key);
                return ResponseFactory.errData(response.getErrCode(), response.getMsg(), map);
            } else {
                return ResponseFactory.errData(response.getErrCode(), response.getMsg(), key);
            }

        }
        return response;
    }

    /**
     * 绑定手机号
     *
     * @param phone  手机号
     * @param openid openid
     * @param client 客户端id
     * @return
     */
    @Override
    public Response bindPhone(String phone, String openid, Integer client, Integer userId, AppUser user) {
        int id = bindPhone1(phone, openid, client, userId);
        if (id != 0) {
            // 修改用户信息(被邀请者)
            if (user != null) {
                userService.updateProfile(id, user);
            }
            // 添加好友
            if (userId != null) {
                friendService.WXAddFriend(id, userId);
            }
        }
        // 绑定成功后登录
        Response response = WXCodeApi.login(openid,
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(), client == 3 ? 2 : 1);

        return response;
    }


    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
    public int bindPhone1(String phone, String openid, Integer client, Integer userId) {
        // 绑定手机号
        Response response = thirdAccService.addOpenAccDetail(openid, client == 3 ? 2 : 1, phone);
        if (!response.isSuccessful()) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), response.getMsg());
        }
        //好友用户id(被邀请者)
        Integer id = (Integer) response.getData();
        if (id == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "用户id获取失败");
        }
        if (userId != null) {
            // 添加邀请好友记录
            Integer inviteId = fireworksService.addInviteUser(id, userId);
            if (inviteId == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "不能邀请自己或添加邀请记录失败");
            }
            // 邀请者获取礼花、添加使用记录
            AppUser user = appUserMapper.selectByPrimaryKey(id);
            int integer1 = fireworksService.updateFireworks(userId, 10, Constants.FIREWORKS_RECORD.ADDFRIEND, "邀请好友:" + user.getNickname(), inviteId);
            if (integer1 != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "邀请者获得礼花失败");
            }
        }
        // 添加礼遇圈会员卡
        memberCardService.addMemberShipCard(id,new BigDecimal("0"),new BigDecimal("0"),new BigDecimal("0"));
        // 测试账号（添加测试商品到背包）
        if ("13110487948".equals(phone)) {
            //物品添加到背包
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByUserIdAndOrderNo(7, 1219091010108L);
            if (CollectionUtils.isNotEmpty(list)) {
                for (ItemOrderDetail itemOrderDetail : list) {
                    itemOrderDetail.setUserId(id);
                }
                bpService.addFromItemOrder(list);
            }
        }
        return id;
    }
}

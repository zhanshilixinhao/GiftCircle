package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.InviteUserMapper;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.InviteUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.service.iwant.wallet.FireworksService;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.service.wxapi.WXResult;
import com.yichen.auth.properties.SecurityProperties;
import com.yichen.auth.service.ThirdAccService;
import org.apache.tomcat.jni.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author linqin
 * @date 2018/6/5
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
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
            if (client != 3){
                mRedisTemplate.setString(result.getOpenid(), result.getAccess_token(), 600);
            }
            if (client == 1) {
                Map<String, String> map = new HashMap<>();
                map.put("key", key);
                return ResponseFactory.errData(response.getErrCode(), response.getMsg(), map);
            } else
            return ResponseFactory.errData(response.getErrCode(), response.getMsg(), key);
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
    public Response bindPhone(String phone, String openid, Integer client,Integer userId) {
        // 绑定手机号
        Response response = thirdAccService.addOpenAccDetail(openid, client == 3 ? 2 : 1, phone);
        if (!response.isSuccessful()) {
            return response;
        }
        if(userId != null){
            Integer id = (Integer) response.getData();//好友用户id
            if (id == null){
                throw new ServiceException(ErrorCode.ERROR.getCode(),"用户id获取失败");
            }
            // 添加邀请好友记录
            Integer inviteId = fireworksService.addInviteUser(id, userId);
            if (inviteId == null) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"添加邀请记录失败");
            }
            // 邀请者获取礼花
            int integer1 = fireworksService.addFirework(userId, 10);
            if (integer1 != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"邀请者获得礼花失败");
            }
            //添加礼花收益记录
            AppUser user = appUserMapper.selectByPrimaryKey(id);
            int integer2 = fireworksService.addFireworkRecord(userId, 10, "邀请好友" + user.getNickname() + "奖励", inviteId, (byte) 1);
            if (integer2 != 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(),"添加礼花记录失败");
            }

        }
        // 绑定成功后登录
        response = WXCodeApi.login(openid,
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(), client == 3 ? 2 : 1);

        return response;
    }
}

package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.user.InviteUserMapper;
import com.chouchongkeji.dial.pojo.user.InviteUser;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.redis.MRedisTemplate;
import com.chouchongkeji.service.user.info.UserLoginService;
import com.chouchongkeji.service.wxapi.WXCodeApi;
import com.chouchongkeji.service.wxapi.WXResult;
import com.yichen.auth.properties.SecurityProperties;
import com.yichen.auth.service.ThirdAccService;
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
    private InviteUserMapper inviteUserMapper;

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
        // 添加邀请好友记录
        if(userId != null){
            Integer id = (Integer) response.getData();
            InviteUser inviteUser = new InviteUser();
            inviteUser.setUserId(id);
            inviteUser.setParentUserId(userId);
            int insert = inviteUserMapper.insert(inviteUser);
            if(insert < 1){
                throw new ServiceException(ErrorCode.ERROR.getCode(), "添加邀请好友记录失败");
            }
        }
        // 绑定成功后登录
        response = WXCodeApi.login(openid,
                securityProperties.getOauth2().getClient()[0].getClientId(),
                securityProperties.getOauth2().getClient()[0].getClientSecret(), client == 3 ? 2 : 1);

        return response;
    }
}

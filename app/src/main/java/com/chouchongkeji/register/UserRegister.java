package com.chouchongkeji.register;

import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

/**
 * @author linqin
 * @date 2019/6/21
 */
public class UserRegister {

    final static String appKey = "82hegw5u8xwsx";
    final static String appSecret = "JbVpHZmR2cS";

    final static RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
    public final static User user = rongCloud.user;



    public static TokenResult register(Integer userId, String nickname,String avatar) throws Exception {
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel userModel = new UserModel()
                .setId(userId.toString())
                .setName(nickname)
                .setPortrait(avatar);

        TokenResult result = user.register(userModel);
        return result;
    }


}

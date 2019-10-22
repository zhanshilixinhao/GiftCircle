package com.chouchongkeji.service.wxapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.ResponseImpl;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpManager;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.yichen.auth.authentication.MyToken;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author linqin
 * @date 2018/6/5
 */
public class WXCodeApi {
    private final static Logger log = LoggerFactory.getLogger(WXCodeApi.class);

    public static final String URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String URL_M = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String LOGIN_URL = "http://localhost:8088/login/third";

    // 小程序
    private static final String WX_APPID = "wx3e38146df77ddbd9"; // wx4e47fc336f8578df
    private static final String WX_APP_SECRET = "2e45e7696cfa2eeb7dcf8900c63163e9";

    // app
    private static final String APPID =      "wxe54fd2867936a895";
    private static final String APP_SECRET = "01426797b62a5c00d86a0a8928fd9a0c";

    public static final String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";


    /**
     * 微信code换取openid
     *
     * @param code 微信code
     * @return
     * @author yichenshanren
     * @date 2018/2/6
     */
    public static WXResult getSession(Integer clientApp, String code) {
        String codeKey;
        String url;

        String appId = "";
        String secret = "";
        if (clientApp != 3) {
            codeKey = "code";
            url = URL;
            appId = APPID;
            secret = APP_SECRET;
        } else {
            codeKey = "js_code";
            url = URL_M;
            appId = WX_APPID;
            secret = WX_APP_SECRET;
        }
        OkHttpClient client = OkHttpManager.create(null, null);
        RequestParams params = new RequestParams();
        params.put("appid", appId);
        params.put("secret", secret);
        params.put(codeKey, code);
        params.put("grant_type", "authorization_code");
        WXResult result = new WXResult();
        try {
            Response response = OkHttpUtil.get(client, url, params);
            String re = response.body().string();
            log.info("微信换取openid:{}", re);
            result = JSON.parseObject(re, WXResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            result.setErrcode(1);
            result.setErrmsg(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        WXResult wx80c79d8611c60ba8 = getSession(3, "wx80c79d8611c60ba8");
        System.out.println(wx80c79d8611c60ba8);
    }

    /**
     * 微信openId登录
     *
     * @param openid 微信openId
     * @author yichenshanren
     * @date 2018/2/6
     */
    public static com.chouchongkeji.goexplore.common.Response login(String openid, String appId, String appSecret, int type) {
        // 构造登录参数
        RequestParams params = new RequestParams();
        params.put("openId", openid);
        params.put("accType", type);
        params.put("app_id", appId);
        params.put("app_secret", appSecret);
        params.put("time", System.currentTimeMillis());
        String sing = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.WXMINI).get(ApiSignUtil.WXMINI);
        params.put("sign", sing);
        // 执行登录
        try {
            Response response = OkHttpUtil.post(OkHttpManager.create(null, null), LOGIN_URL, params);
            String re = response.body().string();
//            System.out.println(re);
            com.chouchongkeji.goexplore.common.Response res = JSON.parseObject(
                    re,
                    new TypeReference<ResponseImpl<MyToken>>() {
                    });
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseFactory.err(e.getMessage());
        }
    }


    /**
     * 获取微信用户的信息
     *
     * @param accessToken 微信令牌
     * @param openId      微信用户唯一标识
     * @return
     * @author yichenshanren
     * @date 2018/2/6
     */
    public static WXResult getUserInfo(String accessToken, String openId) {
        RequestParams params = new RequestParams();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        return wxRequest(URL_USER_INFO, params);
    }


    /**
     * 微信接口请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    private static WXResult wxRequest(String url, RequestParams params) {
        return wxRequest(url, params, 0);
    }

    private static final OkHttpClient CLIENT = OkHttpManager.create(null, null);


    /**
     * 微信接口请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    private static WXResult wxRequest(String url, RequestParams params, int method) {
        WXResult result = new WXResult();
        try {
            Response response;
            if (method == 0) {
                response = OkHttpUtil.get(CLIENT, url, params);
            } else {
                response = OkHttpUtil.postJson(CLIENT, url, params);
            }
            if (response.isSuccessful()) {
                String re = response.body().string();
//                log.info("微信换取openid:{}", re);
                result = JSON.parseObject(re, WXResult.class);
            } else {
                result.setErrcode(1);
                result.setErrmsg(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setErrcode(1);
            result.setErrmsg(e.getMessage());
        }
        return result;
    }


}



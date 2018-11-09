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
    public static final String LOGIN_URL = "http://localhost:8080/login/third";

    // 小程序
    private static final String WX_APPID = "wxe54fd2867936a895"; // wx4e47fc336f8578df
    private static final String WX_APP_SECRET = "2e45e7696cfa2eeb7dcf8900c63163e9";

    // app
    private static final String APPID = "wx3e38146df77ddbd9"; // wx4e47fc336f8578df
    private static final String APP_SECRET = "01426797b62a5c00d86a0a8928fd9a0c";

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

    /**
     * 微信openId登录
     *
     * @param openid 微信openId
     * @author yichenshanren
     * @date 2018/2/6
     */
    public static com.chouchongkeji.goexplore.common.Response login(String openid, String appId, String appSecret) {
        // 构造登录参数
        RequestParams params = new RequestParams();
        params.put("openId", openid);
        params.put("accType", 3);
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
}



package com.chouchongkeji.service.wxapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.ResponseImpl;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpManager;

import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.chouchongkeji.service.v4.vo.JsonVo;
import com.chouchongkeji.service.v4.vo.SuperUserVo;
import com.chouchongkeji.util.WXPayUtil;
import com.yichen.auth.authentication.MyToken;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/5
 */
public class WXCodeApi {
    private final static Logger log = LoggerFactory.getLogger(WXCodeApi.class);

    public static final String URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String URL_M = "https://api.weixin.qq.com/sns/jscode2session";
    public static final String LOGIN_URL = "http://localhost:9001/login/third";

    // 小程序
    public static final String WX_APPID = "wx3e38146df77ddbd9"; // wx4e47fc336f8578df
    public static final String WX_APP_SECRET = "2e45e7696cfa2eeb7dcf8900c63163e9";
    private static final String MCH_ID = "1515659921";
    private static final String TRADETYPE = "JSAPI";
    private static final String NOTIFY_URL = "http://lxh.ngrok2.xiaomiqiu.cn/noauth/pay/elcoupon_list/applets";
    private static final String API_KEY = "JXjV3iERsUv0HTSmLeRNpYG2s1VWqFeH";
    private static final String SIGNTYPE = "MD5";

    // app
    private static final String APPID =      "wxe54fd2867936a895";
    private static final String APP_SECRET = "01426797b62a5c00d86a0a8928fd9a0c";

    public static final String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

    /******************微信小程序支付参数*******************************************/
    /** 统一下单接口 **/
    public static String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";


    /**
     * @Description: 微信小程序预支付
     * @Author: LxH
     * @Date: 2020/10/19 11:51
     */
    public static com.chouchongkeji.goexplore.common.Response prepayment(String openId ,String body , String price,String orderNumber){
        HashMap<String, String> params = new HashMap<>();
        params.put("appid",WX_APPID);
        params.put("mch_id",MCH_ID);
        params.put("nonce_str", WXPayUtil.generateNonceStr());
        params.put("body", body);
        params.put("out_trade_no", orderNumber);
        params.put("total_fee", price);
        try {
            InetAddress localAddr = InetAddress.getLocalHost();
            String localIp = localAddr.getHostAddress();
            //终端IP
            params.put("spbill_create_ip", localIp);
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
        //异步通知回调地址
        params.put("notify_url", NOTIFY_URL);
        //支付类型
        params.put("trade_type", TRADETYPE);
        params.put("sign_type",SIGNTYPE);
        params.put("openid",openId);
        try {
            String sign = WXPayUtil.generateSignature(params,API_KEY, WXPayUtil.SignType.MD5);
            params.put("sign", sign);
            System.out.println("第一次签名"+sign);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        //将请求参数转化为微信支付要求的xml格式文件
        String xml = WXPayUtil.mapToXml1(params);
        System.out.println(xml);
        //调用对方demo请求下单
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        String responseContent = "";
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(UNIFIEDORDER);
            httpPost.setEntity(new StringEntity(xml,"UTF-8"));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            System.out.println("第一次请求"+responseContent);
            response.close();
            httpClient.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            close(response,httpClient);
        }
        Map resultMap = null;
        try {
            resultMap = WXPayUtil.xmlToMap(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println(resultMap.toString());
        //请求返回结果的处理
        String returnCode = (String) resultMap.get("return_code");
        String resultCode = (String)resultMap.get("result_code");
        if ("SUCCESS".equals(returnCode) && returnCode.equals(resultCode)){
            HashMap<String, String> map = new HashMap<>();
            map.put("appId", WX_APPID);
            map.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
            //这边的随机字符串必须是第一次生成sign时，微信返回的随机字符串，不然小程序支付时会报签名错误
            map.put("nonceStr", String.valueOf(resultMap.get("nonce_str")));
            map.put("package", "prepay_id=" + resultMap.get("prepay_id"));
            map.put("signType", SIGNTYPE);
            //String paySign = WXPayUtil.getSign(map, PayConfig.getApi_key());
            String paySign= null;
            try {
                paySign = WXPayUtil.generateSignature(map,API_KEY, WXPayUtil.SignType.MD5);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("第二次签名"+paySign);
            log.info(paySign);
            map.put("paySign",paySign);
            System.out.println(map.toString());
            return ResponseFactory.suc("用户礼包预支付成功！", map);
        }
        return ResponseFactory.err("用户礼包预支付失败！");
    }

    private static void close(Closeable... closeables) {
        if (closeables != null && closeables.length > 0) {
            try {
                for (Closeable closeable : closeables) {
                    if (closeable != null) {
                        closeable.close();
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


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

    public static com.chouchongkeji.goexplore.common.Response loginV4(String openid, String appId, String appSecret, int type,
                                                                      SuperUserVo userVo) {
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
            JsonVo jsonVo = JSON.parseObject(re, JsonVo.class);
            MyToken myToken = JSONObject.parseObject(jsonVo.getData(), MyToken.class);
            userVo.setAccessToken(myToken.getAccessToken()).
                    setRefreshToken(myToken.getRefreshToken()).
                    setExpire(myToken.getExpire());
            return ResponseFactory.sucData(userVo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseFactory.err(e.getMessage());
        }
    }
}



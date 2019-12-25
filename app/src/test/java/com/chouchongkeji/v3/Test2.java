package com.chouchongkeji.v3;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.chouchongkeji.goexplore.utils.Utils;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/12/3
 */

public class Test2 {

    // 提现记录
    @Test
    public void withdrawRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "a2ff9d62-76f0-4075-9806-dbac4d4cb931");
        params.put("cardId", 0);
        params.put("sendMoney", 0.01);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/cardSend/wx", params);
        System.out.println(post.body().string());
    }
//
    @Test
    public void judge() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "d32cb30a-db15-41b5-aef5-1a60c06682cc");
        params.put("transferSendId", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/cardSend/judge", params);
        System.out.println(post.body().string());
    }

    @Test
    public void get() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "a2ff9d62-76f0-4075-9806-dbac4d4cb931");
        params.put("transferSendId", 3);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/cardSend/get", params);
        System.out.println(post.body().string());
    }

    @Test
    public void get1() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("cardId", 0);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/cardSend/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void det() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("transferSendId", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/cardSend/detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void update() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "f79afc76-a434-4f59-830e-34b0cc674735");
        params.put("cardId", 3);
        params.put("oldPassword", Utils.toMD5("400657") );
        params.put("newPassword",Utils.toMD5("123456"));
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/update_password", params);
        System.out.println(post.body().string());
    }

    @Test
    public void find() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "f79afc76-a434-4f59-830e-34b0cc674735");
        params.put("cardId", 4);
//        params.put("oldPassword", Utils.toMD5("400657") );
        params.put("newPassword",Utils.toMD5("123456"));
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/find_password", params);
        System.out.println(post.body().string());
    }

}

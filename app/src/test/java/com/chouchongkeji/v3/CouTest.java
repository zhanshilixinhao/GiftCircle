package com.chouchongkeji.v3;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2020/2/10 14:17
 */

public class CouTest {

    // 提现记录
    @Test
    public void withdrawRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "f79afc76-a434-4f59-830e-34b0cc674735");
        params.put("pageNum", 1);
        params.put("pageSize", 14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v3/coupon/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void de() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "f79afc76-a434-4f59-830e-34b0cc674735");
        params.put("num", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v3/coupon/detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void send() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "f79afc76-a434-4f59-830e-34b0cc674735");
        params.put("num", 41220021410101L);
        params.put("quantity", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v3/coupon/send", params);
        System.out.println(post.body().string());
    }

    @Test
    public void s() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7cb1164b-43f8-4abd-b218-6fa1a2da88f8");
        params.put("couponSendId", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v3/coupon/judge", params);
        System.out.println(post.body().string());
    }
    @Test
    public void get() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7cb1164b-43f8-4abd-b218-6fa1a2da88f8");
        params.put("couponSendId", 39);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
//        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v3/memberCard/list", params);
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v3/coupon/get", params);
        System.out.println(post.body().string());
    }

}

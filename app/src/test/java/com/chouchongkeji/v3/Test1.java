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
 * @date 2018/11/28 18:53
 */

public class Test1 {

    // 提现记录
    @Test
    public void withdrawRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("pageNum", 1);
        params.put("pageSize", 14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/list", params);
        System.out.println(post.body().string());
    }

    // 提现记录
    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("id", 2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void list() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("id", 0);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/charge/record", params);
        System.out.println(post.body().string());
    }

    @Test
    public void de() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("id", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/charge/detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void ewr() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("id", 0);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/expense/record", params);
        System.out.println(post.body().string());
    }
    @Test
    public void det() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("id", 2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/memberCard/expense/detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void rule() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
//        params.put("id", 2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/noauth/v3/charge/rule/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void ca() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("eventId", 2);
        params.put("payWay", 78990);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v3/charge/order", params);
        System.out.println(post.body().string());
    }

}

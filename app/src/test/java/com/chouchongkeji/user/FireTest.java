package com.chouchongkeji.user;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/4/3
 */

public class FireTest {

    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/fireworks/detail", params);
        System.out.println(post.body().string());
    }
    @Test
    public void list() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/fireworks/user_list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void lis() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/fireworks/earn_record", params);
        System.out.println(post.body().string());
    }

    @Test
    public void num() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/fireworks/number", params);
        System.out.println(post.body().string());
    }

    @Test
    public void item() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("status",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/item/order/list", params);
        System.out.println(post.body().string());
    }

@Test
    public void add() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("depositBank","15752400650");
        params.put("cardHolder","li支付宝");
        params.put("cardNo","15752400650000");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/add", params);
        System.out.println(post.body().string());
    }

    @Test
    public void d() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("depositBank","15752400650");
        params.put("cardHolder","li支付宝");
        params.put("cardNo","15752400650000");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void l() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/set_hide", params);
        System.out.println(post.body().string());
    }


    @Test
    public void gift() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("giftRecordId",528);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/gift/getGift", params);
        System.out.println(post.body().string());
    }


}

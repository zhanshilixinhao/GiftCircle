package com.chouchongkeji.v2;

import com.chouchongkeji.goexplore.utils.*;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/6/20
 */

public class test4 {

    @Test
    public void districtList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("pid",24);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8080/noauth/item/second_category", params);
        System.out.println(post.body().string());
    }

    @Test
    public void distryrictList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "ba369cdd-77b4-446e-958e-e4bffd48169f");
        params.put("nickname","时间");
        params.put("avatar","https://wx.qlogo.cn/mmopen/vi_32/cLhvDgpVNMm24pZLQn9NJLvTbribW3ymS4dXSctqaaKWhF7NJcI1Nicqp0QGw2jjVPsCwcrIFOBKv90qbCqTxSicw/132");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8080/auth/user/ryUser", params);
        System.out.println(post.body().string());
    }



}

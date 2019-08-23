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


    @Test
    public void c() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "50e46c96-b152-49cb-81d6-0219b7e5a88a");
//        params.put("access_token", "ba369cdd-77b4-446e-958e-e4bffd48169f");
        params.put("id",133);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/memo/affair2/affair_detail", params);
        System.out.println(post.body().string());
    }
    @Test
    public void book() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "50e46c96-b152-49cb-81d6-0219b7e5a88a");
        params.put("phone","157 5240 0657,86183 1374 7954,180 8831 4253,18510454067");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/friend/book_list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void re() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
//        params.put("phone","15752400657,18313747954,18088314253,18510454067");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v2/recommend/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void fe() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
//        params.put("phone","15752400657,18313747954,18088314253,18510454067");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/festival_all", params);
        System.out.println(post.body().string());
    }

    @Test
    public void sce() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
//        params.put("phone","15752400657,18313747954,18088314253,18510454067");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/scene_all", params);
        System.out.println(post.body().string());
    }
    @Test
    public void sera() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("labelId", 0);
        params.put("festivalId",0);
        params.put("sceneId",0);
//        params.put("keywords","礼");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/search", params);
        System.out.println(post.body().string());
    }



}

package com.chouchongkeji.home;


import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/1/10 10:10
 */

public class HomeTest {

    // 文章列表
    @Test
    public void articleList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("type" ,3);
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/list", params);
        System.out.println(post.body().string());
    }
    // 文章详情
    @Test
    public void articleDetail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("id" ,1);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/detail", params);
        System.out.println(post.body().string());
    }
    // 文章详情
    @Test
    public void item() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("id" ,1);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/item_list", params);
        System.out.println(post.body().string());
    }




}

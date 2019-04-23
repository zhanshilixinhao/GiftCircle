package com.chouchongkeji.home;


import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/1/10 10:10
 */

public class HomeTest {

    @Test
    public void t(){
        String s = "公历节日:  国际妇女节 农历节日:  龙头节 太昊伏羲氏祭";
        List<String> list = new ArrayList<>();
        String[] s1 = s.replaceAll("公历节日:  ", "")
                .replaceAll("农历节日:  ", "").split(" ");

        System.out.println(s1);
    }

    // 文章列表
    @Test
    public void articleList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("type" ,4);
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
        params.put("id" ,34);
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
        params.put("id" ,8);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/item_list", params);
        System.out.println(post.body().string());
    }

    // 标签列表
    @Test
    public void label() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
//        params.put("id" ,1);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/label/list", params);
        System.out.println(post.body().string());
    }
    // 标签商品列表
    @Test
    public void itemList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("id" ,1);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/label/item_list", params);
        System.out.println(post.body().string());
    }

    // 按天查询文章
    @Test
    public void day() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("day" ,1547740800000L);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/article/article_list", params);
        System.out.println(post.body().string());
    }

    // 未查看的评论数量
    @Test
    public void count() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/moment/count", params);
        System.out.println(post.body().string());
    }



    // 节日
    @Test
    public void festival() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/home/festival", params);
        System.out.println(post.body().string());
    }

    @Test
    public void we() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/home/welfare", params);
        System.out.println(post.body().string());
    }
    @Test
    public void weD() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time" ,System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/welfare/confirm", params);
        System.out.println(post.body().string());
    }




}

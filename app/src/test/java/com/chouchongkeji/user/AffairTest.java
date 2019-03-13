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
 * @date 2019/3/12 9:50
 */

public class AffairTest {

    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("name", "聚会");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/event/add", params);
        System.out.println(post.body().string());
    }
    @Test
    public void update() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("name", "聚餐");
        params.put("eventId", "1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/event/update", params);
        System.out.println(post.body().string());
    }
    @Test
    public void del() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("eventId", "3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/event/del", params);
        System.out.println(post.body().string());
    }

    @Test
    public void list() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
//        params.put("eventId", "3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/event/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void add() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("targetTime", 1552374073000L);
        params.put("eventTypeId", "1");
        params.put("users", "1,31");
        params.put("detail", "晚会hh哈哈哈哈h");
        params.put("isCirculation", "3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/add", params);
        System.out.println(post.body().string());
    }
    @Test
    public void modify_affair() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
//        params.put("targetTime", 1552320000000L);
        params.put("eventTypeId", "1");
//        params.put("users", "1,31");
//        params.put("detail", "晚会删");
        params.put("id", "94");
        params.put("isCirculation", "5");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/modify_affair", params);
        System.out.println(post.body().string());
    }
    @Test
    public void deld() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("id", "94");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/del", params);
        System.out.println(post.body().string());
    }
    @Test
    public void l() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
//        params.put("id", "94");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void de() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("id", "1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/festival_detail", params);
        System.out.println(post.body().string());
    }
    @Test
    public void f() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("id", "1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/festival_item", params);
        System.out.println(post.body().string());
    }
    @Test
    public void g() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/friend_list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void zhex() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("bpId", 7719031310110L);
        params.put("type", 2);
        params.put("depositBank", "时良");
        params.put("cardHolder", "wo");
        params.put("cardNo", "hdjs899883");
        params.put("bankId", 8);
        params.put("phone", 8);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/withdraw/add_wx", params);
        System.out.println(post.body().string());
    }
    @Test
    public void th() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("bpId", 7719031310110L);
        params.put("address", "云南昆明");
        params.put("addressDetail", "时良");
//        params.put("code", 234);
        params.put("consigneeName", "wo");
        params.put("adcode", 530100);
        params.put("phone", 8);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/receive/item/order_wx", params);
        System.out.println(post.body().string());
    }

}
package com.chouchongkeji.user;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
//        params.put("eventTypeId", "2");
        params.put("users", "1,31");
        params.put("detail", "xiago");
//        params.put("isCirculation", "3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/add", params);
        System.out.println(post.body().string());
    }
    @Test
    public void modify_affair() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("targetTime", 1552320000000L);
        //        params.put("eventTypeId", "2");
        params.put("users", "31");
        params.put("detail", "啊哈哈哈哈坎坎坷坷扩扩扩哈哈哈哈哈");
        params.put("id", 112);
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
        params.put("id", "28");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair2/del", params);
        System.out.println(post.body().string());
    }
    @Test
    public void l() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "50e46c96-b152-49cb-81d6-0219b7e5a88a");
//        params.put("id", "94");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8089/auth/memo/affair2/list", params);
        System.out.println(post.body().string());
    }


    @Test
    public void de() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("id", "2");
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
        params.put("access_token", "50e46c96-b152-49cb-81d6-0219b7e5a88a");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8089/auth/memo/affair2/friend_list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void zhex() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("bpId", 7719031310119L);
        params.put("type", 2);
        params.put("depositBank", "高新支行");
        params.put("cardHolder", "wo");
        params.put("cardNo", "62480033440006666");
        params.put("bankId", 8);
        params.put("phone", 8);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/withdraw/add_wx", params);
        System.out.println(post.body().string());
    }


    @Test
    public void wxDiscountRecords() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/withdraw/wxRecords", params);
        System.out.println(post.body().string());
    }
    @Test
    public void m() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("detail","时良哈哈哈");
        params.put("friendUserId",1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/leaveMessage/add", params);
        System.out.println(post.body().string());
    }


    @Test
    public void th() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("bpId", 7719031310119L);
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

    @Test
    public void t() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("userId", 6);
        params.put("targetUserId",44);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/friend/wx_add", params);
        System.out.println(post.body().string());
    }

}

package com.chouchongkeji.item;

import com.chouchongkeji.goexplore.pay.alipay.AliPayException;
import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import com.mysql.fabric.xmlrpc.base.ResponseParser;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/1/29 15:08
 */

public class ItemTest {


    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("id",35);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/item_detail", params);
        System.out.println(post.body().string());
    }

 @Test
    public void favorite() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/favorite/itemList", params);
        System.out.println(post.body().string());
    }
    @Test
    public void check() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("bpId",7719013011107L);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/receive/item/check", params);
        System.out.println(post.body().string());
    }

    @Test
    public void list() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("isAll",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/group/list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void read() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("ids","42");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/message/read", params);
        System.out.println(post.body().string());
    }

    @Test
    public void search() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("keyword","花");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/search_all", params);
        System.out.println(post.body().string());
    }

    @Test
    public void searchAll() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("keyword","春");
        params.put("type",3);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/search/all_list", params);
        System.out.println(post.body().string());
    }
    @Test
    public void bpList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("type",1);
//        params.put("pageNum",2);
//        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bp/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void bpLisearcst() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("key","s");
//        params.put("pageNum",2);
//        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bp/search", params);
        System.out.println(post.body().string());
    }

    @Test
    public void ist() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId",1);
        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/tag/add", params);
        System.out.println(post.body().string());
    }

    @Test
    public void it() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId",79);
//        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/tag/userList", params);
        System.out.println(post.body().string());
    }

    @Test
    public void getGift() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("giftRecordId",641);
//        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/gift/getGift", params);
        System.out.println(post.body().string());
    }

    @Test
    public void mo() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId",1);
//        params.put("remark","shil");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/modify", params);
        System.out.println(post.body().string());
    }


    @Test
    public void j() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
//        params.put("type",1);
//        params.put("pageNum",2);
//        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bp/unread", params);
        System.out.println(post.body().string());
    }
    @Test
    public void band() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
//        params.put("type",1);
//        params.put("pageNum",1);
        params.put("clientid","cb4133150e74ff5043358da0ef39dd28");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/band", params);
        System.out.println(post.body().string());
    }


}

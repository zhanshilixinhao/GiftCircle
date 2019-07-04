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
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("id", 528);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/item_detail", params);
        System.out.println(post.body().string());
    }

    @Test
    public void favorite() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/favorite/itemList", params);
        System.out.println(post.body().string());
    }

    @Test
    public void check() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("bpId", 7719013011107L);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/receive/item/check", params);
        System.out.println(post.body().string());
    }

    @Test
    public void list() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("isAll", 2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/group/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void read() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("ids", "42");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/message/read", params);
        System.out.println(post.body().string());
    }

    @Test
    public void search() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("keyword", "花");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/search_all", params);
        System.out.println(post.body().string());
    }

    @Test
    public void searchAll() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
        params.put("keyword", "春");
        params.put("type", 3);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/search/all_list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void bpList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("type", 1);
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
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("key", "蛋糕");
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
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId", 1);
        params.put("tagIds", "2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/tag/add", params);
        System.out.println(post.body().string());
    }

    @Test
    public void it() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId", 79);
//        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/tag/userList", params);
        System.out.println(post.body().string());
    }

    @Test
    public void itf() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
//        params.put("friendUserId",79);
//        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/discount/preinfo", params);
        System.out.println(post.body().string());
    }

    @Test
    public void getGift() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("giftRecordId", 641);
//        params.put("tagIds","2,3");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/gift/getGift", params);
        System.out.println(post.body().string());
    }

    @Test
    public void mo() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("friendUserId", 1);
//        params.put("remark","shil");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/modify", params);
        System.out.println(post.body().string());
    }


    @Test
    public void j() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
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
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "faaeaefc-5bf9-471d-bd5d-85344c8f7fcd");
//        params.put("type",1);
//        params.put("pageNum",1);
        params.put("clientid", "cb4133150e74ff5043358da0ef39dd28");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/band", params);
        System.out.println(post.body().string());
    }

    @Test
    public void bandd() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("id", 8);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/theme/item", params);
        System.out.println(post.body().string());
    }

    @Test
    public void banddd() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("ids", "34,35");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/favorite/delItem", params);
        System.out.println(post.body().string());
    }

    @Test
    public void add() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("id", 6);
        params.put("amount", 1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/withdraw/add", params);
        System.out.println(post.body().string());
    }

    @Test
    public void pay() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("orderNo", 1319043017145L);
        params.put("payWay", 87661);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/virOrder/pay", params);
        System.out.println(post.body().string());
    }

    @Test
    public void we() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("bpId", 7719051711131L);
//        params.put("bpIds","[{\"bpId\":7719051711131,\"quantity\":1}]");
        params.put("greeting", "哈哈哈fdgd");
        params.put("type", 3);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app//auth/v1/gift/sendForWx", params);
        System.out.println(post.body().string());
    }

    @Test
    public void wel() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "ba369cdd-77b4-446e-958e-e4bffd48169f");
//        params.put("bpIds","[{\"bpId\":7719043018148,\"quantity\":2}]");
//        params.put("greeting","哈哈哈");
        params.put("giftRecordId", 191);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app//auth/v1/gift/getGift", params);
        System.out.println(post.body().string());
    }

    @Test
    public void weStays() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "0b83209b-c43a-44fe-9a1d-71fd71809f8a");
        params.put("giftRecordId", 210);

        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/gift/getGiftStatus", params);
        System.out.println(post.body().string());
    }
}

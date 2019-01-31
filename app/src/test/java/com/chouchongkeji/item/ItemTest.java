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
//        params.put("bpId",7719013011107L);
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


}

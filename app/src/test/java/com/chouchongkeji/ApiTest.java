package com.chouchongkeji;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/11/28 18:53
 */

public class ApiTest {

    // 提现记录
    @Test
    public void withdrawRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/withdraw/list", params);
        System.out.println(post.body().string());
    }

    // 收货地址列表
    @Test
    public void reAddress() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
//        params.put("pageNum",1);
//        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/reAddress/list", params);
        System.out.println(post.body().string());
    }
    // 系统消息列表
    @Test
    public void systemMessage() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("messageType",2);
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/message/list", params);
        System.out.println(post.body().string());
    }

    // 系统消息列表
    @Test
    public void createItemOrder() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("skus","[{ \"skuId\":468, \"quantity\":1 }  ]");
        params.put("payWay",24656);
        params.put("isShoppingCart",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/item/order/create", params);
        System.out.println(post.body().string());
    }

}

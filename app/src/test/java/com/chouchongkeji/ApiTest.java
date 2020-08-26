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
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/v1/withdraw/list", params);
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
        params.put("access_token","7a867108-2521-4d95-b482-8603ddcd4c5f");
        params.put("messageType",1);
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
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
        params.put("skus","[{ \"skuId\":1278, \"quantity\":1 },{ \"skuId\":1389, \"quantity\":1 }  ]");
        params.put("payWay",36666);
        params.put("isShoppingCart",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/item/order/create", params);
        System.out.println(post.body().string());
    }
//9219103109114
    // 系统消息列表
    @Test
    public void pay() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token", "8975fd98-d988-4110-a7a1-8fde82f8e66e");
//        params.put("skus","[{ \"skuId\":1262, \"quantity\":1 },{ \"skuId\":1268, \"quantity\":1 }  ]");
        params.put("orderNo",1219110110117L);
        params.put("payWay",98001);
        params.put("isShoppingCart",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/item/order/pay", params);
        System.out.println(post.body().string());
    }

    @Test
    public void receiveItemList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("status",0);
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/receive/item/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void updateBankCard() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("bankId",10);
        params.put("depositBank","红山");
        params.put("cardHolder","");
        params.put("cardNo","8275803664988786366");
        params.put("id",17);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/update", params);
        System.out.println(post.body().string());
    }

    @Test
    public void bankCardList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
//        params.put("bankId",10);
//        params.put("depositBank","红山");
//        params.put("cardHolder","");
//        params.put("cardNo","8275803664988786366");
//        params.put("id",17);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void getSkuStock() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
//        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
//        params.put("categoryId",7);
//        params.put("gender",2);
//        params.put("depositBank","红山");
//        params.put("cardHolder","");
//        params.put("acuraRank",2);
//        params.put("priceRank",2);
        params.put("keywords","花");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/item_list", params);
        System.out.println(post.body().string());
    }




    @Test
    public void giftReply() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("recordDetailId",13);
        params.put("reply","谢谢你的礼物hhh");
//        params.put("cardHolder","");
//        params.put("cardNo","8275803664988786366");
//        params.put("id",17);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/gift/acknowledge", params);
        System.out.println(post.body().string());
    }

    @Test
    public void itemSku() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
//        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("itemId",59);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/sku_set", params);
        System.out.println(post.body().string());
    }

    @Test
    public void sendCode() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("phone","15752400657");
        params.put("type",1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.WXMINI);
        params.put("sign",map.get(ApiSignUtil.WXMINI));
        Response post = OkHttpUtil.post("http://localhost:8088/ask/code", params);
        System.out.println(post.body().string());
    }

    @Test
    public void bind() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("phone","15752400657");
        params.put("type",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/user/bindPhone", params);
        System.out.println(post.body().string());
    }

    @Test
    public void findPwd() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("phone","15752400657");
        params.put("code","759308");
        params.put("de","hskjashfiufhb334");
        params.put("s2","fnsdfhjkshf8893");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/find/pwd", params);
        System.out.println(post.body().string());
    }

    @Test
    public void recordList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","50e46c96-b152-49cb-81d6-0219b7e5a88a");
        params.put("type",2);
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/friend/bp/record_list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void operation() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("forRecordId",79);
        params.put("operation",2);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/bp/operation", params);
        System.out.println(post.body().string());
    }
}

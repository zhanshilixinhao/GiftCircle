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
 * @date 2018/12/6 11:49
 */

public class Test2 {

    //同意拒绝好友索要
    @Test
    public void operation() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","6e4fa086-2bbe-4127-b4da-1ab86c9e07a0");
        params.put("forRecordId",19);
        params.put("operation",1);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/bp/operation", params);
        System.out.println(post.body().string());
    }

    // 向好友索要物品
  @Test
    public void forRecord() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("friendUserId",4);
        params.put("bpId","4818120614107");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/bp/add_for_record", params);
        System.out.println(post.body().string());
    }
    // 用户信息
    @Test
    public void profile() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/user/profile", params);
        System.out.println(post.body().string());
    }

    //银行卡列表
    @Test
    public void bankList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/bankCard/bankList", params);
        System.out.println(post.body().string());
    }

    //banner
    @Test
    public void banner() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/v1/home/Banner", params);
        System.out.println(post.body().string());
    }
    //搜索好友
    @Test
    public void search() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        params.put("key","积极");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/friend/search", params);
        System.out.println(post.body().string());
    }

}

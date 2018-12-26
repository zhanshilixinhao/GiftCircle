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
 * @date 2018/12/19 11:54
 */

public class test3 {
    //虚拟商品列表
    @Test
    public void virItemList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token","ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        params.put("id", 1);
        params.put("pageNum", 1);
        params.put("pageSize", 14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app/noauth/v1/virItem/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void districtList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "ca0aa669-f006-4ba8-bdf9-91a078b3ccf1");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app/noauth/district/all_list", params);
        System.out.println(post.body().string());
    }
    // 收益记录
    @Test
    public void earn() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("pageNum", 1);
        params.put("pageSize", 14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/wallet/earn_record", params);
        System.out.println(post.body().string());
    }

    // 商品详情
    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
//        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
//        params.put("pageNum", 1);
        params.put("skuId", 14);
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/noauth/item/detail", params);
        System.out.println(post.body().string());
    }

}

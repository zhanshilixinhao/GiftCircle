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
 * @date 2019/4/3
 */

public class FireTest {

    @Test
    public void detail() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "7a867108-2521-4d95-b482-8603ddcd4c5f");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/fireworks/detail", params);
        System.out.println(post.body().string());
    }


}

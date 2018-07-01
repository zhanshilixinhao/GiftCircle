package com.chouchongkeji;

import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

/**
 * @author linqin
 * @date 2018/6/29
 */
public class TestPoat {


    @Test
    public void test() throws IOException {
        RequestParams params = new RequestParams();
        params.put("type","shunfeng");
        params.put("postid","078174215686");
        params.put("temp",System.currentTimeMillis());
        Response response = OkHttpUtil.post("http://www.kuaidi100.com/query", params);
        System.out.println(response.body().string());
    }

}

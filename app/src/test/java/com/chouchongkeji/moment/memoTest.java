package com.chouchongkeji.moment;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author linqin
 * @date 2019/1/8 10:45
 */

public class memoTest {

    // 添加备忘录事件
    @Test
    public void forR() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("detail", "过生日d");
        params.put("targetTime", 1573178951000L);
        params.put("users", "1,14");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/add", params);
        System.out.println(post.body().string());
    }

    @Test
    public void time() throws ParseException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//可以方便地修改日期格式
        String hehe = dateFormat.format( now ); //日期
        Date parse = dateFormat.parse("20190301100911");  //时间戳
        System.out.println(parse.getTime());
        System.out.println(hehe);
    }

    // 修改备忘录事件
    @Test
    public void modify() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("id", 4);
        params.put("detail", "过生日hahh ");
        params.put("targetTime", 1573265351000L);
        params.put("users", "1,15");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/modify_affair", params);
        System.out.println(post.body().string());
    }


    //忘录列表
    @Test
    public void getList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
//        params.put("start", 1546308551000L);
//        params.put("end", 1551406151000L);
//        params.put("targetTime", 1573265351000L);
//        params.put("users", "1,15");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/list", params);
        System.out.println(post.body().string());
    }

    // 好友备忘录列表
    @Test
    public void getfList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("friendUserId", 1);
//        params.put("detail", "过生日hahh ");
//        params.put("targetTime", 1573265351000L);
//        params.put("users", "1,15");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/list/friend", params);
        System.out.println(post.body().string());
    }

    // 删除备忘录
    @Test
    public void del() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        params.put("id", 3);
//        params.put("detail", "过生日hahh ");
//        params.put("targetTime", 1573265351000L);
//        params.put("users", "1,15");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/del", params);
        System.out.println(post.body().string());
    }

    // 首页备忘录
    @Test
    public void home() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time", System.currentTimeMillis());
        params.put("access_token", "572f7a09-b3e0-4ec5-b04b-13c82771c1c8");
        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID);
        params.put("sign", map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/memo/affair/home", params);
        System.out.println(post.body().string());
    }

}

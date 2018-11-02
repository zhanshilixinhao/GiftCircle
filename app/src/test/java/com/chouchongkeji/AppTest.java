package com.chouchongkeji;

import com.chouchongkeji.goexplore.utils.ApiSignUtil;
import com.chouchongkeji.goexplore.utils.OkHttpManager;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    public void md5() {
        System.out.println(DateUtils.addDays(new Date(), 2).getTime());
    }


    @Test
    public void login() throws IOException {

        RequestParams params = new RequestParams();
        params.put("username", "18313747954");
        params.put("password", "123456");
        params.put("exploringId", 24);
        params.put("time", "1526539545791");
        params.put("app_id", "giftcircler-dl");
        params.put("app_secret", "qMEjFl8w63EtAX17cRX83L0iMkK2U4mg");

        Map map = ApiSignUtil.sign1(params.getParams(), ApiSignUtil.IOS);
        params.put("sign", map.get(ApiSignUtil.IOS));
        System.out.println(map);
        Response response = OkHttpUtil.post(OkHttpManager.create(null, null),
                "https://liyuquan.cn/app/login/phone", params);
        System.out.println(response.body().string());
    }
}

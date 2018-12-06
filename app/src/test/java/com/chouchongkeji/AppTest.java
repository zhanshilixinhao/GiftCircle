package com.chouchongkeji;

import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.utils.*;
import okhttp3.Response;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.math.BigDecimal;
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
        PayVO payVO = new PayVO();
        payVO.setBody("测试");
        payVO.setSubject("测试");
        payVO.setOrderNo(122222);
        payVO.setPrice(new BigDecimal("0.01"));
        payVO.setUrl("dddd");
//        WXPayDto prePay = WXPayService.service(payVO).createPrePay();
        String orderInfo = AliPayServiceV2.createOrderInfo(payVO);
        System.out.println(orderInfo);
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

    @Test
    public void sms() throws IOException {
        RequestParams params = new RequestParams();
        params.put("phone","15752400657");
        params.put("type",1);
        params.put("time", "1526539545791");
        Map map =ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response response = OkHttpUtil.post("https://liyuquan.cn/app/ask/code",params);
        System.out.println(response.body().string());
    }

    @Test
    public void wxLogin() throws IOException {
        RequestParams params = new RequestParams();
        params.put("code", "ewqrewrewr");
        params.put("time", "43432432");

        params.put("sign",ApiSignUtil.sign1(params.getParams(), ApiSignUtil.ANDROID).get(ApiSignUtil.ANDROID));

        Response post = OkHttpUtil.post("https://liyuquan.cn/app/noauth/user/wxLogin", params);
        System.out.println(post.body().string());
    }


    @Test
    public void messageList() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("messageType",1);
        params.put("pageNum",1);
        params.put("pageSize",14);
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8089/auth/v1/message/list", params);
        System.out.println(post.body().string());
    }

    @Test
    public void discountItem() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("bpId","7718113014145");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.ANDROID);
        params.put("sign",map.get(ApiSignUtil.ANDROID));
        Response post = OkHttpUtil.post("http://localhost:8088/auth/v1/discount/add_record", params);
        System.out.println(post.body().string());
    }

    @Test
    public void modify() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time","1543377365");
        params.put("access_token","d43baab4-d457-4c4a-ad86-84e66b0e6275");
        params.put("nickname","abc");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.IOS);
        params.put("sign",map.get(ApiSignUtil.IOS));
        Response post = OkHttpUtil.post("https://liyuquan.cn/app/auth/user/modify_profile", params);
        System.out.println(post.body().string());

//        System.out.println(Utils.toMD5("access_token=d43baab4-d457-4c4a-ad86-84e66b0e6275&nickname=abc&time=1543382182&key=%sX4H91PzuB7V%5ET4uefDnsiwzHDxOgrX"));
    }


    @Test
    public void apply() throws IOException {
        RequestParams params = new RequestParams();
        params.put("time",System.currentTimeMillis());
        params.put("access_token","f3e7f648-845e-4614-a49e-bbd8f43add65");
        params.put("name","粮食");
        params.put("address","看见反函数地方");
        params.put("registrationNo","12374839857342895");
        params.put("legalPerson","适量");
        params.put("licensePic","/avatar.jpg");
        params.put("otherPics","/avatar.jpg");
        params.put("phone","789798898");
        Map map = ApiSignUtil.sign1(params.getParams(),ApiSignUtil.IOS);
        params.put("sign",map.get(ApiSignUtil.IOS));
        Response post = OkHttpUtil.post("http://localhost:8080/auth/v1/merchant/apply", params);
        System.out.println(post.body().string());
    }
}

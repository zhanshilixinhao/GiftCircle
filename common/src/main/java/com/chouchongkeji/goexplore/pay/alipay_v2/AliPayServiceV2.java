package com.chouchongkeji.goexplore.pay.alipay_v2;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.chouchongkeji.goexplore.pay.PayVO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yichenshanren
 * @date 2017/11/1
 */

public class AliPayServiceV2 {

    public static String createOrderInfo(PayVO payVO) {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                ConfigV2.APP_ID, // appid
                ConfigV2.private_key,
                "json",
                ConfigV2.input_charset,
                ConfigV2.ali_public_key,
                ConfigV2.sign_type);
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(payVO.getBody());
        model.setSubject(payVO.getSubject());
        model.setOutTradeNo(String.valueOf(payVO.getOrderNo()));
        model.setTimeoutExpress("15m");
        model.setTotalAmount(String.valueOf(payVO.getPrice().doubleValue()));
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(payVO.getUrl());
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean notiyVerify(Map requestParams) {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
//        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
//        String test = "app_id=2017102009412940&auth_app_id=2017102009412940&body=珠宝商城&buyer_id=2088702252957362&buyer_logon_id=111***@qq.com&buyer_pay_amount=0.01&charset=utf-8&fund_bill_list=[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]&gmt_create=2017-11-02 14:35:39&gmt_payment=2017-11-02 14:35:40&invoice_amount=0.01&notify_id=cf1aaac5cf9c81aeb6d406b0a391147is2&notify_time=2017-11-02 14:35:40&notify_type=trade_status_sync&out_trade_no=10071&point_amount=0.00&receipt_amount=0.01&seller_email=15877801115@163.com&seller_id=2088821498950563&subject=-商品购买&total_amount=0.01&trade_no=2017110221001004360229369308&trade_status=TRADE_SUCCESS&version=1.0,sign=QLcBjHESfMnfDWRQORjYtkIbLjfg4csTsm5RGLvsvQDZwjlGqbUTDA8CzUb+/+6UJuztNr2iOagHYtOKSztpkVTYPJBkroQMZUyGzhX5K3/sGCiJMiq+LslKMY1KAmritFK8wE+QlJBBKml9XyF7Gl1xpRlsmmI7/VsEUnpmzAU4cpixBTlDEQYt8w0oOvcYkC6y1S2qWVpYqrusW4U+LSHK64XSrVefA3km/9gldHjb+QeOX5Z0VtyGMnJbqPGPWYtC6jkOhu7hws9koFItC3E4rpx7vHasYKXWVUmEjELIR8Hre8D+KUhM5anQAesNXvwL5/GWCl4aSUY+1+xjUw==,charset = utf-8";

        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean flag = false;
        try {
            flag = AlipaySignature.rsaCheckV1(
                    params,
                    ConfigV2.ali_public_key,
                    ConfigV2.input_charset,
                    ConfigV2.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) {
        Map map = JSON.parseObject("{\"app_id\":\"2018082361125281\",\"body\":\"礼遇圈\",\"buyer_id\":\"2088702252957362\",\"buyer_logon_id\":\"111***@qq.com\",\"buyer_pay_amount\":\"0.01\",\"charset\":\"utf-8\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"gmt_create\":\"2018-11-27 21:17:41\",\"gmt_payment\":\"2018-11-27 21:17:41\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2018112700222211741057361026886987\",\"notify_time\":\"2018-11-27 21:17:42\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1218112721101\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"seller_email\":\"3436609882@qq.com\",\"seller_id\":\"2088231386393754\",\"sign\":\"MPh7FtpdfRq9d58UAGJm6Tmn4N7bNvrqbYJF8yQwuc9Wk6ny110DHmxXJKSzqVMmmHw7GmQK4NK/BYxrsHu7g804bEzNDYDFMXCYMkRjVn+27W6w5f76A3aC6vDjN+3SuJXl1ZnK0Xsp+weTMYOnDgOceIlSzaYaNnON+pws1o4TTetfcWRvPjyS0x+HHuZOoLW0wCxFQqQdNWdgQpSRrFDN56dNNlT6UKyZi01kLjb8Jkzl3bBVOAXqg5V+njukKLC4Q+bdebicEkemJ96rLG7XM1v6YK4tXyzKOz48gvwYCAa9Dqj2zS1aOhC8DSZ/9CgNg3av2O9Ar9YZvmErZw==\",\"sign_type\":\"RSA2\",\"subject\":\"-商品购买\",\"total_amount\":\"0.01\",\"trade_no\":\"2018112722001457361008288970\",\"trade_status\":\"TRADE_SUCCESS\",\"version\":\"1.0\"}\n",
                HashMap.class);
        System.out.println(map);
        boolean b = notiyVerify(map);
        System.out.println(b);
    }
}

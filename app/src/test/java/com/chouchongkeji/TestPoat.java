package com.chouchongkeji;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author linqin
 * @date 2018/6/29
 */
public class TestPoat {


    @Test
    public void test() throws IOException {
        RequestParams params = new RequestParams();
        params.put("type", "shunfeng");
        params.put("postid", "078174215686");
        params.put("temp", System.currentTimeMillis());
        Response response = OkHttpUtil.post("http://www.kuaidi100.com/query", params);
        System.out.println(response.body().string());
    }

    @Test
    public void test1() throws IOException {
        Map<String, Object> map = JSON.parseObject("{\"app_id\":\"2018082361125281\",\"body\":\"礼遇圈\",\"buyer_id\":\"2088702252957362\",\"buyer_logon_id\":\"111***@qq.com\",\"buyer_pay_amount\":\"0.01\",\"charset\":\"utf-8\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"gmt_create\":\"2018-11-23 20:44:59\",\"gmt_payment\":\"2018-11-23 20:44:59\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2018112300222204459057361025915904\",\"notify_time\":\"2018-11-23 20:44:59\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1218112320142\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"seller_email\":\"3436609882@qq.com\",\"seller_id\":\"2088231386393754\",\"sign\":\"e+HELc3UriB6XBs7jaLCpdlh3O5ao4K6xp2KwB+3bAKUUeSnHU9BtY1lVyV2lb//D9NLORzdQxIo62w8W5Up9Htgj2DLbI5Z2EsDgZobyT8Po8/j/mAZqoOsPY+KuiqaD8eDRkboo95OCTrU+w5PAma0sSP2PqOTxn7siAZbQIPPsyULH1/tbbI+GOXyfkL6Db+PtLnvKIlfvMcI7VrnP3g+K6wqzX0RcAQUEvNQ2SApYwBzgprXgzbYwBnmOy9CQuU7IYohx8lQF8hyiLNoUVhA7EsAHx16qU/5OP9iyPOVUosH6I7b9YAcbQEyd36nOnM8c/HCmA5+iASlSnSEpQ==\",\"sign_type\":\"RSA2\",\"subject\":\"-商品购买\",\"total_amount\":\"0.01\",\"trade_no\":\"2018112322001457361008067775\",\"trade_status\":\"TRADE_SUCCESS\",\"version\":\"1.0\"}",
                new TypeReference<HashMap<String, Object>>() {
                });
        System.out.println(map);
        RequestParams params = new RequestParams();
        map.forEach(params::put);
        Response response = OkHttpUtil.post("http://localhost:8089/noauth/pay/item_order/ali", params);
        System.out.println(response.body().string());
    }




}

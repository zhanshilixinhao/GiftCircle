package com.chouchongkeji.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chouchongkeji.App;
import com.chouchongkeji.dial.dao.user.GiftPreferenceDictMapper;
import com.chouchongkeji.dial.dao.user.UserTagDictMapper;
import com.chouchongkeji.dial.pojo.user.GiftPreferenceDict;
import com.yichen.auth.redis.MRedisTemplate;
import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yichenshanren
 * @date 2018/6/21
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class UserPreference {

    @Autowired
    private UserTagDictMapper userTagDictMapper;

    @Autowired
    private AppPaymentInfoService appPaymentInfoService;

    @Autowired
    private GiftPreferenceDictMapper giftPreferenceDictMapper;

    @Autowired
    private MRedisTemplate mRedisTemplate;

    @Test
    public void addTagDict() {
        ALiPayV2Vo v2Vo = JSON.parseObject("{\"app_id\":\"2018082361125281\",\"body\":\"礼遇圈\",\"buyer_id\":\"2088702252957362\",\"buyer_logon_id\":\"111***@qq.com\",\"buyer_pay_amount\":\"0.01\",\"charset\":\"utf-8\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"gmt_create\":\"2018-11-23 20:44:59\",\"gmt_payment\":\"2018-11-23 20:44:59\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2018112300222204459057361025915904\",\"notify_time\":\"2018-11-23 20:44:59\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1218112320142\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"seller_email\":\"3436609882@qq.com\",\"seller_id\":\"2088231386393754\",\"sign\":\"e+HELc3UriB6XBs7jaLCpdlh3O5ao4K6xp2KwB+3bAKUUeSnHU9BtY1lVyV2lb//D9NLORzdQxIo62w8W5Up9Htgj2DLbI5Z2EsDgZobyT8Po8/j/mAZqoOsPY+KuiqaD8eDRkboo95OCTrU+w5PAma0sSP2PqOTxn7siAZbQIPPsyULH1/tbbI+GOXyfkL6Db+PtLnvKIlfvMcI7VrnP3g+K6wqzX0RcAQUEvNQ2SApYwBzgprXgzbYwBnmOy9CQuU7IYohx8lQF8hyiLNoUVhA7EsAHx16qU/5OP9iyPOVUosH6I7b9YAcbQEyd36nOnM8c/HCmA5+iASlSnSEpQ==\",\"sign_type\":\"RSA2\",\"subject\":\"-商品购买\",\"total_amount\":\"0.01\",\"trade_no\":\"2018112322001457361008067775\",\"trade_status\":\"TRADE_SUCCESS\",\"version\":\"1.0\"}",
                ALiPayV2Vo.class);
        Map<String, Object> map = JSON.parseObject("{\"app_id\":\"2018082361125281\",\"body\":\"礼遇圈\",\"buyer_id\":\"2088702252957362\",\"buyer_logon_id\":\"111***@qq.com\",\"buyer_pay_amount\":\"0.01\",\"charset\":\"utf-8\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"gmt_create\":\"2018-11-23 20:44:59\",\"gmt_payment\":\"2018-11-23 20:44:59\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2018112300222204459057361025915904\",\"notify_time\":\"2018-11-23 20:44:59\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"1218112320142\",\"point_amount\":\"0.00\",\"receipt_amount\":\"0.01\",\"seller_email\":\"3436609882@qq.com\",\"seller_id\":\"2088231386393754\",\"sign\":\"e+HELc3UriB6XBs7jaLCpdlh3O5ao4K6xp2KwB+3bAKUUeSnHU9BtY1lVyV2lb//D9NLORzdQxIo62w8W5Up9Htgj2DLbI5Z2EsDgZobyT8Po8/j/mAZqoOsPY+KuiqaD8eDRkboo95OCTrU+w5PAma0sSP2PqOTxn7siAZbQIPPsyULH1/tbbI+GOXyfkL6Db+PtLnvKIlfvMcI7VrnP3g+K6wqzX0RcAQUEvNQ2SApYwBzgprXgzbYwBnmOy9CQuU7IYohx8lQF8hyiLNoUVhA7EsAHx16qU/5OP9iyPOVUosH6I7b9YAcbQEyd36nOnM8c/HCmA5+iASlSnSEpQ==\",\"sign_type\":\"RSA2\",\"subject\":\"-商品购买\",\"total_amount\":\"0.01\",\"trade_no\":\"2018112322001457361008067775\",\"trade_status\":\"TRADE_SUCCESS\",\"version\":\"1.0\"}",
                new TypeReference<HashMap<String, Object>>() {
                });
        appPaymentInfoService.itemOrderAli(v2Vo, map, (byte) 2);
    }

    @Test
    public void addGiftReference() {
        String re = "数码，古玩，玉石，盗墓，书画，历史，诗词，琴棋";
        String[] split = re.split("，");
        for (String s : split) {
            GiftPreferenceDict dict = new GiftPreferenceDict();
            dict.setText(s);
            giftPreferenceDictMapper.insert(dict);
        }
    }

    @Test
    public void district(){
        mRedisTemplate.del("gc-di-li");
    }

}

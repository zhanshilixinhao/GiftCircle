package com.chouchongkeji;

import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay.service.AliPayService;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.utils.OkHttpUtil;
import com.chouchongkeji.goexplore.utils.RequestParams;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

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
        PayVO payVO = new PayVO();
        payVO.setBody("Bu");
        payVO.setSubject("shi");
        payVO.setOrderNo(23218938439L);
        payVO.setUrl("http://dsada.com");
        payVO.setPrice(new BigDecimal("0.01"));
        String orderInfo = AliPayServiceV2.createOrderInfo(payVO);
        System.out.println(orderInfo);
    }

}

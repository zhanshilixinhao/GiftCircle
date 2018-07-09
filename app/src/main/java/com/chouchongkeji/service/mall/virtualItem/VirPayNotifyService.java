package com.chouchongkeji.service.mall.virtualItem;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public interface VirPayNotifyService {
    /**
     * 支付宝支付
     *
     * @param: [aLiPayV2Vo, map, orderType]
     * @return: java.lang.String
     * @author: yy
     * @Date: 2018/6/12
     */
    String orderAliPay(ALiPayV2Vo aLiPayV2Vo, Map map, Byte orderType);

    /**
     * 微信支付
     *
     * @param: [xml, orderType]
     * @return: java.lang.String
     * @author: yy
     * @Date: 2018/6/12
     */
    String orderWXPay(String xml, Byte orderType) throws IOException, SAXException, ParserConfigurationException;
}

package com.chouchongkeji.service.user;

import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/8
 */
public interface AppPaymentInfoService {

    /**
     * 支付宝订单支付
     * @param aLiPayV2Vo
     * @param parameterMap
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    String orderAliPay(ALiPayV2Vo aLiPayV2Vo, Map map,Byte orderType);

    /**
     * 微信支付
     * @param xml
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    String orderWXPay(String xml, Byte orderType) throws IOException, SAXException, ParserConfigurationException;
}

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

    /**
     * 商品订单支付宝回调
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    String itemOrderAli(ALiPayV2Vo aLiPayV2Vo, Map parameterMap,Byte orderType);

    /**
     * 微信支付回调基础方法
     * @param
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    String ItemOrderWXPay(String xml, Byte orderType) throws IOException, SAXException, ParserConfigurationException;
}

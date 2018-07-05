package com.chouchongkeji.service.user.info;

import com.chouchongkeji.dial.pojo.user.PaymentInfo;
import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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


    /**
     * 余额支付信息
     * @param
     * @return
     * @author linqin
     *  @date 2018/6/8
     */
    PaymentInfo doYuePaySuccess(Long orderNo,Integer userId,Date created,Byte orderType,Integer sellUserId,
                                BigDecimal amount);
    /**
     * 寄售台支付宝回调基本方法
     *
     * @param aLiPayV2Vo
     * @param parameterMap
     * @param orderType    订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @author linqin
     * @date 2018/7/5
     */
    String conOrderAli(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType);

    /**
     * 寄售台微信支付回调基础方法
     *
     * @param request
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @throws Exception
     * @author linqin
     *  @date 2018/7/5
     */
    String conOrderWXPay(String xml, Byte orderType) throws ParserConfigurationException, SAXException, IOException;
}

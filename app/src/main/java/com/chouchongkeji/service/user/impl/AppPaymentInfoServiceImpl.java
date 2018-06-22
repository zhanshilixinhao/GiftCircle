package com.chouchongkeji.service.user.impl;

import com.chouchongkeji.dial.dao.gift.item.ItemMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderDetailMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.ChargeOrderMapper;
import com.chouchongkeji.dial.dao.user.PaymentInfoMapper;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.exception.ServiceException;
import com.chouchongkeji.goexplore.common.ErrorCode;
import com.chouchongkeji.goexplore.pay.alipay.config.AlipayConfig;
import com.chouchongkeji.goexplore.pay.alipay_v2.ALiPayV2Vo;
import com.chouchongkeji.goexplore.pay.alipay_v2.AliPayServiceV2;
import com.chouchongkeji.goexplore.pay.alipay_v2.ConfigV2;
import com.chouchongkeji.goexplore.pay.weixin.common.Signature;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.goexplore.pay.weixin.protocol.NotifyData;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.goexplore.utils.DateUtil;
import com.chouchongkeji.goexplore.utils.HttpClientUtils;
import com.chouchongkeji.dial.pojo.iwant.wallet.ChargeOrder;
import com.chouchongkeji.dial.pojo.user.PaymentInfo;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.user.AppPaymentInfoService;
import com.chouchongkeji.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author linqin
 * @date 2018/6/8
 */
@Service
@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
public class AppPaymentInfoServiceImpl implements AppPaymentInfoService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Autowired
    private ChargeOrderMapper chargeOrderMapper;

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Autowired
    private ItemOrderDetailMapper itemOrderDetailMapper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    @Autowired
    private ItemMapper itemMapper;
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     * 支付宝充值订单支付
     *
     * @param aLiPayV2Vo
     * @param map
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    @Override
    public String orderAliPay(ALiPayV2Vo aLiPayV2Vo, Map map, Byte orderType) {
        Long orderNo = Long.parseLong(aLiPayV2Vo.getOut_trade_no());
        //检测订单号
        ChargeOrder chargeOrder = chargeOrderMapper.selectByOrderNo(orderNo);
        //如果订单号不存在直接返回错误
        if (chargeOrder == null) {
            return "ERROR";
        }
        //通过订单号取出金额
        BigDecimal amount = chargeOrder.getAmount();
        // 校验支付的价格
        if (amount.compareTo(new BigDecimal(aLiPayV2Vo.getTotal_amount())) != 0) {
            return "ERROR";
        }
        //支付宝相关校验
        int re = checkAliPayBaseInfo(aLiPayV2Vo, map, orderNo);
        if (re == 0) {
            //更新订单支付状态
            chargeOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
            int count = chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
            if (count == 0){
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新失败");
            }
            //更新余额
            Integer userId = chargeOrder.getUserId();
            walletService.updateBalance(userId,amount);
            //支付信息
            doAliPaySuccess(aLiPayV2Vo, orderType);
        } else if (re == 2) {
            return "ERROR";
        }
        return "SUCCESS";

    }

    /**
     * 商品订单支付宝回调
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public String itemOrderAli(ALiPayV2Vo aLiPayV2Vo, Map parameterMap,Byte orderType) {
        //校验订单号
        Long orderNo = Long.parseLong(aLiPayV2Vo.getOut_trade_no());
        ItemOrder itemOrder = itemOrderMapper.selectByOrderNo(orderNo);
        if (itemOrder == null){
            return "ERROR";
        }
        //校验支付金额
        BigDecimal price = itemOrder.getTotalPrice();
        if (price.compareTo(new BigDecimal(aLiPayV2Vo.getTotal_amount()))!=0){
            return "ERROR";
        }
        //支付宝相关校验
        int i = checkAliPayBaseInfo(aLiPayV2Vo, parameterMap, orderNo);
        if (i == 0){
            //更新订单状态
            itemOrder.setStatus((byte)Constants.ORDER_BASE_STATUS.PAID);
            int count = itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
            if (count==0){
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新失败");
            }
            //更新详细订单状态和销量
            updateStatusSales(orderNo);
            //支付信息
            doAliPaySuccess(aLiPayV2Vo,orderType);
        }  else if (i == 2) {
        return "ERROR";
    }
        return "SUCCESS";
    }

    /**
     * 支付宝回调校验
     *
     * @param aLiPayV2Vo
     * @param map
     * @return 0-校验成功，1-已支付过，2-错误
     * @author linqin
     *  @date 2018/6/8
     */
    public int checkAliPayBaseInfo(ALiPayV2Vo aLiPayV2Vo, Map map, Long orderNo) {
        // 校验签名
        boolean flag = AliPayServiceV2.notiyVerify(map);
        // 签名校验不通过直接返回
        if (!flag) {
            return 2;
        }

        //校验商户号
        if (!ConfigV2.partner.equals(aLiPayV2Vo.getSeller_id())) {
            return 2;
        }
        /** 判断这次回调请求的订单是否已处理 **/
        int count = paymentInfoMapper.checkPaymentInfo(orderNo);
        /** 1 为已处理 直接返回通知支付宝 **/
        if (count == 1) {
            return 1;
        }
        /** 验签正确以后,验证是否是支付宝发来的通知 **/
        /** 参数map **/
        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("partner", ConfigV2.partner);
        params2.put("notify_id", aLiPayV2Vo.getNotify_id());
        /** 校验是否是支付宝的请求 **/
        String isAlipayInform = HttpClientUtils.doPost(ALIPAY_GATEWAY_NEW, params2, AlipayConfig.input_charset);
        /** true则代表是支付宝的请求---TRADE_SUCCESS--成功支付成功 **/
        if ("true".equalsIgnoreCase(isAlipayInform)
                && "TRADE_SUCCESS".equalsIgnoreCase(aLiPayV2Vo.getTrade_status())
                && aLiPayV2Vo.getOut_trade_no() != null) {
            /** -------------支付成功逻辑处理-------------- **/

            return 0;
        } else {
//            logger.info("支付宝支付失败");
            return 2;
        }

    }

    /**
     * 支付宝支付成功!, 保存支付信息，更新订单状态
     *
     * @param aLiPayV2Vo
     * @param orderType
     * @author linqin
     *  @date 2018/6/8
     */
    private PaymentInfo doAliPaySuccess(ALiPayV2Vo aLiPayV2Vo, Byte orderType) {
        PaymentInfo payment = new PaymentInfo();
        /** 订单号 **/
        payment.setOrderNo(Long.parseLong(aLiPayV2Vo.getOut_trade_no()));
        /** 买家账号 **/
        payment.setBuyer(aLiPayV2Vo.getBuyer_id());
        /** 付款时间 **/
        payment.setCreated(DateUtil.string2date(aLiPayV2Vo.getGmt_payment()));
        /** 订单类型 **/
        payment.setType(orderType);
        /** 支付平台 **/
        payment.setPayPlatform((byte) Constants.PAY_TYPE.ALI);
        /** 卖家账号 **/
        payment.setSeller(aLiPayV2Vo.getSeller_email());
        /** 支付总价 **/
        payment.setTotalFee(new BigDecimal(aLiPayV2Vo.getTotal_amount()));
        /** 交易流水号 **/
        payment.setPlatformNumber(aLiPayV2Vo.getTrade_no());
        /** 交易标识 **/
        payment.setPlatformStatus(aLiPayV2Vo.getTrade_status());

        /** -------------支付成功逻辑处理-------------- **/
        // 保存订单支付信息
        int count = paymentInfoMapper.insert(payment);
        if (count == 0) {
            throw new ServiceException(200, "pay failed");
        }
        return payment;
    }


    /*-----------------------------------------------支付宝支付回调结束---------------------------------------------------*/

    /*-----------------------------------------------微信支付回调开始---------------------------------------------------*/


    /**
     * 微信充值订单支付
     * @param xml
     * @param orderType
     * @return
     * @author linqin
     *  @date 2018/6/8
     */
    @Override
    public String orderWXPay(String xml, Byte orderType) throws IOException, SAXException, ParserConfigurationException{
        /** 支付成功微信服务器通知XML **/
        String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        /** 获取微信服务请求XML参数 **/
        //获取微信服务请求xml参数
        /** XML转对象 **/
        NotifyData notifyData = (NotifyData) Util.getObjectFromXML(xml, NotifyData.class);
        //校验订单号
       Long orderNo = Long.parseLong(notifyData.getOut_trade_no());
       ChargeOrder chargeOrder = chargeOrderMapper.selectByOrderNo(orderNo);
        if (chargeOrder == null){
            return "ERROR";
        }
        //通过订单号取出金额
        BigDecimal amount = chargeOrder.getAmount();
        // 检验支付金额(微信)
        if (amount.compareTo(
                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100)) != 0) {
            return "ERROR";
        }
//        // 校验支付的价格（支付宝）
//        if (amount.compareTo(new BigDecimal(notifyData.getTotal_fee())) != 0) {
//            return "ERROR";
//        }

        int re = checkBaseWxPayInfo(notifyData,  xml,orderNo);
        if (re == 0) {
            //更新订单支付状态
            chargeOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
            int count = chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
            if (count == 0){
                return "ERROR";
            }
            //更新余额
            Integer userId = chargeOrder.getUserId();
            walletService.updateBalance(userId,amount);
            //支付信息
            doWXPaySuccess(notifyData,orderType);
        } else if (re == 2) {
            return "ERROR";
        }
        return resXml;
    }

    /**
     * 微信商品订单支付
     * @param xml
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public String ItemOrderWXPay(String xml, Byte orderType) throws ParserConfigurationException, SAXException, IOException {
        /** 支付成功微信服务器通知XML **/
        String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        /** 获取微信服务请求XML参数 **/
        //获取微信服务请求xml参数
        /** XML转对象 **/
        NotifyData notifyData = (NotifyData) Util.getObjectFromXML(xml, NotifyData.class);
        //校验订单号
        Long orderNo = Long.parseLong(notifyData.getOut_trade_no());
        ItemOrder itemOrder = itemOrderMapper.selectByOrderNo(orderNo);//根据订单号取出订单信息
        if (itemOrder == null){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"该订单不存在");
        }
        //检验支付金额
        BigDecimal price = itemOrder.getTotalPrice(); //取出金额
        if (price.compareTo(
                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(),100))!=0){
            return "ERROR";
        }
        int re = checkBaseWxPayInfo(notifyData,  xml,orderNo);
        if (re ==0){
            //更新订单状态
            itemOrder.setStatus((byte)Constants.ORDER_BASE_STATUS.PAID);
            int count = itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
            if (count == 0){
                throw new ServiceException(ErrorCode.ERROR.getCode(),"更新状态失败");
            }
            //更新详细订单状态和销量
            updateStatusSales(orderNo);
            //支付信息
            doWXPaySuccess(notifyData,orderType);
        }else if (re == 2) {
            return "ERROR";
        }
        return resXml;

    }


    /**
     * 微信校验
     * @param notifyData
     * @param xml
     * @param orderNo
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @author linqin
     *  @date 2018/6/8
     */
    private int checkBaseWxPayInfo(NotifyData notifyData, String xml, Long orderNo)throws IOException,
            SAXException, ParserConfigurationException {
//        // 检验支付金额
//        if (price.compareTo(
//                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100)) != 0) {
//            return 2;
//        }
        /** 判断该请求订单是否已经处理 **/
        /** 判断这次回调请求的订单是否已处理 **/
        int count = paymentInfoMapper.checkPaymentInfo(orderNo);
        /** count==1 为已处理 直接返回成功 **/
        if (count == 1) {
            return 1;
        }
        /** 验证签名是否合法 **/
        Boolean isSign = Signature.checkIsSignValidFromResponseString(xml);
        /** 如果签名合法 isSign=true **/
        if (isSign) {
            /** 判断是否支付成功 **/
            if ("SUCCESS".equalsIgnoreCase(notifyData.getReturn_code())
                    && "SUCCESS".equalsIgnoreCase(notifyData.getResult_code())) {
//                logger.error("------------------收到回调 支付成功");
                return 1;
            }
        }
        return 2;

    }

    /**
     * 支付信息
     * @param notifyData
     * @param orderType
     * @return
     * @author linqin
     *  @date 2018/6/8
     */
    private PaymentInfo doWXPaySuccess(NotifyData notifyData, Byte orderType) {
        /** -------------支付成功逻辑处理-------------- **/
        PaymentInfo payment = new PaymentInfo();
        /** 订单号 **/
        payment.setOrderNo(Long.parseLong(notifyData.getOut_trade_no()));
        /** 买家账号 **/
        payment.setBuyer(notifyData.getOpenid());
        /** 付款时间 **/
        payment.setCreated(DateUtil.string2date(notifyData.getTime_end(), "yyyyMMddHHmmss"));
        /** 订单类型 **/
        payment.setType(orderType);
        /** 支付平台 **/
        payment.setPayPlatform((byte)Constants.PAY_TYPE.WX);
        /** 卖家账号 **/
        payment.setSeller(notifyData.getMch_id());
        /** 支付总价--分为单位 **/
        payment.setTotalFee(BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100));
        /** 交易流水号 **/
        payment.setPlatformNumber(notifyData.getTransaction_id());
        /** 交易标识 **/
        payment.setPlatformStatus(notifyData.getResult_code());
        // 保存订单支付信息
        int count = paymentInfoMapper.insert(payment);
        if (count == 0) {
            throw new ServiceException(200,"pay failed");
        }
        return payment;
    }


private void updateStatusSales(Long orderNo){
    //取出订单详细信息
    List<ItemOrderDetail> itemOrderDetail = itemOrderDetailMapper.selectByOrderNo(orderNo);
    for (ItemOrderDetail itemDetail:itemOrderDetail) {
        //更新详细订单状态
        itemDetail.setStatus((byte)Constants.ORDER_BASE_STATUS.PAID);
        int update = itemOrderDetailMapper.updateByPrimaryKeySelective(itemDetail);
        if (update == 0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"更新状态失败");
        }
        //更新ItemSku销量
        Integer skuId = itemDetail.getSkuId();
        Integer quantity = itemDetail.getQuantity();
        Integer sales = itemSkuMapper.updateSalesBySkuId(skuId,quantity);
        if (sales == 0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"更新销量失败");
        }
        //更新Item销量
        Integer itemId = itemDetail.getItemId();
        Integer ItemSales = itemMapper.updateSalesByItemId(itemId,quantity);
        if (ItemSales == 0){
            throw new ServiceException(ErrorCode.ERROR.getCode(),"更新销量失败");
        }
    }
}


}

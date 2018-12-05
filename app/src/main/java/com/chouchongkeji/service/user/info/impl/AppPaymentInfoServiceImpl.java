package com.chouchongkeji.service.user.info.impl;

import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentMapper;
import com.chouchongkeji.dial.dao.backpack.consignment.ConsignmentOrderMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderDetailMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemOrderMapper;
import com.chouchongkeji.dial.dao.iwant.wallet.ChargeOrderMapper;
import com.chouchongkeji.dial.dao.user.AppUserMapper;
import com.chouchongkeji.dial.dao.user.PaymentInfoMapper;
import com.chouchongkeji.dial.pojo.backpack.consignment.Consignment;
import com.chouchongkeji.dial.pojo.backpack.consignment.ConsignmentOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrder;
import com.chouchongkeji.dial.pojo.gift.item.ItemOrderDetail;
import com.chouchongkeji.dial.pojo.iwant.wallet.ChargeOrder;
import com.chouchongkeji.dial.pojo.user.AppUser;
import com.chouchongkeji.dial.pojo.user.PaymentInfo;
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
import com.chouchongkeji.service.backpack.base.BpService;
import com.chouchongkeji.service.mall.item.OrderService;
import com.chouchongkeji.service.iwant.wallet.WalletService;
import com.chouchongkeji.service.message.MessageService;
import com.chouchongkeji.service.user.info.AppPaymentInfoService;
import com.chouchongkeji.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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

    private static final Logger log = LoggerFactory.getLogger(AppPaymentInfoServiceImpl.class);

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
    private OrderService orderService;

    @Autowired
    private ConsignmentOrderMapper consignmentOrderMapper;

    @Autowired
    private ConsignmentMapper consignmentMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private BpService bpService;
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
        log.info("支付宝充值订单");
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
            chargeOrder.setPayWay((byte)2);
            chargeOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
            int count = chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //更新余额
            Integer userId = chargeOrder.getUserId();
            walletService.updateBalance(userId, amount);
            //支付信息
            doAliPaySuccess(aLiPayV2Vo, orderType, chargeOrder.getUserId());
        } else if (re == 2) {
            return "ERROR";
        }
        return "SUCCESS";

    }

    /**
     * 商品订单支付宝回调
     *
     * @param aLiPayV2Vo
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/6/21
     */
    @Override
    public String itemOrderAli(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        log.info("支付宝商品订单");
        //校验订单号
        Long orderNo = Long.parseLong(aLiPayV2Vo.getOut_trade_no());
        ItemOrder itemOrder = itemOrderMapper.selectByOrderNo(orderNo);
        if (itemOrder == null) {
            log.info("支付宝商品订单订单不存在{}", orderNo);
            return "ERROR";
        }
        //校验支付金额
        BigDecimal price = itemOrder.getTotalPrice();
        if (price.compareTo(new BigDecimal(aLiPayV2Vo.getTotal_amount())) != 0) {
            log.info("支付宝商品订单价格校验失败");
            return "ERROR";
        }
        //支付宝相关校验
        int i = checkAliPayBaseInfo(aLiPayV2Vo, parameterMap, orderNo);
        if (i == 0) {
            //更新订单状态
            itemOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
            int count = itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //更新详细订单状态和销量
            orderService.updateStatusSales(orderNo);
            //物品添加到背包
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByUserIdAndOrderNo(itemOrder.getUserId(), orderNo);
            int add = bpService.addFromItemOrder(list);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //支付信息
            doAliPaySuccess(aLiPayV2Vo, orderType, itemOrder.getUserId());
        } else if (i == 2) {
            return "ERROR";
        }
        return "SUCCESS";
    }


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
    @Override
    public String conOrderAli(ALiPayV2Vo aLiPayV2Vo, Map parameterMap, Byte orderType) {
        log.info("寄售台支付宝回");
        //校验订单号
        Long orderNo = Long.parseLong(aLiPayV2Vo.getOut_trade_no());
        ConsignmentOrder conOrder = consignmentOrderMapper.selectByOrderNo(orderNo);
        if (conOrder == null) {
            return "ERROR";
        }
        //校验金额
        BigDecimal price = conOrder.getPrice();
        if (price.compareTo(new BigDecimal(aLiPayV2Vo.getTotal_amount())) != 0) {
            return "ERROR";
        }
        //支付宝相关校验
        int checkInfo = checkAliPayBaseInfo(aLiPayV2Vo, parameterMap, orderNo);
        if (checkInfo == 0) { //校验通过
            //更新订单状态
            conOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
            int update = consignmentOrderMapper.updateByPrimaryKeySelective(conOrder);
            if (update == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //更新寄售台状态
            Consignment consignment = consignmentMapper.selectByPrimaryKey(conOrder.getConsignmentId());
            if (consignment == null) {
                return "ERROR";
            }
            consignment.setStatus(Constants.CONSIGNMENT_ITEM.PAY);
            int count = consignmentMapper.updateByPrimaryKeySelective(consignment);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //添加系统消息
            AppUser nickName = appUserMapper.selectByPrimaryKey(conOrder.getUserId());
            messageService.addMessage(Constants.APP_MESSAGE_TYPE.CONSIGNMENT, "您交易的物品被" + nickName.getNickname()
                    + "购买，快去看看吧", null, conOrder.getId(), conOrder.getUserId());
            //增加卖家金额
            int wall = walletService.updateBalance(conOrder.getSellUserId(), conOrder.getPrice(),
                    Constants.WALLET_RECORD.CONSIGNMENT_ITEM, conOrder.getId());
            if (wall < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "卖家余额添加失败");
            }
            //物品添加到背包
            int add = bpService.addFromConsignmengOrder(conOrder);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //保存支付信息
            doAliPaySuccess(aLiPayV2Vo, orderType, conOrder.getUserId());
        } else if (checkInfo == 2) {
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
     * @date 2018/6/8
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
        PaymentInfo count = paymentInfoMapper.checkPaymentInfo(orderNo);
        /** 1 为已处理 直接返回通知支付宝 **/
        if (count != null) {
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
     * @date 2018/6/8
     */
    private PaymentInfo doAliPaySuccess(ALiPayV2Vo aLiPayV2Vo, Byte orderType, Integer userId) {
        PaymentInfo payment = new PaymentInfo();
        /** 订单号 **/
        payment.setOrderNo(Long.parseLong(aLiPayV2Vo.getOut_trade_no()));
        /** 用户Id **/
        payment.setUserId(userId);
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
     *
     * @param xml
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    @Override
    public String orderWXPay(String xml, Byte orderType) throws IOException, SAXException, ParserConfigurationException {
        /** 支付成功微信服务器通知XML **/
        String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        /** 获取微信服务请求XML参数 **/
        //获取微信服务请求xml参数
        /** XML转对象 **/
        log.info("微信充值{}", xml);
        NotifyData notifyData = (NotifyData) Util.getObjectFromXML(xml, NotifyData.class);
        //校验订单号
        Long orderNo = Long.parseLong(notifyData.getOut_trade_no());
        ChargeOrder chargeOrder = chargeOrderMapper.selectByOrderNo(orderNo);
        if (chargeOrder == null) {
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

        int re = checkBaseWxPayInfo(notifyData, xml, orderNo);
        if (re == 0) {
            //更新订单支付状态
            chargeOrder.setPayWay((byte)1);
            chargeOrder.setStatus(Constants.CHARGE_ORDER_STATUS.PAY);
            int count = chargeOrderMapper.updateByPrimaryKeySelective(chargeOrder);
            if (count == 0) {
                return "ERROR";
            }
            //更新余额
            Integer userId = chargeOrder.getUserId();
            walletService.updateBalance(userId, amount);
            //支付信息
            doWXPaySuccess(notifyData, orderType, chargeOrder.getUserId());
        } else if (re == 2) {
            return "ERROR";
        }
        return resXml;
    }

    /**
     * 微信商品订单支付
     *
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
        log.info("微信商品读不到{}", xml);
        NotifyData notifyData = (NotifyData) Util.getObjectFromXML(xml, NotifyData.class);
        //校验订单号
        Long orderNo = Long.parseLong(notifyData.getOut_trade_no());
        ItemOrder itemOrder = itemOrderMapper.selectByOrderNo(orderNo);//根据订单号取出订单信息
        if (itemOrder == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "该订单不存在");
        }
        //检验支付金额
        BigDecimal price = itemOrder.getTotalPrice(); //取出金额
        if (price.compareTo(
                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100)) != 0) {
            return "ERROR";
        }
        int re = checkBaseWxPayInfo(notifyData, xml, orderNo);
        if (re == 0) {
            //更新订单状态
            itemOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
            int count = itemOrderMapper.updateByPrimaryKeySelective(itemOrder);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新状态失败");
            }
            //更新详细订单状态和销量
            orderService.updateStatusSales(orderNo);
            //物品加入背包里
            List<ItemOrderDetail> list = itemOrderDetailMapper.selectByUserIdAndOrderNo(itemOrder.getUserId(), orderNo);
            int add = bpService.addFromItemOrder(list);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //支付信息
            doWXPaySuccess(notifyData, orderType, itemOrder.getUserId());
        } else if (re == 2) {
            return "ERROR";
        }
        return resXml;

    }

    /**
     * 寄售台微信支付回调基础方法
     *
     * @param xml
     * @param orderType 订单类型 1-充值订单，2-商品订单，3-寄售台商品订单
     * @return
     * @throws Exception
     * @author linqin
     * @date 2018/7/5
     */
    @Override
    public String conOrderWXPay(String xml, Byte orderType) throws ParserConfigurationException, SAXException, IOException {
        /** 支付成功微信服务器通知XML **/
        String resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
        /** 获取微信服务请求XML参数 **/
        //获取微信服务请求xml参数
        /** XML转对象 **/
        log.info("微信几首太{}", xml);
        NotifyData notifyData = (NotifyData) Util.getObjectFromXML(xml, NotifyData.class);
        //校验订单号
        Long orderNo = Long.parseLong(notifyData.getOut_trade_no());
        ConsignmentOrder conOrder = consignmentOrderMapper.selectByOrderNo(orderNo);//根据订单号取出订单信息
        if (conOrder == null) {
            throw new ServiceException(ErrorCode.ERROR.getCode(), "该订单不存在");
        }
        //检验支付金额
        BigDecimal price = conOrder.getPrice(); //取出金额
        if (price.compareTo(
                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100)) != 0) {
            return "ERROR";
        }
        int re = checkBaseWxPayInfo(notifyData, xml, orderNo);
        if (re == 0) {
            //更新订单状态
            conOrder.setStatus((byte) Constants.ORDER_BASE_STATUS.PAID);
            int update = consignmentOrderMapper.updateByPrimaryKeySelective(conOrder);
            if (update == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //更新寄售台状态
            Consignment consignment = consignmentMapper.selectByPrimaryKey(conOrder.getConsignmentId());
            if (consignment == null) {
                return "ERROR";
            }
            consignment.setStatus(Constants.CONSIGNMENT_ITEM.PAY);
            int count = consignmentMapper.updateByPrimaryKeySelective(consignment);
            if (count == 0) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "更新失败");
            }
            //添加系统消息
            AppUser nickName = appUserMapper.selectByPrimaryKey(conOrder.getUserId());
            messageService.addMessage(Constants.APP_MESSAGE_TYPE.CONSIGNMENT, "您交易的物品被" + nickName.getNickname()
                    + "购买，快去看看吧", null, conOrder.getId(), conOrder.getUserId());
            //增加卖家金额
            int wall = walletService.updateBalance(conOrder.getSellUserId(), conOrder.getPrice(),
                    Constants.WALLET_RECORD.CONSIGNMENT_ITEM, conOrder.getId());
            if (wall < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //物品添加到背包
            int add = bpService.addFromConsignmengOrder(conOrder);
            if (add < 1) {
                throw new ServiceException(ErrorCode.ERROR.getCode(), "");
            }
            //保存支付信息
            doWXPaySuccess(notifyData, orderType, conOrder.getUserId());
        } else if (re == 2) {
            return "ERROR";
        }
        return resXml;
    }


    /**
     * 微信校验
     *
     * @param notifyData
     * @param xml
     * @param orderNo
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @author linqin
     * @date 2018/6/8
     */
    private int checkBaseWxPayInfo(NotifyData notifyData, String xml, Long orderNo) throws IOException,
            SAXException, ParserConfigurationException {
//        // 检验支付金额
//        if (price.compareTo(
//                BigDecimalUtil.div(new BigDecimal(notifyData.getTotal_fee()).doubleValue(), 100)) != 0) {
//            return 2;
//        }
        /** 判断该请求订单是否已经处理 **/
        /** 判断这次回调请求的订单是否已处理 **/
        PaymentInfo count = paymentInfoMapper.checkPaymentInfo(orderNo);
        /** count==1 为已处理 直接返回成功 **/
        if (count != null) {
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
                return 0;
            }
        }
        return 2;
    }

    /**
     * 微信支付信息
     *
     * @param notifyData
     * @param orderType
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    private PaymentInfo doWXPaySuccess(NotifyData notifyData, Byte orderType, Integer userId) {
        /** -------------支付成功逻辑处理-------------- **/
        PaymentInfo payment = new PaymentInfo();
        /** 订单号 **/
        payment.setOrderNo(Long.parseLong(notifyData.getOut_trade_no()));
        /** 用户Id **/
        payment.setUserId(userId);
        /** 买家账号 **/
        payment.setBuyer(notifyData.getOpenid());
        /** 付款时间 **/
        payment.setCreated(DateUtil.string2date(notifyData.getTime_end(), "yyyyMMddHHmmss"));
        /** 订单类型 **/
        payment.setType(orderType);
        /** 支付平台 **/
        payment.setPayPlatform((byte) Constants.PAY_TYPE.WX);
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
            throw new ServiceException(200, "pay failed");
        }
        return payment;
    }


    /*-----------------------------------------------微信支付回调结束---------------------------------------------------*/

    /*-----------------------------------------------余额宝支付---------------------------------------------------*/

    /**
     * 余额支付信息
     *
     * @param
     * @return
     * @author linqin
     * @date 2018/6/8
     */
    public PaymentInfo doYuePaySuccess(Long orderNo, Integer userId, Date created, Byte orderType, Integer sellUserId,
                                       BigDecimal amount) {
        /** -------------支付成功逻辑处理-------------- **/
        PaymentInfo payment = new PaymentInfo();
        /** 订单号 **/
        payment.setOrderNo(orderNo);
        /** 用户Id **/
        payment.setUserId(userId);
        /** 买家账号 **/
        payment.setBuyer(userId.toString());
        /** 付款时间 **/
        payment.setCreated(created);
        /** 订单类型 **/
        payment.setType(orderType);
        /** 支付平台 **/
        payment.setPayPlatform((byte) Constants.PAY_TYPE.yue);
        /** 卖家账号 **/
        payment.setSeller(sellUserId.toString());
        /** 支付总价--分为单位 **/
        payment.setTotalFee(amount);
        /** 交易流水号 **/
        payment.setPlatformNumber(orderNo.toString());
        /** 交易标识 **/
        payment.setPlatformStatus("SUCCESS");
        // 保存订单支付信息
        int count = paymentInfoMapper.insert(payment);
        if (count == 0) {
            throw new ServiceException(200, "pay failed");
        }
        return payment;
    }


}

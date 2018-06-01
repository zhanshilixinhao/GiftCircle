package com.chouchongkeji.goexplore.pay.alipay.service;

import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.alipay.AliPayException;
import com.chouchongkeji.goexplore.pay.alipay.SignUtils;
import com.chouchongkeji.goexplore.pay.alipay.config.AlipayConfig;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

/**
 * @author yichenshanren
 * @date 2017/10/20
 */

public class AliPayService {

    private PayVO payVO;

    private AliPayService(PayVO payVO){
        this.payVO = payVO;
    }

    public static AliPayService service(PayVO payVO) {
        AliPayService service = new AliPayService(payVO);
        return service;
    }

    public String createAndSing() {
        // 创建订单
        String orderInfo = getOrderInfo();
//        TimeLog.e("阿里de canshu", orderInfo);
        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        if (sign == null) {
            throw new AliPayException("签名时发生错误!");
        }
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + "sign_type=\"RSA\"";
        return payInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, AlipayConfig.private_key);
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo() {
        StringBuilder sb = new StringBuilder();
        // 商品详情
        sb.append("body=\"").append(payVO.getBody()).append("\"");
        // 参数编码， 固定值
        sb.append("&_input_charset=\"utf-8\"");
        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        sb.append("&it_b_pay=\"").append("15m").append("\"");
        // 服务器异步通知页面路径
//        Log.e(TAG, "getOrderInfo: 支付宝huidiao地址" + payMessage.url);
        sb.append("&notify_url=\"").append(payVO.getUrl()).append("\"");
        // 商户网站唯一订单号
        sb.append("&out_trade_no=\"").append(payVO.getOrderNo()).append("\"");
//        orderInfo += "&out_trade_no=" + "\"" + "68431957" + "\"";
        // 签约合作者身份ID
        sb.append("&partner=\"").append(AlipayConfig.partner).append("\"");
        // 支付类型， 固定值
        sb.append("&payment_type=\"1\"");
        // 签约卖家支付宝账号
        sb.append("&seller_id=\"").append(AlipayConfig.seller_id).append("\"");
//        orderInfo += "&sign_type=" + "\"" + "RSA" +"\"";
        // 服务接口名称， 固定值
        sb.append("&service=\"mobile.securitypay.pay\"");
        // 商品名称
        sb.append("&subject=\"").append(payVO.getBody()).append(payVO.getSubject()).append("\"");
        // 商品金额
        sb.append("&total_fee=\"").append(payVO.getPrice().doubleValue()).append("\"");

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";
        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//        orderInfo += "&return_url=\"m.alipay.com\"";
        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";
//        Log.e("创建订单信息", orderInfo);
        return sb.toString();
    }

    public static void main(String[] args) {
        PayVO vo = new PayVO();
        vo.setSubject("优豆充值");
        vo.setBody("优豆充值");
        vo.setOrderNo(1323);
        vo.setPrice(new BigDecimal("0.01"));
        // 创建微信支付信息
        vo.setUrl("http://youka.com/rer");
        // 创建微信支付所需信息
        System.out.println(AliPayService.service(vo).createAndSing());
    }
}

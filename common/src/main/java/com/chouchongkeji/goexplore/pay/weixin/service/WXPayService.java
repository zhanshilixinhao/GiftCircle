package com.chouchongkeji.goexplore.pay.weixin.service;


import com.alibaba.fastjson.JSON;
import com.chouchongkeji.goexplore.pay.PayVO;
import com.chouchongkeji.goexplore.pay.weixin.common.Configure;
import com.chouchongkeji.goexplore.pay.weixin.common.Util;
import com.chouchongkeji.goexplore.pay.weixin.protocol.PrePayData;
import com.chouchongkeji.goexplore.utils.BigDecimalUtil;
import com.chouchongkeji.goexplore.utils.Utils;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author yichenshanren
 * @date 2017/10/19
 */

public class WXPayService {

    private Logger logger = LoggerFactory.getLogger(WXPayService.class);

    private PayVO payVo;

    private WXPayService(PayVO payVO) {
        this.payVo = payVO;
    }

    public static WXPayService service(PayVO payVO) {
        return new WXPayService(payVO);
    }

    public WXPayDto createPrePay() {
        return doPrePayResult(createPreOrder());
    }

    /**
     * 创建预支付订单
     *
     * @return
     */
    private PrePayData createPreOrder() {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String entity = genProductArgs();
//        entity = "<xml><appid>wx67a01a97bda59412</appid><body>珠宝商城-商品购买</body><mch_id>1465159902</mch_id><nonce_str>217F5E7754C92D28FC6835D42F43548D</nonce_str><notify_url>http://localhost:8080/order/wx</notify_url><out_trade_no>10038</out_trade_no><spbill_create_ip>127.0.0.1</spbill_create_ip><total_fee>100</total_fee><trade_type>APP</trade_type><sign>08F937F558E757C78404887A49C45990</sign></xml>";
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/xml"), entity);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null && response.body() != null) {
                String str = response.body().string();
                logger.info("微信与支付："+str);
                return decodeXml(str);
            }
        } catch (Throwable e) {
            logger.info("创建预支付订单时异常", e);
        }
        throw new WXPayException("创建预支付订单时异常");
    }

    /**
     * 处理与支付订单创建结果
     *
     * @param result
     * @return
     */
    private WXPayDto doPrePayResult(PrePayData result) {
        WXPayDto payDto = new WXPayDto();
        if (result != null && StringUtils.isNotBlank(result.getReturn_code())) {
            if (result.getReturn_code().equals("SUCCESS")
                    && result.getResult_code().equals("SUCCESS")) {
                payDto.setCode(1);
                payDto.setPrepay_id(result.getPrepay_id());
                assembleAppPayParmater(payDto);
            } else {
                payDto.setCode(2);
                if (StringUtils.isNotBlank(result.getErr_code_des())) {
                    payDto.setMessage(result.getErr_code_des());
                } else if (StringUtils.isNotBlank(result.getReturn_msg())) {
                    payDto.setMessage(result.getReturn_msg());
                }
            }
        } else {
            payDto.setCode(2);
        }
        return payDto;
    }

    /**
     * 组装app支付需要的参数
     *
     * @param payDto
     */
    private void assembleAppPayParmater(WXPayDto payDto) {
        // 微信app id
        payDto.setAppid(Configure.getAppid());
        // 微信商户号
        payDto.setPartnerid(Configure.getMchid());
        // 随机字符串
        payDto.setNoncestr(genNonceStr());
        // 扩展字段
        payDto.setPackageValue("Sign=WXPay");
        // 当前时间
        payDto.setTimestamp(String.valueOf(genTimeStamp()));

        List<NameValueModel> signParams = new LinkedList<>();
        signParams.add(new NameValueModel("appid", payDto.getAppid()));
        signParams.add(new NameValueModel("noncestr", payDto.getNoncestr()));
        signParams.add(new NameValueModel("package", payDto.getPackageValue()));
        signParams.add(new NameValueModel("partnerid", payDto.getPartnerid()));
        signParams.add(new NameValueModel("prepayid", payDto.getPrepay_id()));
        signParams.add(new NameValueModel("timestamp", payDto.getTimestamp()));

        String sign = genAppSign(signParams);
        payDto.setSign(sign);
    }

    /**
     * 解析返回的XML数据
     * 转为PrepayData对象
     */
    private PrePayData decodeXml(String content) {
        try {
            PrePayData prePayData = (PrePayData) Util.getObjectFromXML(content, PrePayData.class);
            return prePayData;
        } catch (Exception e) {
            logger.info("解析xml时发生异常", e);
            throw new WXPayException("解析xml时发生异常" + e.getMessage());
        }
    }

    /**
     * 生成预支付订单内容
     */
    private String genProductArgs() {
        String nonceStr = genNonceStr();
//        nonceStr = "B2004314AA49D95302179246148E0326";
        List<NameValueModel> packageParams = new LinkedList<>();
        // appid
        packageParams.add(new NameValueModel("appid", Configure.getAppid()));
        // 商品描述
        packageParams.add(new NameValueModel("body", String.format("%s%s", payVo.getBody(), payVo.getSubject())));
        // 商户号
        packageParams.add(new NameValueModel("mch_id", Configure.getMchid()));
        // 随机字符串，不长于32位
        packageParams.add(new NameValueModel("nonce_str", nonceStr));
        // 接收微信支付异步通知回调地址
        packageParams.add(new NameValueModel("notify_url", payVo.getUrl()));
//            if (BuildConfig.DEBUG)
//                Log.e(TAG, "genProductArgs: 毁掉地址" + payMessage.url);
        // 商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
        packageParams.add(new NameValueModel("out_trade_no", String.valueOf(payVo.getOrderNo())));
        // APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
        packageParams.add(new NameValueModel("spbill_create_ip", "127.0.0.1"));
        // 订单总金额，单位为分，详见支付金额
//            Log.e(TAG, "genProductArgs: 总金额" + payMessage.price);
        packageParams.add(new NameValueModel("total_fee",
                String.valueOf(BigDecimalUtil.multi(payVo.getPrice().doubleValue(), 100).intValue())));
//            Log.e(TAG, "genProductArgs: 总金额" + payMessage.price * 100);
        // 交易类型 此处为app
        packageParams.add(new NameValueModel("trade_type", "APP"));
        // 签名
        String sign = genPackageSign(packageParams);
        packageParams.add(new NameValueModel("sign", sign));
        String xmlstring = toXml(packageParams);
        logger.info("微信："+ xmlstring);
        return xmlstring;
    }

    /**
     * 获取随机字符串
     */
    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))).toUpperCase();
    }


    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取App签名
     */
    private String genAppSign(List<NameValueModel> params) {
        StringBuffer sb = doSomething(params);
        String appSign = Utils.toMD5(sb.toString());
        if (appSign != null) {
            return appSign.toUpperCase();
        }
        throw new WXPayException("获取app签名错误!");
    }

    /**
     * 生成签名
     */
    private String genPackageSign(List<NameValueModel> params) {
        StringBuffer sb = doSomething(params);
        logger.info("签名签参数"+sb.toString());
        String packageSign = Utils.toMD5(sb.toString());
//        Log.e(TAG, "genPackageSign: 签名" + packageSign);
        if (packageSign != null) {
            return packageSign.toUpperCase();
        }
        throw new WXPayException("生成签名为null");
    }

    private StringBuffer doSomething(List<NameValueModel> params) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Configure.getKey());
        return sb;
    }

    /**
     * 将List<NameValueModel>转换为XML
     */
    private String toXml(List<NameValueModel> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<").append(params.get(i).getName()).append(">");
            sb.append(params.get(i).getValue());
            sb.append("</").append(params.get(i).getName()).append(">");
        }
        sb.append("</xml>");
        return sb.toString();
//        return sb.toString();
//        try {
//            Log.e(TAG, "toXml: " + sb.toString());
//            String xml = new String(sb.toString().getBytes(), "ISO8859-1"); // ISO8859-1
//            Log.e(TAG, "编码后 toXml: " + xml);
//            return xml;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return sb.toString();
//        }
    }

    static class MD5 {

        private MD5() {
        }

        static String getMessageDigest(String some) {
            byte[] buffer = some.getBytes();

            char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(buffer);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (byte byte0 : md) {
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } catch (Exception e) {
                return null;
            }
        }
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
        WXPayDto dto = WXPayService.service(vo).createPrePay();
        System.out.println(JSON.toJSONString(dto));
    }
}

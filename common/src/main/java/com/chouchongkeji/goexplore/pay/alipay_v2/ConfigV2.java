package com.chouchongkeji.goexplore.pay.alipay_v2;

/**
 * @author yichenshanren
 * @date 2017/11/1
 */

public class ConfigV2 {

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static final String partner = "2088231386393754";
    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串
    public static final String seller_id = partner;
    // 商户的私钥
    public static final String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDluTYqEkUxrqsbwSCZ9uKDfh9xa2reZBOgpqgkdt+M95xi3dYFJJ2/y6haTYbsgWgdumnfyaBQoJyIEuDZux8QKIfMAosCbVmq2JaoR0xibz5K8+h5Yctk3QMpx1+niF/eDkIaBvSUv7/cq1CaNqPTmr5l1SfAc1C5KnJbW8nLDMmdHcamgDoH8vEuN+ByGSJqYUf3dGRk2FSZY7z5jH8APdEZUBStfThwPUhmBSWUEdUhUMEAX03vGM72zVeGImVcvclL13GZhBDL/i8sCyAI+sBPpWOchR0dKwwRIG22eqIc4ev4/o+QX/6+7Rvn45aw93Rj9KY8fwbJcvA3mnXVAgMBAAECggEAHLqg73oBdUkm1P2+GgMqKM+pFwIcB1v0qG6vMIA9TN/aivK7RUFjCQ1rLH/tk75LSwx5GSeaCPtNGCdkP1aLd/XZpcXbpJ4QTLFxhfIEt3q6C52dQTDdwutw7dSlPcpsUtTtp1ufUsr4L5ytI9wZeOktTobtxb+mJMIOMyazgdUG3KE9yQlK1Wmq3oMcc0TpSkR02K621uOSRwAuAJn7kkinBFRBw19nF4Js1njno/qXfRPp8OSypoz9vzhUCvzJMaKC8J3gOr0a3HtNnkiUvlbUGt9GYUyZD9ijUeAQ3c046K0ITdA8o3CuzsCWCa1QfbfDcab2INec04nQOfosMQKBgQD8sww/2L/VOYr3E/MZbVfy0z2TzpqqA9mRcKJ+1bVQ5XfDh3+WVErGzGKx/A/38EPCXaxDaO1Gh6zeYYNPIcY+rpKOsl8/S+VLGl1T0VPJpY9wuKuPPw+KtyNo4Y7BIfjYJhDRmsbz5WA55qVRK9OWjwcCfTpmYM/3nx+tVtWcYwKBgQDouVbK0wcdAb2NJnNpfWc2XN0EFb+hd0iwln/eSZKVS74kJyOjN41SkHDg2XlMOOmTli70IiuT2+A/qbbgrsw3OLxD30jxcM+yx0zVDs87L3U6y/gqjherP17e8/4JGgBkdFHibnFA2O905srUCMuXVsfNr+3bKddcx2qB2pJuZwKBgQCtfTeRtfuuJj1z+GxULq/M9J44FgY1Z0M+CPhm384F5iTdRu+fg+t6pOO1knpcHMZIcQtNlIIihA949bWy5Fs4uZ3A/lFKIY8gis88NmCx9kqOdxrB0+eVsrpKzWC2094Gd8vgO8UPuZDg76rcGZzApx2G5fbsRtdBCCw1U4KARQKBgGNO44h9cKWiHn7jD5DOYILEMdQSa8m9GxmvvzTd9F0dCzBmO0Dk7btzw5dPdgFer9240qR/CFV6GEFSbKWIMcDGxx+3YmsVFqxVe6XXQfshj0bdzzJpnBQJYLSHLbo4jD5RnLehJVbku5oI+jhs4GPeNjjBgCBrxGjk98Adwyq1AoGAVbcEHyx3EpUTwe1qmkEp0p0cB58cg/7kG/kR1o6ob5SLpIlOaBO6yk2PF+o9LuT6mAucIZ1S/uJ3mPx7Xx+KAE9Ho6kSwR6D91dd6n7kHQuG1Uw8lUPT5oTmYLBuZn07vzNBafql/YE3HjDL/JCnO6D8Zjpfbbif61dxw/qO7bg=";
    // 支付宝的公钥，无需修改该值
    public static final String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5bk2KhJFMa6rG8Egmfbig34fcWtq3mQToKaoJHbfjPecYt3WBSSdv8uoWk2G7IFoHbpp38mgUKCciBLg2bsfECiHzAKLAm1ZqtiWqEdMYm8+SvPoeWHLZN0DKcdfp4hf3g5CGgb0lL+/3KtQmjaj05q+ZdUnwHNQuSpyW1vJywzJnR3GpoA6B/LxLjfgchkiamFH93RkZNhUmWO8+Yx/AD3RGVAUrX04cD1IZgUllBHVIVDBAF9N7xjO9s1XhiJlXL3JS9dxmYQQy/4vLAsgCPrAT6VjnIUdHSsMESBttnqiHOHr+P6PkF/+vu0b5+OWsPd0Y/SmPH8GyXLwN5p11QIDAQAB";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    // 调试用，创建TXT日志文件夹路径
    public static final String log_path = "/data/payLogs/";
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String input_charset = "utf-8";
    // 签名方式 不需修改
    public static final String sign_type = "RSA2";
    // 应用id
    public static final String APP_ID = "2018082361125281";


}

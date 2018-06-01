package com.chouchongkeji.goexplore.pay.alipay;

/**
 * @author yichenshanren
 * @date 2017/10/20
 */

public class AliPayException extends RuntimeException{

    public AliPayException() {
    }

    public AliPayException(String message) {
        super(message);
    }

    public AliPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public AliPayException(Throwable cause) {
        super(cause);
    }

    public AliPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

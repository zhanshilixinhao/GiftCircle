package com.chouchongkeji.goexplore.pay.weixin.service;

/**
 * @author yichenshanren
 * @date 2017/10/19
 */

public class WXPayException extends RuntimeException {

    public WXPayException() {
    }

    public WXPayException(String message) {
        super(message);
    }

    public WXPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public WXPayException(Throwable cause) {
        super(cause);
    }

    public WXPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

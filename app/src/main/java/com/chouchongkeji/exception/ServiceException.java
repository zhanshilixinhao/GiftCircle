package com.chouchongkeji.exception;


import com.chouchongkeji.goexplore.common.ErrCodeException;
import com.chouchongkeji.goexplore.common.ErrorCode;

/**
 * @author linqin
 * @date 2018/5/22
 */
public class ServiceException extends RuntimeException implements ErrCodeException {
    private int errCode;

    public ServiceException(String msg) {
        super(msg);
        this.errCode = ErrorCode.ERROR.getCode();
    }

    public ServiceException(int errCode) {
        this.errCode = errCode;
    }

    public ServiceException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

package com.chouchongkeji.exception;

import com.alibaba.druid.wall.violation.ErrorCode;

/**
 * @author linqin
 * @date 2018/5/22
 */
public class ServiceException extends RuntimeException implements ErrorCode {
    private int errCode;

    public ServiceException(int errCode) {
        this.errCode = errCode;
    }

    public ServiceException(int errCode,String message) {
        super(message);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

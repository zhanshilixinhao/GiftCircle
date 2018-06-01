package com.yichen.auth.verify;

import com.chouchongkeji.goexplore.common.ErrCodeException;
import org.springframework.security.core.AuthenticationException;

/**
 * @author yichenshanren
 * @date 2017/11/26
 */

public class VerifyException extends AuthenticationException implements ErrCodeException{

    private int errCode;

    public VerifyException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerifyException(String msg) {
        super(msg);
    }

    public VerifyException(int t,String msg) {
        super(msg);
        this.errCode = t;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }
}

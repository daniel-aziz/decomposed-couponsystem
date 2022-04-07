package com.jb.couponSystem.Exceptions;


/**
 * General Exception for the System
 */
public class CouponSystemException extends Exception{

    // C'TOR for super massages
    public CouponSystemException() {
        super();
    }

    // C'TOR for Custom massages
    public CouponSystemException(String message) {
        super(message);
    }

    // C'TOR for defined massages
    public CouponSystemException(SystemErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}

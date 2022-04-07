package com.jb.couponSystem.Exceptions;


/**
 * Token Exception for the System
 */
public class TokenException extends Exception{

    // C'TOR for super massages
    public TokenException() {
        super();
    }

    // C'TOR for Custom massages
    public TokenException(String message) {
        super(message);
    }

    // C'TOR for defined massages
    public TokenException(SystemErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}

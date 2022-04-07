package com.jb.couponSystem.Advice;

import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * advice for intercept and handle exception for controllers
 */
@RestController
@ControllerAdvice
public class CouponSystemRestException {

    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleCouponSystemException(Exception err) {
        return new ErrorDetail(err.getMessage());
    }

    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorDetail handleTokenException(Exception err) {
        return new ErrorDetail(err.getMessage());
    }

}

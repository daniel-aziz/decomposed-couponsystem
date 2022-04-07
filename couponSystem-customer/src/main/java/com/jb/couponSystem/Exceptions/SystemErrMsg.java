package com.jb.couponSystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Collection of enums that define the system errors and attached massages
 */
@AllArgsConstructor
@Getter
@ToString
public enum SystemErrMsg {
    // coupon entity errors
    COUPON_IS_EXIST("This coupon already exist in the system"),
    COUPON_NOT_EXIST("This coupon does not exist in the system"),
    // customer entity errors
    CUSTOMER_IS_EXIST("This customer already exist in the system"),
    CUSTOMER_NOT_EXIST("This customer does not exist in the system"),
    // customer entity errors
    COMPANY_IS_EXIST("This company already exist in the system"),
    COMPANY_NOT_EXIST("This company does not exist in the system"),
    // general issues errors
    GENERAL("General Error. Contact admin for more details"),
    EMPTY_VALUES("No values have been submitted"),
    // login error errors
    BAD_CREDENTIALS("Invalid Email or Password"),
    BAD_USER_TYPE("Invalid user type"),
    // purchase coupon errors
    COUPON_EXPIRED ("Coupon date expired"),
    ALREADY_PURCHASED("Coupon have already been purchased"),
    OUT_OF_STOCK("This coupon is out of Stock"),
    // token errors
    TOKEN_EXPIRED("Token session expired, Please log in"),
    UNAUTHORIZED("Unauthorized access, Please log in"),
    TOKEN_INVALID("Token is invalid, Please log in");

    // FIELDS
    private final String description; // takes the error description



}

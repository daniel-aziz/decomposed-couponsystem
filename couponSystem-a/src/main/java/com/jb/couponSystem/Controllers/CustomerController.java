package com.jb.couponSystem.Controllers;


import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.Services.CustomerService;
import com.jb.couponSystem.Utils.ControllerUtil;
import com.jb.couponSystem.Utils.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller to represent the methods available to the registered customer
 */
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    // FIELDS
    private final CustomerService customerService;
    private final JWTutil jwTutil;
    private final ControllerUtil controllerUtil;


    // METHODS


    /**
     * purchase coupon for the customer
     *
     * @param token String for validation
     * @param id    of coupon
     * @return ResponseEntity - null
     * @throws CouponSystemException
     */
    @PostMapping("purchaseCoupon/{id}")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int id) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                customerService.purchaseCoupon(id);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }

    /**
     * get all the customers coupons
     *
     * @param token String for validation
     * @return ResponseEntity - list of coupons
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/all")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, customerService.getCustomerCoupons(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }

    /**
     * get all the customers coupons by category
     *
     * @param token    String for validation
     * @param category
     * @return ResponseEntity - list of coupons
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/category/{category}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, customerService.getCustomerCoupons(category), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }


    /**
     * get all the customers coupons by category by price
     * @param token String for validation
     * @param price
     * @return ResponseEntity - list of coupons
     * @throws CouponSystemException
     */
    @Deprecated
    @GetMapping("getAllCoupons/price/{price}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, customerService.getCustomerCoupons(price), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }

    /**
     * get all the customers coupons by category by price between
     *
     * @param token String for validation
     * @param priceMin
     * @param priceMax
     * @return ResponseEntity - list of coupons
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/price/{priceMin}/{priceMax}")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double priceMin, @PathVariable double priceMax) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, customerService.getCustomerCoupons(priceMin, priceMax), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }

    /**
     * gets the all customer details
     *
     * @param token String for validation
     * @return ResponseEntity - Customer
     * @throws CouponSystemException
     */
    @PostMapping("getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            customerService.setCustomerId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, customerService.getCustomerDetails(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }

}

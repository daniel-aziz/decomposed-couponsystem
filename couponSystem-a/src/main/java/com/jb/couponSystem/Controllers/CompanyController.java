package com.jb.couponSystem.Controllers;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.Coupon;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.Services.CompanyService;
import com.jb.couponSystem.Utils.ControllerUtil;
import com.jb.couponSystem.Utils.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {

    // FIELDS
    private final CompanyService companyService;
    private final JWTutil jwTutil;
    private final ControllerUtil controllerUtil;

    // METHODS

    /**
     * add coupon for the company
     *
     * @param token  String for validation
     * @param coupon
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("addCoupon")
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                companyService.addCoupon(coupon);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * get one coupon of the company
     *
     * @param token  String for validation
     * @param couponId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getOneCoupon/{couponId}")
    public ResponseEntity<?> getOneCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getOneCoupon(couponId), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * update coupon for the company
     *
     * @param token  String for validation
     * @param coupon
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("updateCoupon")
    public ResponseEntity<?> updateCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                companyService.updateCoupon(coupon);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * delete a coupon for the company
     *
     * @param token    String for validation
     * @param couponId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @DeleteMapping("deleteCoupon/{couponId}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                companyService.deleteCoupon(couponId);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.ACCEPTED);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * gets all the company coupons
     *
     * @param token String for validation
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/all")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getCompanyCoupons(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * gets all the company coupons by category
     *
     * @param token String for validation
     * @return ResponseEntity
     * @return
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/category/{category}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getCompanyCoupons(category), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * gets all the company coupons by price
     *
     * @param token String for validation
     * @param price
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @Deprecated
    @GetMapping("getAllCoupons/price/{price}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double price) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getCompanyCoupons(price), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * gets all the company coupons by price
     *
     * @param token    String for validation
     * @param priceMin
     * @param priceMax
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getAllCoupons/price/{priceMin}/{priceMax}")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token, @PathVariable double priceMin, @PathVariable double priceMax) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getCompanyCoupons(priceMin, priceMax), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * get the company details
     *
     * @param token String for validation
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getCompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            companyService.setCompanyId(jwTutil.extractUserId(token));
            try {
                return controllerUtil.responseEntityBuilder(token, companyService.getCompanyDetails(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


}

package com.jb.couponSystem.Controllers;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.ContactDetails;
import com.jb.couponSystem.Beans.Customer;
import com.jb.couponSystem.Beans.UserDetails;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.LoginManager.LoginManager;
import com.jb.couponSystem.Services.GuestService;
import com.jb.couponSystem.Utils.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller to represent the methods available to the Guest
 */
@RestController
@RequestMapping("guest")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuestController {
    // FIELDS
    private final LoginManager loginManager;
    private final GuestService guestService;
    private final ControllerUtil controllerUtil;

    // METHODS

    /**
     * uses login manger to login to system and receive a valid token
     *
     * @param userDetails
     * @return ResponseEntity
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails) {
        try {
            String token = loginManager.login(userDetails.getEmail(), userDetails.getPassword(), userDetails.getClientType());
            return controllerUtil.responseEntityBuilder(token, null,HttpStatus.OK);
        } catch (CouponSystemException | TokenException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * register a customer to the DB
     *
     * @param customer
     * @return ResponseEntity
     */
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            guestService.register(customer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (CouponSystemException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * gets all the coupons from the DB
     *
     * @return ResponseEntity
     */
    @GetMapping("getAllCoupons/all")
    public ResponseEntity<?> getAllCoupons() {
        return new ResponseEntity<>(guestService.getAllCoupons(), HttpStatus.OK);
    }

    /**
     * gets all the coupons from the DB by category
     *
     * @param category
     * @return
     */
    @GetMapping("getAllCoupons/category/{category}")
    public ResponseEntity<?> getAllCoupons(@PathVariable Category category) {
        return new ResponseEntity<>(guestService.getAllCoupons(category), HttpStatus.OK);
    }

    /**
     * gets all the coupons from the DB by max price
     *
     * @param price
     * @return
     */
    @Deprecated
    @GetMapping("getAllCoupons/price/{price}")
    public ResponseEntity<?> getAllCoupons(@PathVariable double price) {
        return new ResponseEntity<>(guestService.getAllCoupons(price), HttpStatus.OK);
    }

    /**
     * gets all the coupons from the DB by price Min and price Max
     *
     * @param priceMin
     * @param priceMax
     * @return
     */
    @GetMapping("getAllCoupons/priceBetween/{priceMin}/{priceMax}")
    public ResponseEntity<?> getAllCoupons(@PathVariable double priceMin, @PathVariable double priceMax) {
        return new ResponseEntity<>(guestService.getAllCoupons(priceMin, priceMax), HttpStatus.OK);
    }

    /**
     * gets all the coupon categories for the db
     * @return ResponseEntity
     */
    @GetMapping("getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(guestService.getAllCategories(),HttpStatus.OK);
    }

    /**
     * Send an email to admin from website via contact form
     * @return
     */
    @PostMapping("contactUs")
    public ResponseEntity<?> contactAdmin(@RequestBody ContactDetails contactDetails) {
        try {
            guestService.contactAdmin(contactDetails);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (CouponSystemException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

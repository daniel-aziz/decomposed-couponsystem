package com.jb.couponSystem.Services;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.Coupon;
import com.jb.couponSystem.Beans.Customer;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * this class includes methods to interact with the program for Customer end user, uses all repositories for executions
 */
public class CustomerService extends ClientService {

    // FIELDS
    private int customerId; // the customer id to interact with the DB


    // METHODS

    /**
     * check's if user login details are correct
     * if correct sets the user customerId
     *
     * @param email    receive a String of email field
     * @param password receive a String of password field
     * @return boolean answer
     */
    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            this.setCustomerId(customerRepository.findIdByEmail(email));
            return true;
        } else return false;
    }

    /**
     * purchase coupon for the customer, and insert it into the DB
     * will execute if , amount of coupon is higher than 0, is not expired, the customer hasn't purchased the coupon already
     * will lower the amount of the coupon by 1, add the purchase to DB, and update the coupon in the DB
     *
     * @param id receives a coupon id
     * @throws CouponSystemException if purchase is invalid
     */
    public void purchaseCoupon(int id) throws CouponSystemException {
        if (couponRepository.existsById(id)) {
            Coupon coupon = couponRepository.getById(id);
            if (coupon.getAmount() > 0) {
                if (coupon.getEndDate().toLocalDate().isAfter(LocalDate.now())) {
                    if (couponRepository.isCouponPurchased(this.getCustomerId(), coupon.getId()).size() < 1) {
                        Customer customer = this.getCustomerDetails();
                        customer.getCoupons().add(coupon);
                        customerRepository.save(customer);
                        coupon.setAmount(coupon.getAmount() - 1);
                        couponRepository.save(coupon);
                    } else throw new CouponSystemException(SystemErrMsg.ALREADY_PURCHASED);
                } else throw new CouponSystemException(SystemErrMsg.COUPON_EXPIRED);
            } else throw new CouponSystemException(SystemErrMsg.OUT_OF_STOCK);
        } else throw new CouponSystemException(SystemErrMsg.COUPON_NOT_EXIST);
    }

    /**
     * gets all the (logged) customer coupon list from the DB
     *
     * @return List of Coupons
     */
    public List<Coupon> getCustomerCoupons() {
        return couponRepository.getAllCouponsOfCustomer(this.getCustomerId());
    }

    /**
     * gets all the (logged) customer coupon list by category from the DB
     *
     * @param category receives a coupon enum category
     * @return List of Coupons
     */
    public List<Coupon> getCustomerCoupons(Category category) {
        return couponRepository.getAllCouponsOfCustomerAndCategory(this.getCustomerId(), category.toString());
    }


    /**
     * gets all the (logged) customer coupon list by maxPrice from the DB
     *
     * @param price receives a coupon field maxPrice
     * @return List of Coupons
     */
    @Deprecated
    public List<Coupon> getCustomerCoupons(double price) {
        return couponRepository.getAllCouponsOfCustomerAndPrice(this.getCustomerId(), price);
    }

    /**
     * gets all the (logged) customer coupon list by maxPrice from the DB
     *
     * @param priceMax receives a coupon field max Price
     * @param priceMin receives a coupon field min Price
     * @return List of Coupons
     */
    public List<Coupon> getCustomerCoupons(double priceMin, double priceMax) {
        return couponRepository.getAllCouponsOfCustomerAndPriceBetween(this.getCustomerId(), priceMin, priceMax);
    }

    /**
     * gets the details of the logged customer from the DB
     *
     * @return a customer bean
     */
    public Customer getCustomerDetails() {
        return customerRepository.getById(this.getCustomerId());
    }



}



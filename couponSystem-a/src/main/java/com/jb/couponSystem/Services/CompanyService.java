package com.jb.couponSystem.Services;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.Company;
import com.jb.couponSystem.Beans.Coupon;
import com.jb.couponSystem.Exceptions.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Getter
@Setter
/**
 * this class includes methods to interact with the program for Company end user, uses all repositories for executions
 */
public class CompanyService extends ClientService {
    // FIELDS

    private int companyId; // the company id to interact with the DB

    // METHODS

    /**
     * check's if user login details are correct
     * if correct sets the user companyID
     *
     * @param email    receive a String of email field
     * @param password receive a String of password field
     * @return boolean answer
     */
    @Override
    public boolean login(String email, String password) {
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            setCompanyId(companyRepository.findIdByEmail(email));
            return true;
        } else return false;
    }

    /**
     * add a coupon to the DB
     *
     * @param coupon receives a coupon bean
     * @throws CouponSystemException
     */
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon != null) {
            if (!couponRepository.existsByTitleAndCompany_Id(coupon.getTitle(), this.companyId)) {
                coupon.setCompany(this.getCompanyDetails());
                couponRepository.save(coupon);
            } else throw new CouponSystemException(SystemErrMsg.COUPON_IS_EXIST);
        } else throw new CouponSystemException(SystemErrMsg.EMPTY_VALUES);

    }

    /**
     * update a coupon to the DB
     *
     * @param coupon receives a coupon bean
     * @throws CouponSystemException
     */
    public void updateCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon != null) {
            if (!couponRepository.existsByIdNotAndTitleAndCompany_Id(coupon.getId(), coupon.getTitle(), this.companyId)) {
                coupon.setCompany(this.getCompanyDetails());
                couponRepository.saveAndFlush(coupon);
            } else throw new CouponSystemException(SystemErrMsg.COUPON_IS_EXIST);
        } else throw new CouponSystemException(SystemErrMsg.EMPTY_VALUES);
    }

    /**
     * delete a coupon from the DB
     *
     * @param couponId receives a coupon id field
     * @throws CouponSystemException
     */
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (couponRepository.existsById(couponId) && couponRepository.existsByIdAndCompany_Id(couponId, this.companyId)) {
            couponRepository.deleteAllCouponPurchases(couponId);
            couponRepository.deleteCouponById(couponId);
        } else throw new CouponSystemException(SystemErrMsg.COUPON_NOT_EXIST);
    }

    /**
     * gets one coupon of the (logged) company
     *
     * @return  Coupon
     */
    public Coupon getOneCoupon(int couponId) {
        return couponRepository.findByIdAndCompanyId(couponId ,this.getCompanyId());
    }

    /**
     * gets all the (logged) company coupon list from the DB
     *
     * @return List of Coupons
     */
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompanyId(this.getCompanyId());
    }

    /**
     * gets all the (logged) company coupon list by category from the DB
     *
     * @param category receives a coupon enum category
     * @return List of Coupons
     */
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.findByCompanyIdAndCategory(this.getCompanyId(), category);
    }

    /**
     *
     * gets all the (logged) company coupon list by maxPrice from the DB
     *
     * @param price receives a coupon field Price
     * @return List of Coupons
     */
    @Deprecated
    public List<Coupon> getCompanyCoupons(double price) {
        return couponRepository.findByCompanyIdAndPriceLessThanEqual(this.getCompanyId(), price);
    }

    /**
     * gets all the (logged) company coupon list by maxPrice from the DB
     *
     * @param priceMin receives a coupon field PriceMin
     * @param priceMax receives a coupon field priceMax
     * @return List of Coupons
     */
    public List<Coupon> getCompanyCoupons(double priceMin, double priceMax) {
        return couponRepository.findByCompanyIdAndPriceBetween(this.getCompanyId(), priceMin, priceMax);
    }

    /**
     * gets the details of the logged company from the DB
     *
     * @return a company bean
     */
    public Company getCompanyDetails() {
        return companyRepository.getById(this.getCompanyId());
    }
}

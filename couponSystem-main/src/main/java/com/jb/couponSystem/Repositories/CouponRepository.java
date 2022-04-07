package com.jb.couponSystem.Repositories;

import com.jb.couponSystem.Beans.Category;
import com.jb.couponSystem.Beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    // METHODS
    /**
     * gets all coupons by category
     * @param category
     * @return List of coupons
     */
    List<Coupon> findByCategory(Category category);

    @Deprecated
    /**
     * gets all coupons by less than price
     * @param price
     * @return List of coupons
     */
    List<Coupon> findByPriceLessThanEqual(double price);

    /**
     * gets all coupons by less than price between
     * @param priceMin
     * @param priceMax
     * @return List of coupons
     */
    List<Coupon> findByPriceBetween(double priceMin, double priceMax);

    /**
     * gets all company coupons
     *
     * @param company_id
     * @return List of coupons
     */
    List<Coupon> findByCompanyId(int company_id);

    /**
     * gets all company coupons by category
     *
     * @param company_id
     * @param category
     * @return List of coupons
     */
    List<Coupon> findByCompanyIdAndCategory(int company_id, Category category);

    /**
     * Gets one coupon of a compnay
     * @param id
     * @param company_id
     * @return
     */
    Coupon findByIdAndCompanyId(int id, int company_id);

    @Deprecated
    /**
     * gets all company coupons by less than price
     *
     * @param company_id
     * @param price
     * @return List of coupons
     */
    List<Coupon> findByCompanyIdAndPriceLessThanEqual(int company_id, double price);

    /**
     * gets all company coupons by price min and price max
     *
     * @param company_id
     * @param priceMin
     * @param priceMax
     * @return List of coupons
     */
    List<Coupon> findByCompanyIdAndPriceBetween(int company_id, double priceMin, double priceMax);

    /**
     * gets all customer coupons
     *
     * @param customer_id
     * @return List of coupons
     */
    @Query(value = "SELECT coupon.* FROM coupon " +
            "JOIN customer_coupons ON (coupon.id = customer_coupons.coupons_id) " +
            "WHERE customer_coupons.customer_id = ?1", nativeQuery = true)
    List<Coupon> getAllCouponsOfCustomer(int customer_id);


    /**
     * gets all customer coupons by category
     *
     * @param customer_id
     * @param category
     * @return List of coupons
     */
    @Query(value = "SELECT coupon.* FROM coupon " +
            "JOIN customer_coupons ON (coupon.id = customer_coupons.COUPONS_ID) " +
            "WHERE customer_coupons.customer_id = ?1 AND coupon.category = ?2"
            , nativeQuery = true)
    List<Coupon> getAllCouponsOfCustomerAndCategory(int customer_id, String category);

    @Deprecated
    /**
     * gets all customer coupons by less than price
     *
     * @param customer_id
     * @param price
     * @return List of coupons
     */
    @Query(value = "SELECT coupon.* FROM coupon " +
            "JOIN customer_coupons ON (coupon.id = customer_coupons.coupons_id) " +
            "WHERE customer_coupons.customer_id = ?1 AND coupon.Price <= ?2"
            , nativeQuery = true)
    List<Coupon> getAllCouponsOfCustomerAndPrice(int customer_id, double price);

    /**
     * gets all customer coupons priceMin and priceMax
     *
     * @param customer_id
     * @param priceMin
     * @param priceMax
     * @return List of coupons
     */
    @Query(value = "SELECT coupon.* FROM coupon " +
            "JOIN customer_coupons ON (coupon.id = customer_coupons.coupons_id) " +
            "WHERE customer_coupons.customer_id = ?1 AND (coupon.Price BETWEEN ?2 AND ?3)"
            , nativeQuery = true)
    List<Coupon> getAllCouponsOfCustomerAndPriceBetween(int customer_id, double priceMin, double priceMax);

    /**
     * checks if a coupon exists by title and companyId
     * @param title
     * @param Company_Id
     * @return boolean answer
     */
    boolean existsByTitleAndCompany_Id(String title, int Company_Id);

    /**
     * checks if a coupon exists by id not equal and title and companyId
     * @param id
     * @param title
     * @param company_id
     * @return boolean answer
     */
    boolean existsByIdNotAndTitleAndCompany_Id(int id, String title, int company_id);

    /**
     * return a map of Integer of coupon purchase
     * @param customer_id
     * @param coupons_id
     * @return Map<Integer, Integer>
     */
    @Query(value = "SELECT * FROM customer_coupons WHERE customer_id=?1 AND coupons_id=?2", nativeQuery = true)
    Map<Integer, Integer> isCouponPurchased(int customer_id, int coupons_id);

    /**
     * deletes expired coupons (by end_date) from the db using current_date()
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupon WHERE end_date < current_date()", nativeQuery = true)
    void deleteExpiredCoupons();

    /**
     * used instead of inherited method deleteById()
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupon WHERE id=?1", nativeQuery = true)
    void deleteCouponById(int id);

    /**
     * checks if a row exists by email and password
     * @param id coupon ID
     * @param company_Id company ID
     * @return boolean answer
     */
    boolean existsByIdAndCompany_Id(int id, int company_Id);

    /**
     * delete One Coupon Purchase for the purchase table
     * @param customer_id
     * @param coupons_id
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer_coupons WHERE customer_id=?1 AND coupons_id=?2", nativeQuery = true)
    void deleteOneCouponPurchase(int customer_id, int coupons_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE coupon.* FROM coupon " +
            "JOIN customer_coupons ON (coupon.id = customer_coupons.coupons_id)" +
            "WHERE customer_coupons.customer_id = ?1 ", nativeQuery = true)
    void deleteAllCustomerPurchases(int customer_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customer_coupons " +
            "WHERE customer_coupons.coupons_id = ?1", nativeQuery = true)
    void deleteAllCouponPurchases(int coupons_id);
}

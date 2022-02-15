package com.jb.couponSystem.Services;

import com.jb.couponSystem.Beans.Company;
import com.jb.couponSystem.Beans.Coupon;
import com.jb.couponSystem.Beans.Customer;

import com.jb.couponSystem.Exceptions.*;
import lombok.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * this service includes methods to interact with the program for ADMIN end user, uses all repositories for executions
 */
@Service
@NoArgsConstructor
@Data
public class AdminService extends ClientService {
    // FIELDS

    private static final String ADMIN_EMAIL = "admin@admin.com"; // HARD_CODED EMAIL
    private static final String ADMIN_PASSWORD = "admin"; // HARD_CODED PASSWORD
    private int id = 0;

    // METHODS

    /**
     * check's if user login details are correct
     *
     * @param email    receive a String of email field
     * @param password receive a String of password field
     * @return boolean answer
     */
    @Override
    public boolean login(String email, String password) {
        return (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD));
    }

    /**
     * add a company to the DB
     *
     * @param company receives a company bean
     * @throws CouponSystemException
     */
    public void addCompany(Company company) throws CouponSystemException {
        if (company != null) {
            if (!(companyRepository.existsByNameOrEmail(company.getName(), company.getEmail()))) {
                company.setCoupons(new ArrayList<>());
                companyRepository.save(company);
            } else throw new CouponSystemException(SystemErrMsg.COMPANY_IS_EXIST);
        } else throw new CouponSystemException(SystemErrMsg.EMPTY_VALUES);
    }

    /**
     * update a company to the DB
     *
     * @param company receives a company bean
     * @throws CouponSystemException
     */
    public void updateCompany(Company company) throws CouponSystemException {
        if (company != null) {
            if (!companyRepository.existsByIdNotAndEmail(company.getId(), company.getEmail())) {
                company.setCoupons(companyRepository.getById(company.getId()).getCoupons());
                companyRepository.saveAndFlush(company);
            } else throw new CouponSystemException(SystemErrMsg.COMPANY_IS_EXIST);
        } else throw new CouponSystemException(SystemErrMsg.EMPTY_VALUES);
    }

    /**
     * delete a company from the DB
     *
     * @param companyId receives a company field
     * @throws CouponSystemException
     */
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (companyRepository.existsById(companyId)) {

            Company company = companyRepository.getById(companyId);
            for (Coupon item: company.getCoupons()) {
                couponRepository.deleteAllCouponPurchases(item.getId());
            }
            companyRepository.deleteById(companyId);
        } else throw new CouponSystemException(SystemErrMsg.COMPANY_NOT_EXIST);
    }

    /**
     * gets a companies lists from the DB
     *
     * @return ArrayList of Companies
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    /**
     * gets a company from the DB
     *
     * @param companyId receives a company Id field
     * @return a Company object
     * @throws CouponSystemException
     */
    public Company getOneCompany(int companyId) throws CouponSystemException {
        if (companyRepository.existsById(companyId)) {
            return companyRepository.getById(companyId);
        } else throw new CouponSystemException(SystemErrMsg.COMPANY_NOT_EXIST);
    }

    /**
     * add a customer to the DB
     *
     * @param customer receives a customer bean
     * @throws CouponSystemException
     */
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (!(customerRepository.existsByEmail(customer.getEmail()))) {
            customerRepository.save(customer);
        } else throw new CouponSystemException(SystemErrMsg.CUSTOMER_IS_EXIST);
    }

    /**
     * update a customer to the DB
     *
     * @param customer receives a customer bean
     * @throws CouponSystemException
     */
    public void updateCustomer(Customer customer) throws CouponSystemException {
        if (customer != null) {
            if (!customerRepository.existsByIdNotAndEmail(customer.getId(), customer.getEmail())) {
                customer.setCoupons(companyRepository.getById(customer.getId()).getCoupons());
                customerRepository.saveAndFlush(customer);
            } else throw new CouponSystemException(SystemErrMsg.CUSTOMER_IS_EXIST);
        } else throw new CouponSystemException(SystemErrMsg.EMPTY_VALUES);
    }

    /**
     * delete a customer from the DB
     *
     * @param customerId receives a customer id field
     * @throws CouponSystemException
     */
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if (customerRepository.existsById(customerId)) {
          //  couponRepository.deleteAllCustomerPurchases(customerId);
            customerRepository.deleteById(customerId);
        } else throw new CouponSystemException(SystemErrMsg.CUSTOMER_NOT_EXIST);
    }

    /**
     * gets a customers list from the DB
     *
     * @return ArrayList of Customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * gets a Customer object from the DB
     *
     * @param customerId receives a customer id field
     * @return a customer object
     * @throws CouponSystemException
     */
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        if (customerRepository.existsById(customerId)) {
            return customerRepository.getById(customerId);
        } else throw new CouponSystemException(SystemErrMsg.CUSTOMER_NOT_EXIST);
    }


}

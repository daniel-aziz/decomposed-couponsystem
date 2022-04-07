package com.jb.couponSystem.Services;

import com.jb.couponSystem.Repositories.CompanyRepository;
import com.jb.couponSystem.Repositories.CouponRepository;
import com.jb.couponSystem.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * this is and abstract service, defines the abstract methods and fields to inherit to all services.
 */
public abstract class ClientService {
    // FIELDS

    // initialized all Repositories for inherited services to use
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    // METHODS

    /**
     * defines how login method signature
     * @param email receive a String of email field
     * @param password receive a String of password field
     * @return a boolean answer
     */
    public abstract boolean login(String email, String password);
}

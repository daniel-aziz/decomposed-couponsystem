package com.jb.couponSystem.LoginManager;

import com.jb.couponSystem.Beans.UserDetails;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.Services.AdminService;
import com.jb.couponSystem.Services.CompanyService;
import com.jb.couponSystem.Services.CustomerService;
import com.jb.couponSystem.Utils.JWTutil;
import lombok.RequiredArgsConstructor;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import org.springframework.stereotype.Service;

/**
 * this service used for validating the login into the system
 *
 */
@Service
@RequiredArgsConstructor
public class LoginManager {
    // FIELDS
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final JWTutil jwTutil;

    // METHODS
    /**
     * this login method checks, users entered type,email,password. uses switch to locate how to check login and to return a token.
     * will throw an exception if user credentials or type are incorrect
     * @param email receives user entered email field
     * @param password receives user entered password field
     * @param clientType receives user entered email enum
     * @return string of token
     * @throws CouponSystemException thrown if user entered credentials/type is incorrect
     */
    public String login(String email, String password, ClientType clientType) throws TokenException {
        switch (clientType) {
            case ADMIN:
                if (adminService.login(email.toLowerCase(), password)) {
                    return jwTutil.generateToken(new UserDetails(email, clientType, adminService.getId()));
                } else throw new TokenException(SystemErrMsg.BAD_CREDENTIALS);
            case COMPANY:
                if (companyService.login(email.toLowerCase(), password)) {
                    return jwTutil.generateToken(new UserDetails(email, clientType, companyService.getCompanyId()));
                } else throw new TokenException(SystemErrMsg.BAD_CREDENTIALS);
            case CUSTOMER:
                if (customerService.login(email.toLowerCase(), password)) {
                    return jwTutil.generateToken(new UserDetails(email, clientType, customerService.getCustomerId()));
                } else throw new TokenException(SystemErrMsg.BAD_CREDENTIALS);
            default:
                throw new TokenException(SystemErrMsg.BAD_USER_TYPE);
        }
    }
}

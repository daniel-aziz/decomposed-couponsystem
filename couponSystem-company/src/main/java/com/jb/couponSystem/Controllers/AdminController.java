package com.jb.couponSystem.Controllers;

import com.jb.couponSystem.Beans.Company;
import com.jb.couponSystem.Beans.Customer;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.Services.AdminService;
import com.jb.couponSystem.Utils.ControllerUtil;
import com.jb.couponSystem.Utils.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*") // origins = "http://localhost:3000"
public class AdminController {

    //FIELDS

    private final AdminService adminService;
    private final JWTutil jwTutil;
    private final ControllerUtil controllerUtil;

    // Methods



    /**
     * add a company to the system
     * @param token for validation
     * @param company
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws CouponSystemException , TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.addCompany(company);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * update a company from the system
     * @param token for validation
     * @param company
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("updateCompany")
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) throws  TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.updateCompany(company);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * delete a company from the system
     * @param token for validation
     * @param companyId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @DeleteMapping("deleteCompany/{companyId}")
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws  TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.deleteCompany(companyId);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.ACCEPTED);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    /**
     * gets all the companies in the system
     * @param token for validation
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getAllCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                return controllerUtil.responseEntityBuilder(token, adminService.getAllCompanies(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * get one company by id from the system
     * @param token for validation
     * @param companyId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getOneCompany/{companyId}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                return controllerUtil.responseEntityBuilder(token, adminService.getOneCompany(companyId), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * add a customer to the system
     * @param token for validation
     * @param customer
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.addCustomer(customer);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * update a customer from the system
     * @param token for validation
     * @param customer
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.updateCustomer(customer);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * delete a customer from the system
     * @param token for validation
     * @param customerId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @DeleteMapping("deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                adminService.deleteCustomer(customerId);
                return controllerUtil.responseEntityBuilder(token, null, HttpStatus.ACCEPTED);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * gets all the customers in the system
     * @param token for validation
     * @return ResponseEntity
     * @throws TokenException
     */
    @PostMapping("getAllCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                return controllerUtil.responseEntityBuilder(token, adminService.getAllCustomers(), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * get one customer by id from the system
     * @param token for validation
     * @param customerId
     * @return ResponseEntity
     * @throws CouponSystemException
     */
    @PostMapping("getOneCustomer/{customerId}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token, @PathVariable int customerId) throws TokenException {
        if (jwTutil.validateToken(token)) {
            try {
                return controllerUtil.responseEntityBuilder(token, adminService.getOneCustomer(customerId), HttpStatus.OK);
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(SystemErrMsg.TOKEN_EXPIRED,HttpStatus.UNAUTHORIZED);
    }


}

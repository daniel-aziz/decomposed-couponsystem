package com.jb.couponSystem.Utils;

import com.jb.couponSystem.Beans.UserDetails;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * utils class holds methods for controllers
 */
@Component
@RequiredArgsConstructor
public class ControllerUtil {
    private final JWTutil jwTutil;

    /**
     * create a and return ResponseEntity
     * @param token
     * @param body
     * @param status
     * @return
     * @throws CouponSystemException
     */
    public ResponseEntity<?> responseEntityBuilder(@NotNull String token, @Nullable Object body, @NotNull HttpStatus status) throws CouponSystemException,TokenException {
        UserDetails user = new UserDetails(jwTutil.extractEmail(token), jwTutil.extractClientType(token), jwTutil.extractUserId(token));
        switch (status) {
            case OK:
                return ResponseEntity.ok()
                        .header("Authorization", "Bearer " + jwTutil
                                .generateToken(user))  // token
                        .body(body); // list or smth
            case ACCEPTED:
                return ResponseEntity.accepted()
                        .header("Authorization","Bearer " +  jwTutil
                                .generateToken(user))
                        .body(body);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

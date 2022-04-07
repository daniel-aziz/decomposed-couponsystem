package com.jb.couponSystem.Utils;

import com.jb.couponSystem.Beans.UserDetails;
import com.jb.couponSystem.Exceptions.SystemErrMsg;
import com.jb.couponSystem.Exceptions.CouponSystemException;
import com.jb.couponSystem.Exceptions.TokenException;
import com.jb.couponSystem.LoginManager.ClientType;
import io.jsonwebtoken.*;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT util is a util class for Json Web Token
 */

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
//@SuppressWarnings("all")
public class JWTutil {
    // FIELDS

    // Signature algorithm field - type of encryption
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();

    // Encoded secret key field - our private key
    private String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bits+long";

    // Decoded secret key field - creates our private key
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), signatureAlgorithm);

    // Sets the Expiration time of the token (Minutes)
    private final int EXPIRATION_TIME = 30;

    // METHODS

    /**
     * Generate token
     * this method generates our token
     *
     * @param userDetails- the user's details
     * @return Token in String
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", userDetails.getClientType().toString());
        claims.put("userId", userDetails.getUserId());
        return createToken(claims, userDetails.getEmail());
    }


    /**
     * Create token
     * this method creates our token
     *
     * @param claims  - contains the fields which we are basing the token on
     * @param subject - contains the user email
     * @return Token in String
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Instant now = Instant.now();
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(EXPIRATION_TIME, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    /**
     * Extract all claims
     * this method extracts all the claims in json
     *
     * @param token
     * @return Claims
     * @throws CouponSystemException
     */
    private Claims extractAllClaims(String token) throws TokenException {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(this.decodedSecretKey).build();
            if (jwtParser == null) return null;
            else return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException err) {
            throw new TokenException(SystemErrMsg.TOKEN_EXPIRED);
        } catch (NullPointerException err) {
            throw new TokenException(SystemErrMsg.TOKEN_INVALID);
        } catch (Exception err) {
            throw new TokenException(SystemErrMsg.GENERAL);
        }
    }

    /**
     * Extract email of user from the token
     *
     * @param token
     * @return String email
     * @throws CouponSystemException
     */
    public String extractEmail(String token) throws TokenException {
        try {
            return extractAllClaims(getCleanToken(token)).getSubject();
        } catch (Exception err) {
            throw new TokenException(SystemErrMsg.UNAUTHORIZED);
        }

    }

    /**
     * Extract ClientType of user from the token
     *
     * @param token
     * @return int clientId
     * @throws CouponSystemException
     */
    public int extractUserId(String token) throws TokenException {
        try {
            return ((int) extractAllClaims(getCleanToken(token)).get("userId"));
        } catch (Exception err) {
            throw new TokenException(SystemErrMsg.UNAUTHORIZED);
        }

    }


    /**
     * Extract ClientType of user from the token
     *
     * @param token
     * @return ClientType
     * @throws CouponSystemException
     */
    public ClientType extractClientType(String token) throws TokenException {
        try {
            return ClientType.valueOf(extractAllClaims(getCleanToken(token)).get("userType").toString());
        } catch (Exception err) {
            throw new TokenException(SystemErrMsg.UNAUTHORIZED);
        }
    }

    /**
     * this method checks if the token is expired
     *
     * @param token - the user's token
     * @return boolean- if it's expired
     */

    private boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (TokenException e) {
            return true;
        }
    }

    @Deprecated
    /**
     * this method checks if the token clientId is equal to the ClientId that uses the System
     *
     * @param token    String
     * @param clientId int
     * @return boolean
     */
    public boolean isClientInvalidate(String token, int clientId) {
        try {
            return extractUserId(token) != clientId;
        } catch (TokenException e) {
            return true;
        }
    }

    /**
     * this method checks the validation of the user's details with the token
     *
     * @param token
     * @return boolean
     * @throws CouponSystemException
     */
    public boolean validateToken(String token) throws TokenException {
        if (isTokenExpired(getCleanToken(token))) throw new TokenException(SystemErrMsg.TOKEN_EXPIRED);
        else return true;
    }

    private String getCleanToken(String token) {
        try {
            return token.split(" ")[1].strip();
        } catch (Exception err) {
            return token;
        }
    }

}

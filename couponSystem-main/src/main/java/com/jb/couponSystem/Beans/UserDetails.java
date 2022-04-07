package com.jb.couponSystem.Beans;


import com.jb.couponSystem.LoginManager.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * This is a Class bean. defines the `UserDetails` object and represent the user data
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Scope("prototype")
public class UserDetails {
    // takes the user email
    private String email;
    // takes the user password
    private String password;
    // takes the user Client Type
    private ClientType clientType;
    // takes the user id
    private int userId;


    /**
     * CTOR without the password
     * @param email
     * @param clientType
     * @param userId
     */
    public UserDetails(String email, ClientType clientType, int userId) {
        this.email = email;
        this.clientType = clientType;
        this.userId = userId;
    }

}

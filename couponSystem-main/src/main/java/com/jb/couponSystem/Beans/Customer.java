package com.jb.couponSystem.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * This is a Class entity. defines the `Customer` object
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "customer")
public class Customer {
    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // takes the customer ID, auto-incremented

    private String firstName; // takes the customer First Name
    private String lastName; // takes the customer Last Name

    @Column(nullable = false, unique = true)
    private String email; // takes the customer Email
    private String password; // takes the customer Password

    @Singular
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private List<Coupon> coupons; // the customer related coupons list


}

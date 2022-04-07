package com.jb.couponSystem.Beans;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


/**
 * This is a Class Entity. defines the `Company` object
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "company")
public class Company {
    // FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // takes the company ID, auto-incremented

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email; // take the company email

    private String password; // take the company password

    @Singular
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "company")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties("company")
    private List<Coupon> coupons; // the company related coupons list

}

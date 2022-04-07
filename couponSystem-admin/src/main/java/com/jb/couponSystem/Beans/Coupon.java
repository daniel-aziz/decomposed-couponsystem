package com.jb.couponSystem.Beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;

/**
 * This is a Class entity. defines the `Coupon` object
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "coupon")
public class Coupon {

    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  // takes the coupon ID, auto-incremented

    @ManyToOne()
    @ToString.Exclude
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnoreProperties({"email","password","coupons"})
    private Company company; // takes the company Id related to the coupon in the Company Object

    @Enumerated(EnumType.STRING)
    private Category category; // takes the coupon Category in String

    private String title; // takes the coupon head title
    private String description; // takes the coupon description
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd")
    private Date startDate; // takes the coupon activation date (Start date)
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "yyyy-MM-dd")
    private Date endDate; // takes the coupon expiration date (End date)
    private int amount; // take in the amount of the coupon availability
    private double price; // take in the coupon price
    private String image; // take in coupon image url


}

package com.jb.couponSystem.Beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This is a Class Entity. represent the `Category` Enum
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "category")
public class CategoryEntity {

    @Id
    private int id; // Category id

    private String category; // Category description

}
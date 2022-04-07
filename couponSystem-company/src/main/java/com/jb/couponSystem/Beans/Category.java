package com.jb.couponSystem.Beans;


/**
 * Enum for coupon category options
 */
public enum Category {
    Automotive("Automotive"),
    Beauty("Beauty"),
    Clothing("Clothing"),
    Electronics("Electronics"),
    Entertainment("Entertainment"),
    Financial("Financial"),
    Fitness("Fitness"),
    Food("Food"),
    Garden("Garden"),
    General("General"),
    Gifts("Gifts"),
    Health("Health"),
    Home("Home"),
    Jewelry("Jewelry"),
    Travel("Travel");

    // FIELDS
    private String description;

    // METHODS
    public String getDescription() {
        return description;
    }

    // C'TOR
    Category(String description) {
        this.description = description;
    }



}

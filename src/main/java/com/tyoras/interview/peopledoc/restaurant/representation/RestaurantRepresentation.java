package com.tyoras.interview.peopledoc.restaurant.representation;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable Restaurant representation
 * @author yoan
 */
public class RestaurantRepresentation {

    @Length(max = 50)
    private final String restaurant;

    @JsonCreator
    public RestaurantRepresentation(@JsonProperty("restaurant") String name) {
        this.restaurant = name;
    }

    @JsonProperty("restaurant")
    public String getRestaurant() {
        return restaurant;
    }
}

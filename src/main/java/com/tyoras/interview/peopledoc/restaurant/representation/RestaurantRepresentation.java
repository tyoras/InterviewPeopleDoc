package com.tyoras.interview.peopledoc.restaurant.representation;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestaurantRepresentation {
	private long id;

    @Length(max = 3)
    private String content;

    public RestaurantRepresentation() {
        // Jackson deserialization
    }

    public RestaurantRepresentation(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}

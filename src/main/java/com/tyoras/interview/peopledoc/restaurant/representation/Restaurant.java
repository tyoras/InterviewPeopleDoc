package com.tyoras.interview.peopledoc.restaurant.representation;

import java.util.Objects;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Immutable Restaurant representation
 * @author yoan
 */
public class Restaurant {

    @Length(max = 50)
    private final String name;

    @JsonCreator
    public Restaurant(@JsonProperty("restaurant") String name) {
        this.name = name;
    }

    @JsonProperty("restaurant")
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) obj;
        return Objects.equals(this.name, that.name);
    }
	

	@Override
	public final String toString() {
		return MoreObjects.toStringHelper(this).add("name", name).toString();
	}
}

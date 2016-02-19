package com.tyoras.interview.peopledoc.restaurant.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.tyoras.interview.peopledoc.restaurant.representation.Restaurant;

public class RestaurantMapper implements ResultSetMapper<Restaurant> {

	@Override
	public Restaurant map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
		return new Restaurant(resultSet.getString("name"));
	}
	
}

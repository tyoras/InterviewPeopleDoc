package com.tyoras.interview.peopledoc.restaurant.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.tyoras.interview.peopledoc.restaurant.representation.Restaurant;

@RegisterMapper(RestaurantMapper.class)
public interface RestaurantDAO {

	@SqlUpdate("insert into restaurant (name) values (:name)")
	void insert(@Bind("name") String name);

	@SqlQuery("select * from restaurant where name = :name")
	Restaurant findByName(@Bind("name") String name);
	
	@SqlQuery("select * from restaurant")
	List<Restaurant> list();
	
	@SqlUpdate("delete from restaurant where name = :name")
    void deleteByName(@Bind("name") String name);
	
}

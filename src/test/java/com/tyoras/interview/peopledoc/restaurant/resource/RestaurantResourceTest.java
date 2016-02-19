package com.tyoras.interview.peopledoc.restaurant.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import jersey.repackaged.com.google.common.collect.Lists;

import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;

import com.tyoras.interview.peopledoc.restaurant.dao.RestaurantDAO;
import com.tyoras.interview.peopledoc.restaurant.representation.Restaurant;

public class RestaurantResourceTest {
	private static final String RESTAURANT_TARGET = "/restaurants";
	private static final String RANDOM_TARGET = RESTAURANT_TARGET + "/random";
	private static final RestaurantDAO mockedRestoDAO = mock(RestaurantDAO.class);
	private static final GenericType<List<Restaurant>> RESTO_LIST_TYPE = new GenericType<List<Restaurant>>() {};
	
	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new RestaurantResource(mockedRestoDAO))
            .build();
	
	@After
    public void tearDown() {
        reset(mockedRestoDAO);
    }
	
	@Test
	public void list_should_work_with_result() {
		//given
		Client client = resources.client();
		Restaurant restoA = new Restaurant("resto A");
		Restaurant restoB = new Restaurant("resto B");
		when(mockedRestoDAO.list()).thenReturn(Lists.newArrayList(restoA, restoB));
		
		//when
		List<Restaurant> result = client.target(RESTAURANT_TARGET).request(APPLICATION_JSON_TYPE).get(RESTO_LIST_TYPE);
		
		//then
		assertThat(result).isNotNull();
		assertThat(result).containsExactly(restoA, restoB);
		verify(mockedRestoDAO).list();
	}
	
	@Test
	public void list_should_work_without_result() {
		//given
		Client client = resources.client();
		when(mockedRestoDAO.list()).thenReturn(Lists.newArrayList());
		
		//when
		List<Restaurant> result = client.target(RESTAURANT_TARGET).request(APPLICATION_JSON_TYPE).get(RESTO_LIST_TYPE);
		
		//then
		assertThat(result).isNotNull();
		assertThat(result).isEmpty();
		verify(mockedRestoDAO).list();
	}
	
	@Test
	public void getRandom_should_work_with_result() {
		//given
		Client client = resources.client();
		Restaurant restoA = new Restaurant("resto A");
		Restaurant restoB = new Restaurant("resto B");
		when(mockedRestoDAO.list()).thenReturn(Lists.newArrayList(restoA, restoB));
		
		//when
		Restaurant result = client.target(RANDOM_TARGET).request(APPLICATION_JSON_TYPE).get(Restaurant.class);
		
		//then
		assertThat(result).isNotNull();
		assertThat(result).isIn(restoA, restoB);
		verify(mockedRestoDAO).list();
	}
	
	@Test
	public void getRandom_should_work_without_result() {
		//given
		Client client = resources.client();
		when(mockedRestoDAO.list()).thenReturn(Lists.newArrayList());
		
		//when
		Response response = client.target(RANDOM_TARGET).request(APPLICATION_JSON_TYPE).get();
		
		//then
		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(NOT_FOUND.getStatusCode());
		verify(mockedRestoDAO).list();
	}
	
	@Test
	public void create_should_work() {
		//gvien
		Client client = resources.client();
		Restaurant restaurant = new Restaurant("MacDo");
		
		//when
		Response response = client.target(RESTAURANT_TARGET)
								  .request(APPLICATION_JSON_TYPE)
								  .post(Entity.entity(restaurant, APPLICATION_JSON_TYPE));
		
		//then
		verify(mockedRestoDAO).insert(restaurant.getName());
		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(CREATED.getStatusCode());
		Restaurant returnedResto = response.readEntity(Restaurant.class);
		assertThat(returnedResto).isNotNull();
		assertThat(returnedResto).isEqualTo(restaurant);
	}
	
	@Test
	public void delete_should_work() {
		//gvien
		Client client = resources.client();
		String deletedRestoName = "MacDo";
		
		//when
		Response response = client.target(RESTAURANT_TARGET + "/" + deletedRestoName).request().delete();
		
		//then
		verify(mockedRestoDAO).deleteByName(deletedRestoName);
		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(NO_CONTENT.getStatusCode());
	}
}

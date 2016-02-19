package com.tyoras.interview.peopledoc.restaurant.representation;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantRepresentationTest {
	
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void should_serialize_to_JSON() throws Exception {
    	//given
        final RestaurantRepresentation representation = new RestaurantRepresentation("MacDo");
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/valid_restaurant.json"), RestaurantRepresentation.class));

        //when
        String serializedRepresentation = MAPPER.writeValueAsString(representation);
        
        //then
        assertThat(serializedRepresentation).isEqualTo(expected);
    }
    
    @Test
    public void should_deserialize_from_JSON() throws Exception {
    	//given
    	final String json = fixture("fixtures/valid_restaurant.json");
        final RestaurantRepresentation expected = new RestaurantRepresentation("MacDo");

        //when
        RestaurantRepresentation deserializedRepresentation = MAPPER.readValue(json, RestaurantRepresentation.class);
        
        //then
        assertThat(deserializedRepresentation).isNotNull();
        assertThat(deserializedRepresentation.getRestaurant()).isEqualTo(expected.getRestaurant());
    }
    
}

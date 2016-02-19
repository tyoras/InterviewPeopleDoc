package com.tyoras.interview.peopledoc.restaurant.representation;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestaurantTest {
	
	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void should_serialize_to_JSON() throws Exception {
    	//given
        final Restaurant representation = new Restaurant("MacDo");
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/valid_restaurant.json"), Restaurant.class));

        //when
        String serializedRepresentation = MAPPER.writeValueAsString(representation);
        
        //then
        assertThat(serializedRepresentation).isEqualTo(expected);
    }
    
    @Test
    public void should_deserialize_from_JSON() throws Exception {
    	//given
    	final String json = fixture("fixtures/valid_restaurant.json");
        final Restaurant expected = new Restaurant("MacDo");

        //when
        Restaurant deserializedRepresentation = MAPPER.readValue(json, Restaurant.class);
        
        //then
        assertThat(deserializedRepresentation).isNotNull();
        assertThat(deserializedRepresentation.getName()).isEqualTo(expected.getName());
    }
    
}

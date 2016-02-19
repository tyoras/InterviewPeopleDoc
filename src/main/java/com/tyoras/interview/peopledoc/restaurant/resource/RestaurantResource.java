package com.tyoras.interview.peopledoc.restaurant.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotBlank;

import com.tyoras.interview.peopledoc.restaurant.dao.RestaurantDAO;
import com.tyoras.interview.peopledoc.restaurant.representation.Restaurant;


@Path("/restaurants")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RestaurantResource {
	
	@NotNull
	private final RestaurantDAO dao;

    public RestaurantResource(RestaurantDAO dao) {
    	this.dao = dao;
    }

    @POST
    public Response create(@Valid Restaurant representation) {
    	dao.insert(representation.getName());
    	final URI createdURI = URI.create("restaurants/random");
        return Response.created(createdURI).entity(representation).build();
    }
    
    @GET
    public Response list() {
        final List<Restaurant> restaurants = dao.list();
        return Response.ok(restaurants).build();
    }
    
    @GET
    @Path("/random")
    public Response getRandom() {
        final List<Restaurant> restaurants = dao.list();
        if (restaurants == null || restaurants.isEmpty()) {
            return Response.status(NOT_FOUND).build();
        }
        
        int randomIndex = new Random().nextInt(restaurants.size());
        final Restaurant resto = restaurants.get(randomIndex);
        return Response.ok(resto).build();
    }
    
    @DELETE
    @Path("/{name}")
    public Response delete(@PathParam("name") @NotBlank String name) {
    	dao.deleteByName(name);
        return Response.noContent().build();
    }
}

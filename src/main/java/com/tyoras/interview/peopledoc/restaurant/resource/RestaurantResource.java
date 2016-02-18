package com.tyoras.interview.peopledoc.restaurant.resource;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.tyoras.interview.peopledoc.restaurant.representation.RestaurantRepresentation;


@Path("/restaurants")
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantResource {
	
	private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public RestaurantResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @POST
    public Response create() {
    	final long id = store.add(userId.get(), notification);
        return Response.created(UriBuilder.fromResource(RestaurantResource.class)
                                          .build(userId.get(), id))
                       .build();
    }
    
    @GET
    @Timed
    public RestaurantRepresentation sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new RestaurantRepresentation(counter.incrementAndGet(), value);
    }
}

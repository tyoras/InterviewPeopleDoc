package com.tyoras.interview.peopledoc.infra;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.tyoras.interview.peopledoc.restaurant.health.RestaurantHealthCheck;
import com.tyoras.interview.peopledoc.restaurant.resource.RestaurantResource;

public class RestaurantChooserApplication extends Application<RestaurantChooserConfiguration> {
	
	public static void main(String[] args) throws Exception {
        new RestaurantChooserApplication().run(args);
    }

    @Override
    public String getName() {
        return "Restaurant Chooser";
    }

    @Override
    public void initialize(Bootstrap<RestaurantChooserConfiguration> bootstrap) {
        // nothing to do yet
    }
    
	@Override
	public void run(RestaurantChooserConfiguration configuration, Environment environment) {
		final RestaurantResource restaurantResource = new RestaurantResource(configuration.getTemplate(), configuration.getDefaultName());
		final RestaurantHealthCheck restaurantHealthCheck = new RestaurantHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", restaurantHealthCheck);
		environment.jersey().register(restaurantResource);
	}

}

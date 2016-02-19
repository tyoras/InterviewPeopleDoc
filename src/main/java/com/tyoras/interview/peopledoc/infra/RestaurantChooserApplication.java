package com.tyoras.interview.peopledoc.infra;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import com.tyoras.interview.peopledoc.restaurant.dao.RestaurantDAO;
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
    	bootstrap.addBundle(new MigrationsBundle<RestaurantChooserConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(RestaurantChooserConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }
    
	@Override
	public void run(RestaurantChooserConfiguration configuration, Environment environment) {
		final DBIFactory factory = new DBIFactory();
	    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "restaurantDB");
	    final RestaurantDAO dao = jdbi.onDemand(RestaurantDAO.class);
	    
		final RestaurantResource restaurantResource = new RestaurantResource(dao);
//		final RestaurantHealthCheck restaurantHealthCheck = new RestaurantHealthCheck(configuration.getTemplate());
//		environment.healthChecks().register("restaurant", restaurantHealthCheck);
		environment.jersey().register(restaurantResource);
	}

}

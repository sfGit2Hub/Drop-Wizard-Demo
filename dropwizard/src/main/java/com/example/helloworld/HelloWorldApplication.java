package com.example.helloworld;

import com.example.helloworld.dao.PersonDAO;
import com.example.helloworld.model.Person;
import com.example.helloworld.resources.HelloWorldResource;
import com.example.helloworld.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    private final HibernateBundle<HelloWorldConfiguration> hibernate = new HibernateBundle<HelloWorldConfiguration>(Person.class) {
        public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName());

        final PersonDAO dao = new PersonDAO(hibernate.getSessionFactory());

        environment.jersey().register(new PersonResource(dao));
        environment.jersey().register(resource);

    }

}

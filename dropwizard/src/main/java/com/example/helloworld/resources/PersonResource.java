package com.example.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.api.Saying;
import com.example.helloworld.dao.PersonDAO;
import com.example.helloworld.model.Person;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(value = "person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private PersonDAO dao;

    public PersonResource(PersonDAO dao){
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    public Saying sayHello(@QueryParam("id") Long id) {
        Person person = dao.findById(id);
        Saying saying = new Saying(person.getId(),person.getName());
        return saying;
    }
}

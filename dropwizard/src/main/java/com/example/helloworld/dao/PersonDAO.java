package com.example.helloworld.dao;

import com.example.helloworld.model.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory){
        super(factory);
    }

    public Person findById(Long id){
        return get(id);
    }

    public long create(Person person){
        return persist(person).getId();
    }

    @Override
    protected List<Person> list(Query query) throws HibernateException {
        return super.list(query);
    }

    @Override
    protected List<Person> list(Criteria criteria) throws HibernateException {
        return super.list(criteria);
    }
}

package com.erudio.SBJP_Studies.service;

import com.erudio.SBJP_Studies.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Person findById(String id) {

        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("Uberlândia - Minas Gerais - Brasil");
        person.setGender("Male");
        return person;
    }


}

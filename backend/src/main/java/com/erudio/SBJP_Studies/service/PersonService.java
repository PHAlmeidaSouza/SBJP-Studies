package com.erudio.SBJP_Studies.service;

import com.erudio.SBJP_Studies.model.Person;
import com.erudio.SBJP_Studies.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PersonService {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    PersonRepository personRepository;

    public List<Person> findAll() {

        logger.info("Finding all people!");

        return personRepository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Finding one person!");

        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));
    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        var entity = personRepository.findById(person.getId()).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}

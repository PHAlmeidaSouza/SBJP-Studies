package com.erudio.SBJP_Studies.service;

import com.erudio.SBJP_Studies.data.dto.v1.PersonDTO;
import com.erudio.SBJP_Studies.data.dto.v2.v1.PersonDTOV2;
import com.erudio.SBJP_Studies.mapper.DozerMapper;
import com.erudio.SBJP_Studies.mapper.custom.PersonMapper;
import com.erudio.SBJP_Studies.model.Person;
import com.erudio.SBJP_Studies.repository.PersonRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;

@Service
public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonDTO> findAll() {

        logger.info("Finding all people!");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {

        logger.info("Finding one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);

        return DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {

        logger.info("Creating one person with V2!");

        var entity = personMapper.convertDTOToEntity(person);

        return personMapper.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {

        logger.info("Updating one person!");

        var entity = personRepository.findById(person.getId()).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}

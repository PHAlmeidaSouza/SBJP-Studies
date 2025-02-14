package com.erudio.SBJP_Studies.service;

import com.erudio.SBJP_Studies.data.vo.v1.PersonVO;
import com.erudio.SBJP_Studies.data.vo.v2.v1.PersonVOV2;
import com.erudio.SBJP_Studies.mapper.DozerMapper;
import com.erudio.SBJP_Studies.mapper.custom.PersonMapper;
import com.erudio.SBJP_Studies.model.Person;
import com.erudio.SBJP_Studies.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);

        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with V2!");

        var entity = personMapper.convertVoToEntity(person);

        return personMapper.convertEntityToVo(personRepository.save(entity));
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = personRepository.findById(person.getId()).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = personRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}

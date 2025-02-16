package com.erudio.SBJP_Studies.mapper.custom;

import com.erudio.SBJP_Studies.data.dto.v2.v1.PersonDTOV2;
import com.erudio.SBJP_Studies.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {

        PersonDTOV2 DTO = new PersonDTOV2();
        DTO.setId(person.getId());
        DTO.setAddress(person.getAddress());
        DTO.setBirthDate(new Date());
        DTO.setFirstName(person.getFirstName());
        DTO.setLastName(person.getLastName());
        DTO.setGender(person.getGender());

        return DTO;
    }

    public Person convertDTOToEntity(PersonDTOV2 person) {

        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());

        return entity;
    }
}

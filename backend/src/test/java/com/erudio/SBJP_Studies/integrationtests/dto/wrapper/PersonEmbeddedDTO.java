package com.erudio.SBJP_Studies.integrationtests.dto.wrapper;

import com.erudio.SBJP_Studies.integrationtests.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PersonEmbeddedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("people")
    private List<PersonDTO> people;

}

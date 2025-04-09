package com.erudio.SBJP_Studies.integrationtests.dto.wrapper.xml;

import com.erudio.SBJP_Studies.integrationtests.dto.PersonDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement
public class PagedModelPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "content")
    private List<PersonDTO> content;
}

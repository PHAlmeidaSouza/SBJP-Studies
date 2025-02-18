package com.erudio.SBJP_Studies.data.dto.v1;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String launchDate;
    private Double price;
    private String title;

}

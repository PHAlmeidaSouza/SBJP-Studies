package com.erudio.SBJP_Studies.integrationtests.dto.wrapper.json;

import com.erudio.SBJP_Studies.integrationtests.dto.BookDTO;
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
public class BookEmbeddedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("books")
    private List<BookDTO> books;
}

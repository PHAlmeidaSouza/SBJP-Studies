package com.erudio.SBJP_Studies.controller;

import com.erudio.SBJP_Studies.data.dto.v1.PersonDTO;
import com.erudio.SBJP_Studies.data.dto.v2.v1.PersonDTOV2;
import com.erudio.SBJP_Studies.service.PersonService;
import com.erudio.SBJP_Studies.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all People",
        description = "Finds all People",
        tags = {"People"},
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                    )
                }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find a Person",
            description = "Finds a specific person by your ID",
            tags = {"Person"},
            responses = {
                    @ApiResponse(
                        description = "Success",
                        responseCode = "200",
                        content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new person by passing in a JSON, XML or YML representation of the person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PostMapping(value = "/v2",
        consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Adds a new Person V2 version",
            description = "Adds a new person by passing in a JSON, XML or YML representation of the person in API V2 version.",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
        return personService.createV2(person);
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
        produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a person's information",
            description = "Updates a person's information by passing in a JSON, XML or YML representation of the updated person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a specific person by their ID",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.erudio.SBJP_Studies.integrationtests.controllers.withyaml;

import com.erudio.SBJP_Studies.config.TestConfig;
import com.erudio.SBJP_Studies.integrationtests.controllers.withyaml.mapper.YAMLMapper;
import com.erudio.SBJP_Studies.integrationtests.dto.PersonDTO;
import com.erudio.SBJP_Studies.integrationtests.dto.wrapper.xml.PagedModelPerson;
import com.erudio.SBJP_Studies.integrationtests.testcontainers.AbstractIntegrationTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerYamlTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static YAMLMapper objectMapper;
    private static PersonDTO person;

    @BeforeAll
    static void setUp() {
        objectMapper = new YAMLMapper();
        person = new PersonDTO();
    }

    @Test
    @Order(1)
    void createTest() throws JsonProcessingException {
        mockPerson();

        specification = new RequestSpecBuilder()
                .addHeader(TestConfig.HEADER_PARAM_ORIGIN, TestConfig.ORIGIN_SBPJ)
                .setBasePath("/api/person/v1")
                .setPort(TestConfig.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(person, objectMapper)
                .when()
                .post()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Torvalds", createdPerson.getLastName());
        assertEquals("Helsink - Finland", createdPerson.getAddress());
        assertEquals("Binary", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }

    @Test
    @Order(2)
    void updateTest() throws JsonProcessingException {
        person.setLastName("Benedit Torvalds");

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .body(person, objectMapper)
                .when()
                .put()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Benedit Torvalds", createdPerson.getLastName());
        assertEquals("Helsink - Finland", createdPerson.getAddress());
        assertEquals("Binary", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }

    @Test
    @Order(value = 3)
    void findByIdTest() throws JsonProcessingException {

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Benedit Torvalds", createdPerson.getLastName());
        assertEquals("Helsink - Finland", createdPerson.getAddress());
        assertEquals("Binary", createdPerson.getGender());
        assertTrue(createdPerson.getEnabled());
    }

    @Test
    @Order(value = 4)
    void disabledTest() throws JsonProcessingException {

        var createdPerson = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .pathParam("id", person.getId())
                .when()
                .patch("{id}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PersonDTO.class, objectMapper);

        person = createdPerson;

        assertNotNull(createdPerson.getId());
        assertTrue(createdPerson.getId() > 0);

        assertEquals("Linus", createdPerson.getFirstName());
        assertEquals("Benedit Torvalds", createdPerson.getLastName());
        assertEquals("Helsink - Finland", createdPerson.getAddress());
        assertEquals("Binary", createdPerson.getGender());
        assertFalse(createdPerson.getEnabled());
    }

    @Test
    @Order(value = 5)
    void deleteTest() throws JsonProcessingException {

        given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204)
                .extract()
                .body()
                .asString();
    }

    @Test
    @Order(value = 6)
    void findAllTest() throws JsonProcessingException {

        var response = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .queryParams("page", 3, "size", 12, "direction", "asc")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PagedModelPerson.class, objectMapper);

        List<PersonDTO> people = response.getContent();

        PersonDTO personOne = people.getFirst();
        person = personOne;

        assertNotNull(personOne.getId());
        assertTrue(personOne.getId() > 0);

        assertEquals("Andrea", personOne.getFirstName());
        assertEquals("Siverns", personOne.getLastName());
        assertEquals("PO Box 4880", personOne.getAddress());
        assertEquals("Male", personOne.getGender());
        assertTrue(personOne.getEnabled());

        PersonDTO personFour = people.get(4);
        person = personFour;

        assertNotNull(personFour.getId());
        assertTrue(personFour.getId() > 0);

        assertEquals("Angelle", personFour.getFirstName());
        assertEquals("O'Nion", personFour.getLastName());
        assertEquals("Apt 1896", personFour.getAddress());
        assertEquals("Female", personFour.getGender());
        assertTrue(personFour.getEnabled());
    }

    @Test
    @Order(value = 7)
    void findByNameTest() throws JsonProcessingException {

        var response = given().config(
                        RestAssuredConfig.config()
                                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs(
                                        MediaType.APPLICATION_YAML_VALUE,
                                        ContentType.TEXT))
                ).spec(specification)
                .accept(MediaType.APPLICATION_YAML_VALUE)
                .pathParam("firstName", "and")
                .queryParams("page", 0, "size", 12, "direction", "asc")
                .when()
                .get("findPeopleByName/{firstName}")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_YAML_VALUE)
                .extract()
                .body()
                .as(PagedModelPerson.class, objectMapper);

        List<PersonDTO> people = response.getContent();

        PersonDTO personOne = people.getFirst();
        person = personOne;

        assertNotNull(personOne.getId());
        assertTrue(personOne.getId() > 0);

        assertEquals("Alexandros", personOne.getFirstName());
        assertEquals("Dunridge", personOne.getLastName());
        assertEquals("Suite 22", personOne.getAddress());
        assertEquals("Male", personOne.getGender());
        assertFalse(personOne.getEnabled());

        PersonDTO personFour = people.get(4);
        person = personFour;

        assertNotNull(personFour.getId());
        assertTrue(personFour.getId() > 0);

        assertEquals("Candie", personFour.getFirstName());
        assertEquals("Pasby", personFour.getLastName());
        assertEquals("Suite 26", personFour.getAddress());
        assertEquals("Female", personFour.getGender());
        assertTrue(personFour.getEnabled());
    }

    private void mockPerson() {
        person.setFirstName("Linus");
        person.setLastName("Torvalds");
        person.setAddress("Helsink - Finland");
        person.setGender("Binary");
        person.setEnabled(true);
    }
}

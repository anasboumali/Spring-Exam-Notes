package spring.exam.web.rest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import spring.exam.web.rest.models.Person;

import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    public PersonController personController;

    @LocalServerPort
    Integer port;

    String baseUrl;

    public RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    void testGet() throws Exception {

        ResponseEntity<Person> person = restTemplate.getForEntity(baseUrl + "/{age}", Person.class, Map.of("age", 10));

        assertThat(person.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(person.getHeaders().getContentType().toString()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
        assertThat(person.getBody().getAge()).isEqualTo(10);
        assertThat(person.getBody().getFirstName()).isEqualTo("anas");
    }

    @Test
    void testPost() {
        Person person = new Person("anas", "boumali", 10);
        ResponseEntity<Person> result = restTemplate.postForEntity(baseUrl, person, Person.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType().toString()).isEqualTo(MediaType.APPLICATION_JSON_VALUE);
    }

    @Test
    void testPut() {
        Person person = new Person("anas", "boumali", 10);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_LANGUAGE, Locale.US.toString());

        HttpEntity<Person> httpEntity = new HttpEntity<>(person, headers);

        ResponseEntity<Person> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, httpEntity, Person.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentLanguage().getLanguage()).isEqualTo(Locale.US.getLanguage());
    }


    @Test
    void testDelete() {
        //restTemplate.delete return void , not useful !
        HttpStatus result = restTemplate.execute(baseUrl + "/{id}", HttpMethod.DELETE, request -> {
                    return;
                },
                response -> response.getStatusCode()

                , Map.of("id", 10));

        assertThat(result).isEqualTo(HttpStatus.OK);
    }


}

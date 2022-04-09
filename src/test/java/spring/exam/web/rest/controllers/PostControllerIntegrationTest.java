package spring.exam.web.rest.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import spring.exam.web.rest.models.Person;
import spring.exam.web.rest.models.Post;
import spring.exam.web.rest.security.JwtTokenUtil;
import spring.exam.web.rest.security.Roles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    private final String baseUrl = "http://localhost:";

    @Test
    void testAuthorizedPost() {
        Post newPost = new Post("my post !");

        HttpHeaders headers = new HttpHeaders();
        String token = JwtTokenUtil.generateAccessToken("fatiha");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<Post> request = new HttpEntity<>(newPost, headers);

        ResponseEntity<Post> result = restTemplate.postForEntity(baseUrl + port + "/posts", request, Post.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testUnAuthorizedPost() {
        Post newPost = new Post("my post !");

        HttpHeaders headers = new HttpHeaders();
        String token = JwtTokenUtil.generateAccessToken("anas");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<Post> request = new HttpEntity<>(newPost, headers);

        ResponseEntity<Post> result = restTemplate.postForEntity(baseUrl + port + "/posts", request, Post.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void testUnAuthenticatedPost() {
        Post newPost = new Post("my post !");

        HttpHeaders headers = new HttpHeaders();
        String token = JwtTokenUtil.generateAccessToken("unkown");
        //headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<Post> request = new HttpEntity<>(newPost, headers);

        ResponseEntity<Post> result = restTemplate.postForEntity(baseUrl + port + "/posts", request, Post.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    //TODO : test allowedRole here

}

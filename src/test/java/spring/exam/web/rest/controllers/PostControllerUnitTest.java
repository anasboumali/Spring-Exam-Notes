package spring.exam.web.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import spring.exam.web.rest.models.Post;
import spring.exam.web.rest.security.Roles;
import spring.exam.web.rest.services.PostService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
public class PostControllerUnitTest {

    @Autowired
    public PostController postController;

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "anas", password = "anas", roles = Roles.USER)
    void testGet() throws Exception {

        Post expectedPost = new Post(1, "my post !");
        when(postService.get(1)).thenReturn(Optional.ofNullable(expectedPost));
        mockMvc.perform(get("/posts").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedPost.getId()))
                .andExpect(jsonPath("$.content").value(expectedPost.getContent()));
    }

    @Test
    @WithMockUser(username = "fatiha", password = "fatiha", roles = Roles.ADMIN)
    void testPost() throws Exception {
        Post newPost = new Post(1, "my post !");
        when(postService.create(any(Post.class))).thenReturn(newPost);

        mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newPost.getId()))
                .andExpect(jsonPath("$.content").value(newPost.getContent()));
    }


    @Test
    @WithMockUser(username = "fatiha", password = "fatiha", roles = Roles.ADMIN)
    void testDelete_With_Authorized() throws Exception {
        mockMvc.perform(delete("/posts").param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "anas", password = "anas", roles = Roles.USER)
    void testDelete_With_Unauthorized() throws Exception {
        mockMvc.perform(delete("/posts").param("id", "1"))
                .andExpect(status().isUnauthorized());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

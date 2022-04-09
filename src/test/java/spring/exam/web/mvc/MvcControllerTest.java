package spring.exam.web.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring.exam.web.mvc.controllers.MvcController;

import javax.servlet.http.Cookie;

@WebMvcTest
public class MvcControllerTest {

    @Autowired
    public MvcController mvcController;

    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    void testModel() throws Exception {
        mockMvc.perform(get("/testModel").param("title", "Exam"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("person", allOf(
                        hasProperty("firstName", is("anas")),
                        hasProperty("lastName", is("boumali")),
                        hasProperty("age", is(29)
                        ))))
                .andExpect(model().attribute("title", "Exam"));
    }

    @Test
    void testServlet() throws Exception {
        mockMvc.perform(get("/testServlet").cookie(new Cookie("anas-cookie", "yeah")))
                .andExpect(status().isOk())
                .andExpect(header().string("anas-header", "hhhh"))
                .andExpect(cookie().value("anas-cookie", "yeah"))
                .andExpect(content().string(containsString("getLocale")))
                .andExpect(header().string("Content-Language", "en-US"));
    }


    @Test
    void testCookieAndHeader() throws Exception {
        mockMvc.perform(get("/testHeaderAndCookie").header("anas-header", "hhhh").cookie(new Cookie("anas-cookie", "yeah")))
                .andExpect(status().isOk())
                .andExpect(header().string("anas-header", "hhhh"))
                .andExpect(cookie().value("anas-cookie", "yeah"));
    }


    @Test
    void testModelAttribute_WithoutErrors() throws Exception {
        mockMvc.perform(post("/testModelAttribute")
                .param("firstName", "anass")
                .param("lastName", "boumali")
                .param("age", "10"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", allOf(
                        hasProperty("firstName", is("anass")),
                        hasProperty("lastName", is("boumali")),
                        hasProperty("age", is(10))))


                )
                .andExpect(model().hasNoErrors())
                .andExpect(model().attribute("msg", "Welcome"));
    }

    @Test
    void testModelAttribute_With_Errors() throws Exception {
        mockMvc.perform(post("/testModelAttribute")
                .param("firstName", "anas")
                .param("lastName", "")
                .param("age", "-1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", allOf(
                        hasProperty("firstName", is("anas")),
                        hasProperty("lastName", is("")),
                        hasProperty("age", is(-1))))
                )
                .andExpect(model().errorCount(4));
    }

    @Test
    void testControllerAdvice() throws Exception {
        mockMvc.perform(get("/testControllerAdvice"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("notfound"))
                .andExpect(model().attribute("message", "hihihi"));
    }

}

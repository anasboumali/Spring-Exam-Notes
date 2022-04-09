package spring.exam.web.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring.exam.web.mvc.controllers.MvcController;
import spring.exam.web.mvc.controllers.OrderController;
import spring.exam.web.mvc.models.Cart;

import javax.servlet.http.Cookie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class OrderControllerTest {

    @Autowired
    public OrderController orderController;

    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        /*
         * here we have configured mockMvc , you can use @AutoConfigureMockMvc to let spring configure it for you
         * */
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testOrder() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();

        mockMvc.perform(get("/order/init").param("product", "nokia").session(mockHttpSession))
                .andExpect(status().isOk());

        assertThat(((Cart) mockHttpSession.getAttribute("cart")).product).isEqualTo("nokia");

        mockMvc.perform(get("/order/end").session(mockHttpSession))
                .andExpect(status().isOk());

        assertThat(mockHttpSession.getAttribute("cart")).isNull();
    }
}

@Bean
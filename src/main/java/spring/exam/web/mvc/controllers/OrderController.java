package spring.exam.web.mvc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import spring.exam.web.mvc.models.Cart;
import spring.exam.web.mvc.models.Person;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/*test SessionAttributes*/
@Controller
@RequestMapping("order")
@SessionAttributes("cart")
public class OrderController {

    /*the HttpServletResponse should exist otherwise a thymeleaf exception will be thrown*/
    @GetMapping("init")
    public void init(@RequestParam String product, @ModelAttribute("cart") Cart cart, HttpServletResponse servletResponse) {
        cart.product = product;
    }

    @GetMapping("end")
    public void end(@ModelAttribute("cart") Cart cart, HttpServletResponse servletResponse, SessionStatus status) {
        status.setComplete();
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart("");
    }

}

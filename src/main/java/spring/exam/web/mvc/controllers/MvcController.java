package spring.exam.web.mvc.controllers;


import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
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

@Controller
public class MvcController {

    @GetMapping("/testModel")
    public String testModel(@RequestParam String title, WebRequest webRequest, Model model) {

        Person person = new Person("anas", "boumali", 29);

        model.addAttribute("contextPath", webRequest.getContextPath());
//        model.addAttribute("userPrincipal", webRequest.getUserPrincipal().getName());
        model.addAttribute("locale", webRequest.getLocale());


        model.addAttribute("person", person);
        model.addAttribute("title", title);

        return "hello";
    }


    @GetMapping("/testServlet")
    public void testServletRequestResponse(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
        try (PrintWriter printWriter = servletResponse.getWriter()) {

            printWriter.printf("getLocale %s\n", servletRequest.getLocale());
            printWriter.printf("getDispatcherType %s\n", servletRequest.getDispatcherType());
            printWriter.printf("getLocalAddr %s\n", servletRequest.getLocalAddr());
            printWriter.printf("getLocalPort %s\n", servletRequest.getLocalPort());
            printWriter.printf("getProtocol %s\n", servletRequest.getProtocol());
            printWriter.printf("getServerName %s\n", servletRequest.getServerName());
            printWriter.printf("getServerPort %s\n", servletRequest.getServerPort());
            printWriter.printf("getRemoteAddr %s\n", servletRequest.getRemoteAddr());
            printWriter.printf("getRemoteHost %s\n", servletRequest.getRemoteHost());
            printWriter.printf("getRemotePort %s\n", servletRequest.getRemotePort());

            //HttpServletRequest
            printWriter.printf("getCookies %s\n", Arrays.stream(servletRequest.getCookies()).map(Cookie::getName).collect(Collectors.toList()));
            printWriter.printf("getHttpServletMapping %s\n", servletRequest.getHttpServletMapping());
            printWriter.printf("getQueryString %s\n", servletRequest.getQueryString());
            printWriter.printf("getSession %s\n", servletRequest.getSession());
            printWriter.printf("getRequestURI %s\n", servletRequest.getRequestURI());
            printWriter.printf("getAuthType %s\n", servletRequest.getAuthType());

            servletResponse.setHeader("anas-header", "hhhh");
            servletResponse.setLocale(Locale.US);
            servletResponse.addCookie(new Cookie("anas-cookie", "yeah"));
        }

    }

    @GetMapping("/testHeaderAndCookie")
    void testHeaderAndCookie(@CookieValue("anas-cookie") String anasCookie, @RequestHeader("anas-header") String anasHeader, HttpServletResponse servletResponse) {
        servletResponse.addHeader("anas-header", anasHeader);
        servletResponse.addCookie(new Cookie("anas-cookie", anasCookie));
    }

    @PostMapping("/testModelAttribute")
    String testModelAttribute(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {
        model.addAttribute(person);

        if (result.hasErrors())
            return "error";

        return "hello";
    }


    @GetMapping("/testControllerAdvice")
    void testControllerAdvice() throws NotFoundException {
        throw new NotFoundException("hihihi");
    }

    static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        /*this attribute will be added at all request in this controller ,
         on all requests if the controller annotated with @ControllerAdvice*/
        model.addAttribute("msg", "Welcome");
    }


}

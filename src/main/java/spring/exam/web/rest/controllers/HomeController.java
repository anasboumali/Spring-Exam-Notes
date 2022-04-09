package spring.exam.web.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import spring.exam.web.rest.models.Person;

import java.util.Locale;


@RestController
@RequestMapping("home")
public class HomeController {

    @GetMapping("anonymous")
    public ResponseEntity<String> anonymous() {

        return ResponseEntity.ok("hello anonymous");
    }

    @GetMapping("guest")
    public ResponseEntity<String> guest() {
        return ResponseEntity.ok("guest");
    }


}




















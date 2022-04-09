package spring.exam.web.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.exam.web.rest.models.Person;

import java.security.Principal;
import java.util.Locale;


@RestController
@RequestMapping("person")
public class PersonController {

    @GetMapping("{age}")
    public ResponseEntity<Person> get(@PathVariable Integer age , Principal principal) {
        return ResponseEntity.ok(new Person("anas", "boumali", age));
    }

    @PostMapping
    public ResponseEntity<Person> post(@RequestBody Person person) {
        return ResponseEntity.ok(person);
    }

    @PutMapping
    public ResponseEntity<Person> put(@RequestBody Person person) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLanguage(Locale.US);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

}

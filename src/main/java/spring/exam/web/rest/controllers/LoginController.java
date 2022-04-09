package spring.exam.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.exam.web.rest.security.Credentials;
import spring.exam.web.rest.security.JwtTokenFilter;
import spring.exam.web.rest.security.JwtTokenUtil;


@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("login")
    public ResponseEntity login(@RequestBody Credentials credentials) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );

            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, JwtTokenUtil.generateAccessToken(user.getUsername())).build();

        } catch (BadCredentialsException ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
        }

    }

}

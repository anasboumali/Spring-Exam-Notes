package spring.exam.web.rest.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Roles implements GrantedAuthority {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private String authority;
}

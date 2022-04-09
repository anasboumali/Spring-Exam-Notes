package spring.exam.web.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.exam.web.rest.security.JwtTokenFilter;
import spring.exam.web.rest.security.Roles;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /*
         * this way is not that the path to the endpoints
         * is hardcoded as /actuator/**.
         * */
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests().anyRequest().permitAll();

        http.authorizeRequests()
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/home/anonymous").permitAll()
                .mvcMatchers("/home/guest").permitAll()
                .mvcMatchers("/login").permitAll()

//                .mvcMatchers(HttpMethod.GET, "/posts*").permitAll()
//                .mvcMatchers(HttpMethod.POST, "/posts").permitAll()
//                .mvcMatchers(HttpMethod.DELETE, "/posts*").permitAll()

                .mvcMatchers(HttpMethod.GET, "/posts*").hasAnyRole(Roles.USER, Roles.ADMIN)
                .mvcMatchers(HttpMethod.POST, "/posts").hasRole(Roles.ADMIN)
                .mvcMatchers(HttpMethod.DELETE, "/posts*").hasRole(Roles.ADMIN)
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /*
     * another way to configure in memory auth manager
     *
     * */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("anas").password("anas").roles(Roles.USER)
//                .and()
//                .withUser("fatiha").password("fatiha").roles(Roles.USER, Roles.ADMIN);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails anas = User.withUsername("anas").password(passwordEncoder().encode("anas"))
                .roles(Roles.USER).build();

        UserDetails fatiha = User.withUsername("fatiha").password(passwordEncoder().encode("fatiha"))
                .roles(Roles.USER, Roles.ADMIN).build();

        return new InMemoryUserDetailsManager(anas, fatiha);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


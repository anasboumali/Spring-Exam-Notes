package spring.exam.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {

    /*
     * Would be injected by specifying variables environment to : spring_profiles_active=test
     * or VM Options to : -Dspring.profiles.active=test
     *
     * */
    @Profile("test")
    @Bean
    Valuable getJewelry() {
        return new Jewelry();
    }

    @Profile("prod")
    @Bean
    Valuable getDiamond() {
        return new Diamond();
    }
}

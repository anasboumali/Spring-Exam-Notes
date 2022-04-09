package spring.exam.profiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProfilesApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ProfilesApplication.class, args);
        Store store = context.getBean(Store.class);
        store.getValuable().Hi();
        store.printUsername();

    }

}

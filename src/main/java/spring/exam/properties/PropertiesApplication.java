package spring.exam.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.exam.profiles.Store;

@SpringBootApplication
public class PropertiesApplication {
    private static final Logger log = LoggerFactory.getLogger(Store.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(PropertiesApplication.class, args);
        Config config = context.getBean(Config.class);

        log.info(config.getUsername());
        log.info(config.getPassword());
        log.info(config.getDuration().toString());
        log.info(config.getFileSize().toGigabytes() + " ");
        log.info(config.getServer().toString());
        log.info(config.getHolidays().toString());

    }

}

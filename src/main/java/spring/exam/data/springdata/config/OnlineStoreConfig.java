package spring.exam.data.springdata.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spring.exam.data.springdata.repositories.AddressRepository;


@ComponentScan(basePackages = {"spring.exam.data.springdata"})
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "spring.exam.data.springdata.repositories")
@EntityScan("spring.exam.data.springdata.entities")
public class OnlineStoreConfig {

}

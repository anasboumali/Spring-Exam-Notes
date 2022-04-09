package spring.exam.profiles;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Store {
    private static final Logger log = LoggerFactory.getLogger(Store.class);


    private Valuable valuable;

    @Value("${exam.username}")
    private String username;

    @Autowired
    private Environment environment;

    public Store(Valuable valuable) {
        this.valuable = valuable;
    }

    Valuable getValuable() {
        return valuable;
    }

    public void printUsername()
    {
        log.info("username : " + username);
    }
}

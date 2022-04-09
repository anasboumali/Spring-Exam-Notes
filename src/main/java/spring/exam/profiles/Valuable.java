package spring.exam.profiles;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Valuable {
    Logger log = LoggerFactory.getLogger(Valuable.class);

    void Hi();
}

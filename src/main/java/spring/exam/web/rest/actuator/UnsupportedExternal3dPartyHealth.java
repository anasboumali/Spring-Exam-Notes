package spring.exam.web.rest.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.Random;

public class UnsupportedExternal3dPartyHealth implements HealthIndicator {

    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {


        if (new Random().nextInt() % 10 >= 5)
            return Health.up().build();
        else
            return Health.down().build();


    }
}

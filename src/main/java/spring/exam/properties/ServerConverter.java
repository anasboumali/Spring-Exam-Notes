package spring.exam.properties;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ServerConverter implements Converter<String, Server> {

    @Override
    public Server convert(String source) {
        String url = source.split("[,;]")[0];
        int port = Integer.valueOf(source.split("[,;]")[1]);
        return new Server(url, port);
    }
}

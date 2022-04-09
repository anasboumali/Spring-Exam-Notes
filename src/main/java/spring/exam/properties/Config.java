package spring.exam.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/*
 * You should add setter in order to get props
 *
 * */
@Profile("test")
@Configuration
@ConfigurationProperties(prefix = "exam")
public class Config {

    private String username;
    private String password;
    private Duration duration;
    private DataSize fileSize;
    private Server server;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Value("${exam.holidays}")
    private List<LocalDate> holidays;

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<LocalDate> holidays) {
        this.holidays = holidays;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public DataSize getFileSize() {
        return fileSize;
    }

    public void setFileSize(DataSize fileSize) {
        this.fileSize = fileSize;
    }
}

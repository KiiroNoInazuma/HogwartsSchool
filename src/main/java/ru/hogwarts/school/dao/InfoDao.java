package ru.hogwarts.school.dao;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.hogwarts.school.model.Info;

@Configuration
public class InfoDao {
    @Value("${app.env}")
    private String prof;
    @Profile("test")
    @Bean
    public Info info() {
        return new Info("hogwarts-school", "0.0.1", prof);
    }
    @Profile("dev")
    @Bean
    public Info infoTest() {
        return new Info("hogwarts-school", "0.0.1", prof);
    }
}

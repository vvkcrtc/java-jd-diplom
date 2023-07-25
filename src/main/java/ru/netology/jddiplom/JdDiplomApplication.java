package ru.netology.jddiplom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.netology.jddiplom.configuration.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)

public class JdDiplomApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdDiplomApplication.class, args);
    }

}

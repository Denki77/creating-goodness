

package ru.leadersofdigital.dobro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class DobroApplication {

    public static void main(String[] args) {
        SpringApplication.run(DobroApplication.class, args);
    }

}
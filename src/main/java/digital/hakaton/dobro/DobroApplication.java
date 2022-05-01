

package digital.hakaton.dobro;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class DobroApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DobroApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DobroApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3:00"));
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("For geekBrains application API")
                                .version("1.0")
                                .description("Hakaton project")
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:8080")
                                        .description("Ready for use server")
                        )
                )
                .paths(
                        new Paths()
                                .addPathItem(
                                        "Api",
                                        new PathItem()
                                                .summary("What is it?")
                                )
                );
    }
}
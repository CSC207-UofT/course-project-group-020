package Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/get-user-data").allowedOrigins("http://localhost:3000");
                registry.addMapping("/verify-user").allowedOrigins("http://localhost:3000");
                registry.addMapping("/create-user").allowedOrigins("http://localhost:3000");
                registry.addMapping("/create-entry").allowedOrigins("http://localhost:3000");
                registry.addMapping("/delete-entry").allowedOrigins("http://localhost:3000");
                registry.addMapping("/update-entry").allowedOrigins("http://localhost:3000");
                registry.addMapping("/delete-user").allowedOrigins("http://localhost:3000");
            }
        };
    }

}
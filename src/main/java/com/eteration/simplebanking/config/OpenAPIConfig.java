package com.eteration.simplebanking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("API Documentation")
                .version("1.0")
                .description("Simple Banking Open API");

        return new OpenAPI()
                .info(info);
    }
}
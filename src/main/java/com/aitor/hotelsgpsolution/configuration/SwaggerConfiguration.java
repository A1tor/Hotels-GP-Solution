package com.aitor.hotelsgpsolution.configuration;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Management API")
                        .version("1.0")
                        .description("Set of CRUD methods to manage list of hotels"))
                .externalDocs(new ExternalDocumentation()
                        .description("Docs")
                        .url("https://example.com/docs"));
    }
}

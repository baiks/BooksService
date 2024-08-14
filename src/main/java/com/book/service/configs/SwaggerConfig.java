package com.book.service.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "basicAuth";
        final String apiTitle = "Books API";
        final String description = "You are tasked with developing a simple Spring Boot application that manages a collection of books";
        String apiVersion = "1.0.0";
        Contact contact = new Contact();
        contact.setName("Paul");
        contact.setEmail("paulkabaiku023@gmail.com");
        contact.setUrl("paulkabaiku.website");
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("basic")
                                ))
                .info(new Info().title(apiTitle).version(apiVersion).description(description).contact(contact)).
                addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("http://localhost:8078")
                        .description("Development server"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("https://api.example.com")
                        .description("Production server"));
    }
}

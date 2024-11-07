package com.example.book2.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Library",
                description = "Managing books and readers", version = "2.6.0",
                contact = @Contact (
                        name = "Anastasia",
                        email = "n.bogocharova@gmail.com"
                )
        )
)
public class OpenApiConfig {

}

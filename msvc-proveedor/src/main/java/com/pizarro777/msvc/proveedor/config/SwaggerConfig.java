package com.pizarro777.msvc.proveedor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact = new Contact();
        contact.setName("Abraham Neira");
        contact.setEmail("abrahamneira1@gmail.com");
        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Proveedor")
                        .version("1.0.0")
                        .description("Este es el microservicio de Proveedor, con el puedes realizar todas las consultas" +
                                " CRUD que necesites")
                        .contact(contact)
                        .summary("Esto es una api dentro de un proyecto de MSVC")
                );
    }
}

package com.pizarro777.msvc.boletas.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API - MSVC - Boletas")
                        .version("1.0.0")
                        .description("Este es el microservicio de Boletas, con el puedes realizar todas las consultas" +
                                " CRUD que necesites")
                        .summary("Esto es una api dentro de un proyecto de MSVC")
        );
    }
}

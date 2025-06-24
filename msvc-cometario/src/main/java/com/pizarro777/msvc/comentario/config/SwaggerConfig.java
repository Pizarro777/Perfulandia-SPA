package com.pizarro777.msvc.comentario.config;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Comentario")
                        .description("Esta es la api dedicada al msvc de comentario")
                        .version("1.0.0")
                );
    }
}

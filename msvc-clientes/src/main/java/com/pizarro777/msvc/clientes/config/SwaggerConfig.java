package com.pizarro777.msvc.clientes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Clientes")
                        .description("Esta es la api dedicada al msvc de Clientes")
                        .version("1.0.0"));
    }
}

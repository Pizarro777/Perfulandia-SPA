package com.pizarro777.msvc.sucursales.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;


    @Configuration
    public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenApi() {
            return new OpenAPI()
                    .info(new Info()
                            .title("API - Microservicio - Sucursal")
                            .description("Esta es la api dedicada al msvc de Sucursal")
                            .version("1.0.0"));
        }
    }


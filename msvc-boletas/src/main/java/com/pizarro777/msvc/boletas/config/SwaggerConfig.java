package com.pizarro777.msvc.boletas.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApo(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Boletas")
                        .description("Esta es la api ")
                        .version()
                );
    }
}

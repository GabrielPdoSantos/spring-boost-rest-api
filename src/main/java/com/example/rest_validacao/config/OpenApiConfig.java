package com.example.rest_validacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.awt.SystemColor.info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI OpenApiConfig(){
        return new OpenAPI()
                .info(new Info()
                .title("API de tarefas do Capacita")
                .version("1.0")
                .description("CRUD de tarefas relacionadas a API publica do capacita")
                );
    }

}

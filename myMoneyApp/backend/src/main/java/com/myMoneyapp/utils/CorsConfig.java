package com.myMoneyapp.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Mapeie o endpoint do seu aplicativo aqui
                .allowedOrigins("*") // Permita solicitações de qualquer origem
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permita os métodos HTTP necessários
                .allowedHeaders("*"); // Permita todos os cabeçalhos
    }
}
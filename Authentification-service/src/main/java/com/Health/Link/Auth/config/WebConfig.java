package com.Health.Link.Auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Autorise les requêtes depuis React
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // Autorise les méthodes HTTP
                .allowedHeaders("*")  // Autorise tous les en-têtes
                .allowCredentials(true);  // Permet l'envoi des cookies (par exemple pour JWT)
    }
}

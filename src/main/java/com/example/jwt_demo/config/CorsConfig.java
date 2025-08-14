package com.example.jwt_demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        //Permitir todos los origene
        configuration.setAllowedOrigins(List.of("*"));

        // Metodos HHTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "OPTIONS"));

        // Encabezados permitidos
        configuration.setAllowedHeaders(Arrays.asList(
            "Origin", "Content-Type", "Accept", "Authorization", 
            "X-Requested-With", "Cache-Control", "Pragma"
        ));

        // Exponer encabezados
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization", "Content-Type"
        ));

        // Permitir credenciales
        configuration.setAllowCredentials(true);

        // tiempo
        configuration.setMaxAge(3600L); // 1 hora

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

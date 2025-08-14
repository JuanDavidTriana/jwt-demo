package com.example.jwt_demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public CommandLineRunner databaseInitializer() {
        return args -> {
            System.out.println("🚀 Inicializando base de datos...");
            System.out.println("📊 URL: " + databaseUrl);
            System.out.println("👤 Usuario: " + databaseUsername);
            System.out.println("✅ Base de datos configurada correctamente");
        };
    }

}

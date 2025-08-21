package com.example.jwt_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Panel de Administrador - Acceso solo para ADMIN");
    }

    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("Lista de todos los usuarios - Solo ADMIN puede ver esto");
    }

     @GetMapping("/system-stats")
    public ResponseEntity<String> getSystemStats() {
        return ResponseEntity.ok("Estadísticas del sistema - Solo ADMIN puede ver esto");
    }
    
}

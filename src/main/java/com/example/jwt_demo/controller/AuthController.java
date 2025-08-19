package com.example.jwt_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_demo.dto.AuthResponse;
import com.example.jwt_demo.dto.ErrorResponse;
import com.example.jwt_demo.dto.LoginRequest;
import com.example.jwt_demo.dto.RegistroRequest;
import com.example.jwt_demo.service.AuthService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroRequest request) {
    try {
        AuthResponse response = authService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } catch (Exception  e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("Datos de registro inválidos", "VALIDATION_ERROR"));
    } 

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                .body(new ErrorResponse("Datos de Login inválidos", "VALIDATION_ERROR"));
        }
    }
    
    
    
    
}

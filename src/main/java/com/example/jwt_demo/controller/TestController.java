package com.example.jwt_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt_demo.repository.UsuarioRepository;


@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok("Endpoint protegido. Usuario autenticado: " + username);

    }
}




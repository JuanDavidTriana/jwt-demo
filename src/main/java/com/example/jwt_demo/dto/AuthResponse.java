package com.example.jwt_demo.dto;

import java.util.Set;

import com.example.jwt_demo.model.Role;

public class AuthResponse {
    
    private String token;
    private String tipoToken = "Bearer";
    private String username;
    private String email;
    private String nombreCompleto;
    private Set<Role> roles;

    public AuthResponse(String token, String username, String email, String nombreCompleto) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.roles = Set.of(Role.ROLE_USER); // Default role
    }

    public AuthResponse(String token, String username, String email, String nombreCompleto, Set<Role> roles) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}

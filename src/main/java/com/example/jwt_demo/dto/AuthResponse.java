package com.example.jwt_demo.dto;

public class AuthResponse {
    
    private String token;
    private String tipoToken = "Bearer";
    private String username;
    private String email;
    private String nombreCompleto;

    public AuthResponse(String token, String username, String email, String nombreCompleto) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
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


}

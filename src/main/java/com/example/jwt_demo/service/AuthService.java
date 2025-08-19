package com.example.jwt_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jwt_demo.dto.AuthResponse;
import com.example.jwt_demo.dto.LoginRequest;
import com.example.jwt_demo.dto.RegistroRequest;
import com.example.jwt_demo.model.Usuario;
import com.example.jwt_demo.repository.UsuarioRepository;

@Service
public class AuthService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    //Crear nuevo usuario
    public AuthResponse registrar(RegistroRequest request){

        if(usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptar la contraseña
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setActivo(true);

        usuarioRepository.save(usuario);

        // Generar token
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                java.util.Collections.singletonList(new org.springframework.security.core.authority.SimpleGrantedAuthority("USER"))
               );

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, usuario.getUsername(), usuario.getEmail(), usuario.getNombreCompleto());

    }

    public AuthResponse login(LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Usuario usuario = usuarioRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, usuario.getUsername(), usuario.getEmail(), usuario.getNombreCompleto());

    }

}

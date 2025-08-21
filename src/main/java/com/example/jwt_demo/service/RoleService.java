package com.example.jwt_demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.jwt_demo.model.Role;
import com.example.jwt_demo.model.RoleEntity;
import com.example.jwt_demo.repository.RoleRepository;

@Service
public class RoleService implements CommandLineRunner{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
    }

    private void initializeRoles(){
        for(Role role: Role.values()){
            if(!roleRepository.existsByName(role)){
                RoleEntity roleEntity = new RoleEntity(role, getRoleDescription(role));
                roleRepository.save(roleEntity);
                System.out.println("Rol creado: " + role.name());
            }
        }
    }

    private String getRoleDescription(Role role) {
        switch (role) {
            case ROLE_USER:
                return "Usuario básico con acceso limitado";
            case ROLE_MODERATOR:
                return "Moderador con permisos de moderación";
            case ROLE_ADMIN:
                return "Administrador con todos los permisos";
            case ROLE_EDITOR:
                return "Editor con permisos de contenido";
            default:
                return "Sin descripción";
        }
    }

    public Set<RoleEntity> getRolesByNames(Set<Role> roleNames) {
        Set<RoleEntity> roles = new HashSet<>();
        for (Role roleName : roleNames) {
            roleRepository.findByName(roleName).ifPresent(roles::add);
        }
        return roles;
    }

    public RoleEntity getRoleByName(Role roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleName));
    }

    
    
}

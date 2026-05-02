package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.data.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Tu repositorio de JPA

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos al usuario en la base de datos de FarmaSalud
        com.cibertec.FarmaSalud.business.data.entity.Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Retornamos un objeto User de Spring Security con el password HASHEADO de la BD
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Aquí Spring recibe el hash $2a$10...
                .roles(String.valueOf(usuario.getRol()))
                .build();
    }
}
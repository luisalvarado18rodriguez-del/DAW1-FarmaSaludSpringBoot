package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import com.cibertec.FarmaSalud.business.data.repository.UsuarioRepository;
import com.cibertec.FarmaSalud.business.domain.enums.RolNombre;
import com.cibertec.FarmaSalud.business.domain.mapper.UsuarioMapper;
import com.cibertec.FarmaSalud.business.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public UsuarioResponseDto login(String username, String password) {
        // 1. Autenticar
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // 2. Buscar y transformar a DTO
        Usuario usuario = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toResponseDto(usuario);
    }


    @Override
    public List<UsuarioResponseDto> listarTodos() {
        return repo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }



    @Override
    public UsuarioResponseDto guardar(UsuarioRequestDto requestDto) {
        // 1. Convertimos el DTO a Entidad
        Usuario entidad = mapper.toEntity(requestDto);


        entidad.setRol(RolNombre.CLIENTE);

        //  ENCRIPTACIÓN: Hasheamos la contraseña para que el login funcione
        String passwordHashed = passwordEncoder.encode(entidad.getPassword());
        entidad.setPassword(passwordHashed);

        // Guardamos en MySQL
        Usuario guardado = repo.save(entidad);

        //  Devolvemos la respuesta (sin el password)
        return mapper.toResponseDto(guardado);
    }

    @Override
    public UsuarioResponseDto buscarPorId(Long id) {
        Usuario encontrado = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapper.toResponseDto(encontrado);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
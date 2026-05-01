package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import com.cibertec.FarmaSalud.business.data.repository.UsuarioRepository;
import com.cibertec.FarmaSalud.business.domain.enums.RolNombre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired private BCryptPasswordEncoder encoder;

    public UsuarioResponseDto registrar(UsuarioRequestDto dto) {
        Usuario user = new Usuario();
        user.setUsername(dto.getUsername());
        user.setNombres(dto.getNombres());
        user.setApellidos(dto.getApellidos());
        user.setRol(RolNombre.valueOf(dto.getRol().toUpperCase()));

        // ENCRIPTACIÓN: Aquí se genera el hash
        user.setPassword(encoder.encode(dto.getPassword()));

        Usuario guardado = repository.save(user);
        return mapearADto(guardado);
    }

    public UsuarioResponseDto login(String username, String rawPassword) {
        Usuario user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // MATCHES: Compara la clave plana con el hash de la DB
        if (encoder.matches(rawPassword, user.getPassword())) {
            return mapearADto(user);
        }
        throw new RuntimeException("Credenciales incorrectas");
    }

    private UsuarioResponseDto mapearADto(Usuario u) {
        UsuarioResponseDto res = new UsuarioResponseDto();
        res.setIdUsuario(u.getIdUsuario());
        res.setUsername(u.getUsername());
        res.setNombres(u.getNombres());
        res.setApellidos(u.getApellidos());
        res.setRol(u.getRol().name());
        return res;
    }
}

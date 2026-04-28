package com.cibertec.FarmaSalud.business.domain.service.impl;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import com.cibertec.FarmaSalud.business.data.entity.Usuario;
import com.cibertec.FarmaSalud.business.data.repository.UsuarioRepository;
import com.cibertec.FarmaSalud.business.domain.mapper.UsuarioMapper;
import com.cibertec.FarmaSalud.business.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private UsuarioMapper mapper;

    @Override
    public List<UsuarioResponseDto> listarTodos() {
        return repo.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDto guardar(UsuarioRequestDto requestDto) {
        // Convertimos el DTO a Entidad
        Usuario entidad = mapper.toEntity(requestDto);

        // NOTA: Por ahora guardamos el password en texto plano.
        // Más adelante aplicaremos BCrypt aquí mismo[cite: 117].
        Usuario guardado = repo.save(entidad);

        // Devolvemos la respuesta (que ya no incluye el password)
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
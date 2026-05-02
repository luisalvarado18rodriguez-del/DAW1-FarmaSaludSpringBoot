package com.cibertec.FarmaSalud.business.domain.service;

import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.usuario.UsuarioResponseDto;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDto login(String username, String password);
    List<UsuarioResponseDto> listarTodos();
    UsuarioResponseDto guardar(UsuarioRequestDto requestDto);
    UsuarioResponseDto buscarPorId(Long id);
    void eliminar(Long id);
}
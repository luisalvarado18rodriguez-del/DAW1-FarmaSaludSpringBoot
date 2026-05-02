package com.cibertec.FarmaSalud.business.domain.service;

import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.categoria.CategoriaResponseDto;
import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDto> listarTodas();
    CategoriaResponseDto guardar(CategoriaRequestDto requestDto);
    void eliminar(Integer id);
}
package com.cibertec.FarmaSalud.business.domain.service;

import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.medicamento.MedicamentoResponseDto;
import java.util.List;

public interface MedicamentoService {
    List<MedicamentoResponseDto> listarTodos();
    MedicamentoResponseDto guardar(MedicamentoRequestDto requestDto);
    MedicamentoResponseDto buscarPorId(Long id);
    void eliminar(Long id);

    //metodo actualizar que faltaba xdd
    MedicamentoResponseDto actualizar(Long id, MedicamentoRequestDto requestDto);
}
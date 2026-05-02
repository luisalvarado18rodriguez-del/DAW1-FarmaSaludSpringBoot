package com.cibertec.FarmaSalud.business.domain.service;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReservaService {
    // Cambia la firma del método
    ReservaResponseDto crearReserva(ReservaRequestDto requestDto, List<MultipartFile> archivos);
    List<ReservaResponseDto> listarTodas();
    List<ReservaResponseDto> listarPorUsuario(Long idUsuario);
}
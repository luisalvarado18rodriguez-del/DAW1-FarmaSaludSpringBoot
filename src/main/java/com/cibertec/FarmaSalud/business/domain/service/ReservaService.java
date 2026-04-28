package com.cibertec.FarmaSalud.business.domain.service;

import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaRequestDto;
import com.cibertec.FarmaSalud.business.api.dto.reserva.ReservaResponseDto;
import java.util.List;

public interface ReservaService {
    ReservaResponseDto crearReserva(ReservaRequestDto requestDto);
    List<ReservaResponseDto> listarTodas();
}
package com.cibertec.FarmaSalud.business.api.dto.reserva;

import com.cibertec.FarmaSalud.business.api.dto.detalleReserva.DetalleReservaRequestDto;
import lombok.Data;
import java.util.List;

@Data
public class ReservaRequestDto {
    private Long idUsuario;
    private List<DetalleReservaRequestDto> detalles;
}
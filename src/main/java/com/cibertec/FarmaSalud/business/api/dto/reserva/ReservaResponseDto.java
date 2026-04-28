package com.cibertec.FarmaSalud.business.api.dto.reserva;

import com.cibertec.FarmaSalud.business.api.dto.detalleReserva.DetalleReservaResponseDto;
import com.cibertec.FarmaSalud.business.domain.enums.EstadoReserva;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservaResponseDto {
    private Long idReserva;
    private LocalDateTime fechaCreacion;
    private EstadoReserva estado;
    private String username; // Para saber a quién pertenece sin mandar toda la entidad Usuario
    private List<DetalleReservaResponseDto> detalles;
}
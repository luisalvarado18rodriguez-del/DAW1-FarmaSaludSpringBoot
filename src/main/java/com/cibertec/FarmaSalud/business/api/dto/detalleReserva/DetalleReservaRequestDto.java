package com.cibertec.FarmaSalud.business.api.dto.detalleReserva;

import lombok.Data;

@Data
public class DetalleReservaRequestDto {
    private Long idMedicamento;
    private Integer cantidad;
    private String rutaReceta;
}
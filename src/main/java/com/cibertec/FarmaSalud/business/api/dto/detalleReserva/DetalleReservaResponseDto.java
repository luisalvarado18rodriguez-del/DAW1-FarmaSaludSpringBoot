package com.cibertec.FarmaSalud.business.api.dto.detalleReserva;

import lombok.Data;

@Data
public class DetalleReservaResponseDto {
    private Long idDetalle;
    private String nombreMedicamento; // Transformamos la entidad Medicamento a solo su nombre
    private Integer cantidad;
    private Double precioUnitario;
    private String rutaReceta;
    private Double subtotal; //Para calcular el SubTotal de la reserva
}
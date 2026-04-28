package com.cibertec.FarmaSalud.business.api.dto.medicamento;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicamentoResponseDto {
    private Long idMedicamento;
    private String nombre;
    private Double precio;
    private Integer stock;
    private LocalDate fechaVencimiento;
    private Boolean recetaMedica;
    private String nombreCategoria; // Mostramos el nombre de la categoría, no todo el objeto
}
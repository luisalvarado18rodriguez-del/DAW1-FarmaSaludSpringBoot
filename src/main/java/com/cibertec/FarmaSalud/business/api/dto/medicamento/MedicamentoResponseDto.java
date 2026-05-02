package com.cibertec.FarmaSalud.business.api.dto.medicamento;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicamentoResponseDto {
    private Long idMedicamento;
    private String nombre;
    private Double precio;
    private Integer stock;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;
    private Boolean recetaMedica;
    private String nombreCategoria;
    private String lote;
    private String rutaImagen;
}
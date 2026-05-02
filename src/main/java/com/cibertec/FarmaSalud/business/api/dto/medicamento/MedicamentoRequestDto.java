package com.cibertec.FarmaSalud.business.api.dto.medicamento;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class MedicamentoRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;
    private Boolean recetaMedica;

    @NotNull(message = "La categoría es obligatoria")
    private Integer idCategoria; // Solo pedimos el ID al crear
    @NotNull(message = "Debe ingresar el Lote")
    private String lote;
    private String rutaImagen;
}
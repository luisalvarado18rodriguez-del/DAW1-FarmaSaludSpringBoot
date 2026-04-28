package com.cibertec.FarmaSalud.business.api.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequestDto {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
    private String descripcion;
}

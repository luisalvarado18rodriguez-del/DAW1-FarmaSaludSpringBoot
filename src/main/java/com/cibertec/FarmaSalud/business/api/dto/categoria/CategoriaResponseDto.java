package com.cibertec.FarmaSalud.business.api.dto.categoria;

import lombok.Data;

@Data
public class CategoriaResponseDto {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
}
package com.cibertec.FarmaSalud.business.api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioResponseDto {
    private Long idUsuario;
    private String username;
    private String nombres;
    private String apellidos;
    private String rol;
}
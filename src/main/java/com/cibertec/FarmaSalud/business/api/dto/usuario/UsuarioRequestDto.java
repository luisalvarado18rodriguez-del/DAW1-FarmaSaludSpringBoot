package com.cibertec.FarmaSalud.business.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDto {
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "El password es obligatorio")
    private String password;

    private String nombres;
    private String apellidos;

    // Aquí recibiremos el rol como String desde el JSON ("ADMIN" o "CLIENTE")
    private String rol;
}

package com.cibertec.FarmaSalud.business.data.entity;

import com.cibertec.FarmaSalud.business.domain.enums.RolNombre;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nombres;
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolNombre rol; // Cambiado de String a Enum
}
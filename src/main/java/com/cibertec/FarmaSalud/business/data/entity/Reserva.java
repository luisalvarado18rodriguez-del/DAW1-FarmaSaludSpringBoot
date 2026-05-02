package com.cibertec.FarmaSalud.business.data.entity;

import com.cibertec.FarmaSalud.business.domain.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado; // Cambiado de String a Enum

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleReserva> detalles;
}
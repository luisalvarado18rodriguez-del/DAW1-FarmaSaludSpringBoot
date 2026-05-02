package com.cibertec.FarmaSalud.business.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalleReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    private Integer cantidad;
    private Double precioUnitario;
    private String rutaReceta; // Aquí guardaremos el nombre o ruta del archivo PDF/Imagen
}

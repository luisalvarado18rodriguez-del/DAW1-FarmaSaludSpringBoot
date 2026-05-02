package com.cibertec.FarmaSalud.business.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "receta_medica")
    private Boolean recetaMedica;

    // Relación ManyToOne con Categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @Column(nullable = false, length = 20)
    private String lote;
    //CAMPO: Para la imagen del producto
    @Column(name = "ruta_imagen", length = 255)
    private String rutaImagen;
}

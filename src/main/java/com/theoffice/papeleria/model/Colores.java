package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "colores")
@Data
public class Colores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColores;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

}

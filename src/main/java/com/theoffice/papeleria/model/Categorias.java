package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategorias;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Productos producto; // Muchos puentes apuntan a un Producto

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria; // Muchos puentes apuntan a una Categoria

    @Column(nullable = false)
    private boolean activo;
}
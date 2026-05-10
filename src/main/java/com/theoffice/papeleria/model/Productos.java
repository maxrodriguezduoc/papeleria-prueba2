package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductos;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto; // Conexión con el Producto

    @Column(nullable = false)
    private Integer cantidad; // Cuántas unidades se compraron

    @Column(nullable = false)
    @Positive(message = "el precio debe ser mayor a 0")
    private Integer precioUnitario;

    @Column(nullable = false)
    private boolean activo;
}

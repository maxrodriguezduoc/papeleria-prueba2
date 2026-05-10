package com.theoffice.papeleria.model;

import jakarta.persistence.*;
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
    private Integer precioUnitario; // Precio al momento de la venta

    @Column(nullable = false)
    private boolean activo;
}

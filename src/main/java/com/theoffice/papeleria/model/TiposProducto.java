package com.theoffice.papeleria.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "tipo_productos")
@Data
public class TiposProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTiposProductos;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", nullable = false)
    private TipoProducto tipoProducto;
}

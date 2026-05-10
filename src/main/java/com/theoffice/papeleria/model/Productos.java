package com.theoffice.papeleria.model;
import lombok.Data;
import jakarta.persistence.*; 
@Entity
@Table(name = "productos")
@Data
public class Productos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductos;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "id_color", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = true)
    private Venta venta;

}

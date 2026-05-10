package com.theoffice.papeleria.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "marcas")
@Data
public class Marcas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarcas;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;


}

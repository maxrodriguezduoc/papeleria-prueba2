package com.theoffice.papeleria.model;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "ventas")
@Data
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVentas;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_pago", nullable = false)
    private TipoPago tipoPago;
}

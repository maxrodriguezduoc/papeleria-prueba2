package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Ventas venta; // Relación con la Venta (Muchos pagos apuntan a una Venta)

    @ManyToOne
    @JoinColumn(name = "id_tipo_pago", nullable = false)
    private TipoPago tipoPago; // Efectivo, Tarjeta, Transferencia

    // --- CONEXIONES EXCLUSIVAS (PERMITEN NULO) ---

    @ManyToOne
    @JoinColumn(name = "id_tarjeta", nullable = true)
    private Tarjeta tarjeta; // Solo se llena si se pagó con Tarjeta

    @ManyToOne
    @JoinColumn(name = "id_transferencia", nullable = true)
    private Transferencia transferencia; // Solo se llena si se pagó con Transferencia

    @Column(nullable = false)
    @NotNull(message = "El monto a pagar es obligatorio")
    @Positive(message = "El monto de pago debe ser mayor a cero")
    private Integer monto;

    @Column(nullable = false)
    private boolean activo;
}

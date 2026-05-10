package com.theoffice.papeleria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Integer idPago;
    private Integer idVenta;
    private Integer totalVenta;
    private String tipoPagoNombre;
    private Integer montoPagado;
    private boolean activo;
}
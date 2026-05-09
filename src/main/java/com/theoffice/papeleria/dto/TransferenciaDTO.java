package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class TransferenciaDTO {
    private Integer idTransferencia;
    private String bancoOrigen;
    private Integer numeroCuentaOrigen;
    private String bancoDestino;
    private Integer numeroCuentaDestino;
    private double monto;
}

package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class TransferenciaDTO {
    private int idTransferencia;
    private String bancoOrigen;
    private int numeroCuentaOrigen;
    private String bancoDestino;
    private int numeroCuentaDestino;
    private double monto;
}

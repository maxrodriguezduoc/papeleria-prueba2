package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class TarjetaDTO {
    private Integer idTarjeta;
    private String tipoTarjeta;
    private String nombreBanco;
    private String ultimosCuatro;
    private String nombreTitular;
    private boolean activo;
}

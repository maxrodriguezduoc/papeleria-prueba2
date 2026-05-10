package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class ComunasDTO {

    private Integer idComuna;
    private String nombreComuna;
    private String codigoPostal;
    private boolean activo;
    private Integer idRegion;
}

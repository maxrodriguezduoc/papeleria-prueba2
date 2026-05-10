package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class CargosDTO {

    private Integer idCargos;
    private Integer colaboradorId;
    private Integer cargoId;
    private boolean activo;
}

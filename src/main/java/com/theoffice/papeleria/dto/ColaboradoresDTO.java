package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class ColaboradoresDTO {

    private Integer idColaborador;
    private String nombreColaborador;
    private boolean activo;
    private Integer idCargo;
    private Integer idLocal;
}

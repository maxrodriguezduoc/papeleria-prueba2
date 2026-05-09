package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer idCliente;
    private String rut;
    private String nombreCompleto;
    private boolean activo;
}

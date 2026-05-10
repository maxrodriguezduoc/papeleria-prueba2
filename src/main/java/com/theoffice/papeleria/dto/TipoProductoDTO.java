package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class TipoProductoDTO {
    private Integer id_tipo_producto;
    private String nombre;
    private boolean activo;
}

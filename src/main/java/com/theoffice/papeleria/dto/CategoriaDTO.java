package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class CategoriaDTO {
    private Integer idCategoria;
    private String nombre;
    private boolean activo;
}

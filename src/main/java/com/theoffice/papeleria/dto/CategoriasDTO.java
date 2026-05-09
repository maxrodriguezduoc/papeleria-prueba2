package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class CategoriasDTO {
    private Integer idCategorias;
    private Integer idProducto;
    private String nombreProducto;
    private Integer idCategoria;
    private String nombreCategoria;
    private boolean activo;
}
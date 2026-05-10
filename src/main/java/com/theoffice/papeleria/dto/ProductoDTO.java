package com.theoffice.papeleria.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Integer id_productos;
    private String nombre_producto;
    private Integer precio_producto;
    private Integer stock;
    private boolean activo;
}

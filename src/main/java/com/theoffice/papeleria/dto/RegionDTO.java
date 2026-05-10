package com.theoffice.papeleria.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegionDTO {

    private Integer idRegion;
    private String nombreRegion;
    private boolean activo;
}

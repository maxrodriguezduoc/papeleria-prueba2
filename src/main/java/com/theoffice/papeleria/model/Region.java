package com.theoffice.papeleria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "regiones")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegion;

    @NotBlank (message = "NOMBRE DE REGION ES OBLIGATORIO!")
    @Size(min = 15, max = 100, message = "NOMBRE DE REGION DEBE TENER ENTRE 15 A 100 CARACTERES!")
    @Column(nullable = false, length = 100)
    private String nombreRegion;

}

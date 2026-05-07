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
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComuna;

    @NotBlank (message = "NOMBRE DE COMUNA ES OBLIGATORIO!")
    @Size(min = 5, max = 100, message = "NOMBRE DE LA COMUNA DEBE TENER ENTRE 5 A 100 CARACTERES!")
    @Column(nullable = false, length = 100)
    private String nombreComuna;

    @NotBlank (message = "EL CODIGO POSTAL ES OBLIGATORIO!")
    @Size(min = 7, max = 7, message = "EL CODIGO POSTAL DEBE TENER 7 CARACTERES!")
    @Column(nullable = false, length = 100)
    private String codigoPostal;

}

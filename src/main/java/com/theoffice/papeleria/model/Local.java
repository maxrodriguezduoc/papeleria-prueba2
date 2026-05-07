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
@Table(name = "locales")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocal;

    @NotBlank (message = "EL NOMBRE DE LOCAL ES OBLIGATORIO!")
    @Size(min = 5, max = 100, message = "EL NOMBRE DE LOCAL DEBE TENER ENTRE 5 A 100 CARACTERES!")
    @Column(nullable = false, length = 100)
    private String nombreLocal;

    @NotBlank (message = "LA DIRECCIÓN ES OBLIGATORIO!")
    @Size(min = 5, max = 100, message = "LA DIRECCIÓN DEBE TENER ENTRE 5 A 100 CARACTERES!")
    @Column(nullable = false, length = 100)
    private String direccion;

}

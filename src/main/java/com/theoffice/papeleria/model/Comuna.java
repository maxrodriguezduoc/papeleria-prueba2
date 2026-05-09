package com.theoffice.papeleria.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComuna;

    @NotBlank (message = "Nombre de comuna es obligatorio!!")
    @Size(min = 5, max = 50, message = "Nombre de la comuna debe tener entre 5 a 50 caracteres!")
    @Column(nullable = false, length = 100)
    private String nombreComuna;

    @NotBlank (message = "El codigo postal es obligatorio!")
    @Size(min = 7, max = 7, message = "El codigo postal debe tener 7 caracteres!")
    @Column(nullable = false, length = 100)
    private String codigoPostal;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "comuna")
    private List<Local> locales;
}

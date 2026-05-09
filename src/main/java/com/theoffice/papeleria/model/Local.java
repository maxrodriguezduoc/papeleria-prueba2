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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locales")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocal;

    @NotBlank (message = "El nombre de local es obligatorio!")
    @Size(min = 5, max = 50, message = "El nombre de local debe tener entre 5 a 50 caracteres!")
    @Column(nullable = false, length = 50)
    private String nombreLocal;

    @NotBlank (message = "La dirección de local es obligatorio!")
    @Size(min = 15, max = 60, message = "La dirección debe tener entre 15 a 60 caracteres!")
    @Column(nullable = false, length = 60)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;

    @OneToMany(mappedBy = "local")
    private List<Colaborador> colaboradores;
}

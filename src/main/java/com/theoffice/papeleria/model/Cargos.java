package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cargos")
@Data
public class Cargos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCargos;

    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "id_cargo", nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private boolean activo;
}

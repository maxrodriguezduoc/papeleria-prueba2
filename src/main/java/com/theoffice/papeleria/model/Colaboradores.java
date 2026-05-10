package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "colaboradores")
public class Colaboradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaboradores;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private Local local; // La tienda física donde trabaja

    @ManyToOne
    @JoinColumn(name = "id_colaborador", nullable = false)
    private Colaborador colaborador; // El empleado asociado

    @Column(nullable = false)
    private boolean activo;
}

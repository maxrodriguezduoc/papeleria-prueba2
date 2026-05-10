package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "regiones")
@Data
public class Regiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegiones;

    @ManyToOne
    @JoinColumn(name = "id_region", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "id_comuna", nullable = false)
    private Comuna comuna;

    @Column(nullable = false)
    private boolean activo;
}

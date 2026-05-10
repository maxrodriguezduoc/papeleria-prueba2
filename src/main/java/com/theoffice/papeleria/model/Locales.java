package com.theoffice.papeleria.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "locales")
@Data
public class Locales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocales;

    @ManyToOne
    @JoinColumn(name = "id_local", nullable = false)
    private Local local;

    @ManyToOne
    @JoinColumn(name = "id_comuna", nullable = false)
    private Comuna comuna;

    @Column(nullable = false)
    private boolean activo;
}

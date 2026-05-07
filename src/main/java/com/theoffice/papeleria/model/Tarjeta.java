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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjetas")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private boolean esDebito; // true para débito, false para crédito

    @Column(nullable = false)
    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Size(min = 16, max = 16, message = "El número de tarjeta debe tener 16 dígitos")
    private int numeroTarjeta;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del titular debe tener entre 3 y 100 caracteres")
    private String nombreTitular;

    @Column(nullable = false)
    @NotBlank(message = "La fecha de expiración es obligatoria")
    private String fechaExpiracion;

    @Column(nullable = false)
    @NotBlank(message = "El CVV es obligatorio")
    @Size(min = 3, max = 3, message = "El CVV debe tener 3 dígitos")
    private String cvv;
}

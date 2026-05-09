package com.theoffice.papeleria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
<<<<<<< HEAD
import jakarta.validation.constraints.Pattern;
=======
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
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
<<<<<<< HEAD
    private Integer idTarjeta;
=======
    private int id;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9

    @Column(nullable = false)
    private boolean esDebito; // true para débito, false para crédito

    @Column(nullable = false)
    @NotBlank(message = "El número de tarjeta es obligatorio")
<<<<<<< HEAD
    @Size(min = 13, max = 19, message = "El número de tarjeta debe tener entre 13 y 19 caracteres")
    private String numeroTarjeta;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del banco es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del banco debe tener entre 3 y 50 caracteres")
    private String nombreBanco;
=======
    @Size(min = 16, max = 16, message = "El número de tarjeta debe tener 16 dígitos")
    private int numeroTarjeta;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9

    @Column(nullable = false)
    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del titular debe tener entre 3 y 100 caracteres")
    private String nombreTitular;

    @Column(nullable = false)
    @NotBlank(message = "La fecha de expiración es obligatoria")
<<<<<<< HEAD
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "El formato de fecha debe ser MM/YY")
=======
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
    private String fechaExpiracion;

    @Column(nullable = false)
    @NotBlank(message = "El CVV es obligatorio")
    @Size(min = 3, max = 3, message = "El CVV debe tener 3 dígitos")
    private String cvv;
<<<<<<< HEAD

    private boolean activo;
=======
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
}

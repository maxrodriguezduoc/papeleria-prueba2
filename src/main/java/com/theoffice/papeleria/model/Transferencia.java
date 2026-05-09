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
@Table(name = "transferencias")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer idTransferencia;
=======
    private int idTransferencia;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9

    @Column(nullable = false)
    @NotBlank(message = "El banco de origen es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del banco de origen debe tener entre 3 y 100 caracteres")
    private String bancoOrigen;

    @Column(nullable = false)
    @NotBlank(message = "El número de cuenta de origen es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta de origen debe tener entre 10 y 20 caracteres")
    private int numeroCuentaOrigen;

    @Column(nullable = false)
    @NotBlank(message = "El banco de destino es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del banco de destino debe tener entre 3 y 100 caracteres")
    private String bancoDestino;

    @Column(nullable = false)
    @NotBlank(message = "El número de cuenta de destino es obligatorio")
    @Size(min = 10, max = 20, message = "El número de cuenta de destino debe tener entre 10 y 20 caracteres")
<<<<<<< HEAD
    private Integer numeroCuentaDestino;
=======
    private int numeroCuentaDestino;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9

    @Column(nullable = false)
    @NotBlank(message = "El monto es obligatorio")
    private double monto;
}

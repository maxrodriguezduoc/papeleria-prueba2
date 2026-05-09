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
@Table(name = "tipos_pago")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer idTipoPago;
=======
<<<<<<< HEAD
    private Integer idTipoPago;
=======
    private int id;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1

    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "El nombre del tipo de pago debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El nombre del tipo de pago es obligatorio")
    private String formaPago;
<<<<<<< HEAD

    private boolean activo;
=======
<<<<<<< HEAD

    private boolean activo;
=======
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1
}

package com.theoffice.papeleria.model;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import java.time.LocalDateTime;

>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer idCliente;
=======
<<<<<<< HEAD
    private Integer idCliente;
=======
    private int id;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^[0-9]{7,8}-[0-9Kk]$", message = "Formato de RUT inválido")
    private String rut;

<<<<<<< HEAD
    @Column(nullable = false, length = 100 )
=======
<<<<<<< HEAD
    @Column(nullable = false, length = 100 )
=======
    @Column(nullable = false)
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1
    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 10, max = 100, message = "Nombre completo debe tener entre 10 y 100 caracteres")
    private String nombreCompleto;

<<<<<<< HEAD
    @Column(nullable = false)
    private boolean activo = true;
=======
<<<<<<< HEAD
    @Column(nullable = false)
    private boolean activo = true;
=======
    private LocalDateTime creadoEn;
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1
}
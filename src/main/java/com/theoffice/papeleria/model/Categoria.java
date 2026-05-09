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
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer idCategoria;
=======
<<<<<<< HEAD
    private Integer idCategoria;

    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;

    @Column(nullable = false)
    private boolean activo;
=======
    private int id;
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1

    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
<<<<<<< HEAD

    @Column(nullable = false)
    private boolean activo;
=======
>>>>>>> 4ccf83db74090978a7de6d23206230c0ec87abd9
>>>>>>> 2bdc99fb229ac588d0845715ea5d7bfd8c49d5f1
}

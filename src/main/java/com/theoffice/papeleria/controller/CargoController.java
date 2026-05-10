package com.theoffice.papeleria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.theoffice.papeleria.dto.CargoDTO;
import com.theoffice.papeleria.service.CargoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/cargos")
@Slf4j
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> listar() {
        log.info("Listando cargos!");
        return ResponseEntity.ok(cargoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> obtener(@PathVariable Integer id) {
        log.info("Obteniendo cargo {}", id);
        return ResponseEntity.ok(cargoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CargoDTO> crear(@Valid @RequestBody CargoDTO dto) {
        log.info("Registrando cargo!");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cargoService.guardarCargo(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody CargoDTO dto) {
        log.info("Actualizando cargo {}", id);
        return ResponseEntity.ok(cargoService.actualizarCargo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Eliminando cargo {}", id);
        cargoService.eliminarCargo(id);
        return ResponseEntity.noContent().build();
    }

}

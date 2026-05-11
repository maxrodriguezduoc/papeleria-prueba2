package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.CargoDTO;
import com.theoffice.papeleria.model.Cargo;
import com.theoffice.papeleria.service.CargoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargos")
@Slf4j
public class CargoController {

    @Autowired
    private CargoService cargoService;

    // 1. CREAR CARGO (Envía la entidad para validación en el modelo)
    @PostMapping
    public ResponseEntity<CargoDTO> crear(@Valid @RequestBody Cargo cargo) {
        log.info("API REST - Petición POST para crear un cargo con nombre: {}", cargo.getNombreCargo());
        
        CargoDTO nuevoCargo = cargoService.guardarCargo(cargo);
        return new ResponseEntity<>(nuevoCargo, HttpStatus.CREATED);
    }

    // 2. LISTAR TODOS LOS CARGOS ACTIVOS (Retorna 204 si la lista está vacía)
    @GetMapping
    public ResponseEntity<List<CargoDTO>> obtenerTodos() {
        log.info("API REST - Petición GET para listar todos los cargos activos");
        
        List<CargoDTO> cargos = cargoService.obtenerTodos();
        
        if (cargos.isEmpty()) {
            log.info("La consulta de cargos no retornó resultados");
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        
        return ResponseEntity.ok(cargos);
    }

    // 3. OBTENER UN CARGO POR SU ID
    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar cargo con ID: {}", id);
        
        CargoDTO cargo = cargoService.buscarPorId(id);
        return ResponseEntity.ok(cargo);
    }

    // 4. ACTUALIZAR UN CARGO
    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Cargo cargo) {
        log.info("API REST - Petición PUT para actualizar el cargo con ID: {}", id);
        
        CargoDTO actualizado = cargoService.actualizarCargo(id, cargo);
        return ResponseEntity.ok(actualizado);
    }

    // 5. BORRADO LÓGICO DE UN CARGO (Desactivación)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica del cargo con ID: {}", id);
        
        cargoService.eliminarCargo(id);
        return ResponseEntity.ok("El cargo con ID " + id + " ha sido desactivado con éxito de la papelería.");
    }
}
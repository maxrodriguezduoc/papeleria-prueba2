package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.LocalDTO;
import com.theoffice.papeleria.model.Local;
import com.theoffice.papeleria.service.LocalService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locales")
@Slf4j
public class LocalController {

    @Autowired
    private LocalService localService;

    @PostMapping
    public ResponseEntity<LocalDTO> crear(@Valid @RequestBody Local local) {
        log.info("API REST - Petición POST para crear un local con nombre: {}", local.getNombreLocal());
        
        LocalDTO nuevoLocal = localService.guardarLocal(local);
        return new ResponseEntity<>(nuevoLocal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LocalDTO>> obtenerTodos() {
        log.info("API REST - Petición GET para listar todos los locales activos");
        
        List<LocalDTO> locales = localService.obtenerTodos();
        
        if (locales.isEmpty()) {
            log.info("La consulta de locales no retornó resultados");
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(locales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar local con ID: {}", id);
        
        LocalDTO local = localService.buscarPorId(id);
        return ResponseEntity.ok(local);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Local local) {
        log.info("API REST - Petición PUT para actualizar el local con ID: {}", id);
        
        LocalDTO actualizado = localService.actualizarLocal(id, local);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica del local con ID: {}", id);
        
        localService.eliminarLocal(id);
        return ResponseEntity.ok("El local con ID " + id + " ha sido desactivado con éxito de la papelería.");
    }
}
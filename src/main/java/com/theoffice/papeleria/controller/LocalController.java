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

import com.theoffice.papeleria.dto.LocalDTO;
import com.theoffice.papeleria.service.LocalService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/locales")
@Slf4j
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public ResponseEntity<List<LocalDTO>> listar() {
        log.info("Listando locales");
        return ResponseEntity.ok(localService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> obtener(@PathVariable Integer id) {
        log.info("Obteniendo local {}", id);
        return ResponseEntity.ok(localService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LocalDTO> crear(@Valid @RequestBody LocalDTO dto) {
        log.info("Registrando local");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(localService.guardarLocal(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody LocalDTO dto) {

        log.info("Actualizando local {}", id);
        return ResponseEntity.ok(localService.actualizarLocal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Eliminando local {}", id);
        localService.eliminarLocal(id);
        return ResponseEntity.noContent().build();
    }
}

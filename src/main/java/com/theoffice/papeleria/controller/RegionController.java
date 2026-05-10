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

import com.theoffice.papeleria.dto.RegionDTO;
import com.theoffice.papeleria.service.RegionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/regiones")
@Slf4j
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
        log.info("Listando regiones");
        return ResponseEntity.ok(regionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtener(@PathVariable Integer id) {
        log.info("Obteniendo región {}", id);
        return ResponseEntity.ok(regionService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RegionDTO> crear(@Valid @RequestBody RegionDTO dto) {
        log.info("Registrando región");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(regionService.guardarRegion(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody RegionDTO dto) {

        log.info("Actualizando región {}", id);
        return ResponseEntity.ok(regionService.actualizarRegion(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Eliminando región {}", id);
        regionService.eliminarRegion(id);
        return ResponseEntity.noContent().build();
    }
}

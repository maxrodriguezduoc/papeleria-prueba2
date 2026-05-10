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

import com.theoffice.papeleria.dto.ComunaDTO;
import com.theoffice.papeleria.service.ComunaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/comunas")
@Slf4j
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> listar() {
        log.info("Listando comunas!");
        return ResponseEntity.ok(comunaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> obtener(@PathVariable Integer id) {
        log.info("Obteniendo comuna con ID: {}", id);
        return ResponseEntity.ok(comunaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> crear(@Valid @RequestBody ComunaDTO dto) {
        log.info("Registrando comuna");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comunaService.guardarComuna(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunaDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ComunaDTO dto) {

        log.info("Actualizando comuna con ID: {}", id);
        return ResponseEntity.ok(comunaService.actualizarComuna(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Eliminando comuna con ID: {}", id);
        comunaService.eliminarComuna(id);
        return ResponseEntity.noContent().build();
    }
}

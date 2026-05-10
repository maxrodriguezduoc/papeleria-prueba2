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

import com.theoffice.papeleria.dto.ColaboradorDTO;
import com.theoffice.papeleria.service.ColaboradorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/colaboradores")
@Slf4j
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> listar() {
        log.info("Listando colaboradores!");
        return ResponseEntity.ok(colaboradorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> obtener(@PathVariable Integer id) {
        log.info("Obteniendo colaborador con ID: {}", id);
        return ResponseEntity.ok(colaboradorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ColaboradorDTO> crear(@Valid @RequestBody ColaboradorDTO dto) {
        log.info("Registrando colaborador!");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colaboradorService.guardarColaborador(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ColaboradorDTO dto) {
        
        log.info("Actualizando colaborador con ID: {}", id);
        return ResponseEntity.ok(colaboradorService.actualizarColaborador(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        log.info("Eliminando colaborador con ID: {}", id);
        colaboradorService.eliminarColaborador(id);
        return ResponseEntity.noContent().build();
    }

}

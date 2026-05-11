package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.ColaboradorDTO;
import com.theoffice.papeleria.model.Colaborador;
import com.theoffice.papeleria.service.ColaboradorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colaboradores")
@Slf4j
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<ColaboradorDTO> crear(@Valid @RequestBody Colaborador colaborador) {
        log.info("API REST - Petición POST para crear un colaborador con RUT/Nombre: {}", colaborador.getNombreColaborador());
        
        ColaboradorDTO nuevoColaborador = colaboradorService.guardarColaborador(colaborador);
        return new ResponseEntity<>(nuevoColaborador, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> obtenerTodos() {
        log.info("API REST - Petición GET para listar todos los colaboradores activos");
        
        List<ColaboradorDTO> colaboradores = colaboradorService.obtenerTodos();
        
        if (colaboradores.isEmpty()) {
            log.info("La consulta de colaboradores no retornó resultados");
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(colaboradores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar colaborador con ID: {}", id);
        
        ColaboradorDTO colaborador = colaboradorService.buscarPorId(id);
        return ResponseEntity.ok(colaborador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Colaborador colaborador) {
        log.info("API REST - Petición PUT para actualizar el colaborador con ID: {}", id);
        
        ColaboradorDTO actualizado = colaboradorService.actualizarColaborador(id, colaborador);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica del colaborador con ID: {}", id);
        
        colaboradorService.eliminarColaborador(id);
        return ResponseEntity.ok("El colaborador con ID " + id + " ha sido desactivado con éxito de la papelería.");
    }
}
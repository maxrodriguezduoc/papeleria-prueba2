package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.TransferenciaDTO;
import com.theoffice.papeleria.model.Transferencia;
import com.theoffice.papeleria.service.TransferenciaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
@Slf4j
@CrossOrigin(origins = "*") // Habilita la integración segura con el frontend
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    // 1. REGISTRAR TRANSFERENCIA
    // Retorna HTTP 201 Created
    @PostMapping
    public ResponseEntity<TransferenciaDTO> crear(@Valid @RequestBody Transferencia transferencia) {
        log.info("API REST - Petición POST para registrar una nueva transferencia");
        TransferenciaDTO nuevaTransferencia = transferenciaService.crear(transferencia);
        return new ResponseEntity<>(nuevaTransferencia, HttpStatus.CREATED);
    }

    // 2. LISTAR TRANSFERENCIAS ACTIVAS
    // Retorna HTTP 200 OK (o HTTP 204 No Content si está vacía)
    @GetMapping
    public ResponseEntity<List<TransferenciaDTO>> obtenerTodas() {
        log.info("API REST - Petición GET para listar todas las transferencias activas");
        List<TransferenciaDTO> transferencias = transferenciaService.obtenerTodas();
        
        if (transferencias.isEmpty()) {
            log.info("La consulta de transferencias no retornó resultados activos");
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        
        return ResponseEntity.ok(transferencias);
    }

    // 3. OBTENER TRANSFERENCIA POR ID
    // Retorna HTTP 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar transferencia con ID: {}", id);
        TransferenciaDTO transferencia = transferenciaService.obtenerPorId(id);
        return ResponseEntity.ok(transferencia);
    }

    // 4. ACTUALIZAR TRANSFERENCIA
    // Retorna HTTP 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Transferencia transferencia) {
        log.info("API REST - Petición PUT para actualizar transferencia con ID: {}", id);
        TransferenciaDTO actualizada = transferenciaService.actualizar(id, transferencia);
        return ResponseEntity.ok(actualizada);
    }

    // 5. ANULAR / ELIMINAR TRANSFERENCIA (Borrado Lógico)
    // Retorna HTTP 200 OK con un mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica de la transferencia con ID: {}", id);
        transferenciaService.eliminar(id);
        return ResponseEntity.ok("La transferencia con ID " + id + " ha sido desactivada con éxito.");
    }
}
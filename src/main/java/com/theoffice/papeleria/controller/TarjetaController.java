package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.TarjetaDTO;
import com.theoffice.papeleria.model.Tarjeta;
import com.theoffice.papeleria.service.TarjetaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarjetas")
@Slf4j
@CrossOrigin(origins = "*") // Habilita la integración segura con el frontend
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    // 1. REGISTRAR TARJETA
    // Retorna HTTP 201 Created
    @PostMapping
    public ResponseEntity<TarjetaDTO> crear(@Valid @RequestBody Tarjeta tarjeta) {
        log.info("API REST - Petición POST para registrar tarjeta");
        TarjetaDTO nuevaTarjeta = tarjetaService.crear(tarjeta);
        return new ResponseEntity<>(nuevaTarjeta, HttpStatus.CREATED);
    }

    // 2. LISTAR TARJETAS ACTIVAS
    // Retorna HTTP 200 OK (o HTTP 204 No Content si está vacía)
    @GetMapping
    public ResponseEntity<List<TarjetaDTO>> obtenerTodas() {
        log.info("API REST - Petición GET para listar todas las tarjetas activas");
        List<TarjetaDTO> tarjetas = tarjetaService.obtenerTodas();
        
        if (tarjetas.isEmpty()) {
            log.info("La consulta de tarjetas no retornó resultados activos");
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        
        return ResponseEntity.ok(tarjetas);
    }

    // 3. OBTENER TARJETA POR ID
    // Retorna HTTP 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<TarjetaDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar tarjeta con ID: {}", id);
        TarjetaDTO tarjeta = tarjetaService.obtenerPorId(id);
        return ResponseEntity.ok(tarjeta);
    }

    // 4. ACTUALIZAR TARJETA
    // Retorna HTTP 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<TarjetaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Tarjeta tarjeta) {
        log.info("API REST - Petición PUT para actualizar tarjeta con ID: {}", id);
        TarjetaDTO actualizada = tarjetaService.actualizar(id, tarjeta);
        return ResponseEntity.ok(actualizada);
    }

    // 5. ANULAR / ELIMINAR TARJETA (Borrado Lógico)
    // Retorna HTTP 200 OK con un mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica de la tarjeta con ID: {}", id);
        tarjetaService.eliminar(id);
        return ResponseEntity.ok("La tarjeta con ID " + id + " ha sido desactivada con éxito.");
    }
}
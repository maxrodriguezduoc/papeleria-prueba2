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
import com.theoffice.papeleria.dto.MarcaDTO;
import com.theoffice.papeleria.model.Marca;
import com.theoffice.papeleria.service.MarcaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/productos")
@Slf4j

public class ProductoController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaDTO> crear(@Valid @RequestBody Marca marca) {
        log.info("POST - Crear marca: {}", marca.getNombre_marca());
        MarcaDTO nueva = marcaService.crearMarca(marca);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarcaDTO>> obtenerTodas() {
        log.info("GET - Listar marcas activas");
        List<MarcaDTO> marcas = marcaService.obtenerTodos();
        if (marcas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("GET - Buscar marca ID: {}", id);
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Marca marca) {
        log.info("PUT - Actualizar marca ID: {}", id);
        return ResponseEntity.ok(marcaService.actualizarMarca(id, marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("DELETE - Eliminar marca ID: {}", id);
        marcaService.eliminarMarca(id);
        return ResponseEntity.ok("Marca con ID " + id + " desactivada con éxito.");
    }


}

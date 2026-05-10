package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.CategoriaDTO;
import com.theoffice.papeleria.model.Categoria;
import com.theoffice.papeleria.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@Slf4j
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // 1. CREAR CATEGORIA
    // Retorna HTTP 201 Created
    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@Valid @RequestBody Categoria categoria) {
        log.info("API REST - Petición POST para crear una nueva categoría: '{}'", categoria.getNombre());
        
        CategoriaDTO nuevaCategoria = categoriaService.crear(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // 2. OBTENER TODAS LAS CATEGORIAS ACTIVAS
    // Retorna HTTP 200 OK (o HTTP 204 No Content si la lista está vacía)
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obtenerTodas() {
        log.info("API REST - Petición GET para listar todas las categorías activas");
        
        List<CategoriaDTO> categorias = categoriaService.obtenerTodas();
        
        if (categorias.isEmpty()) {
            log.info("La consulta de categorías no retornó registros activos");
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        }
        
        return ResponseEntity.ok(categorias);
    }

    // 3. OBTENER CATEGORÍA POR ID
    // Retorna HTTP 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar la categoría con ID: {}", id);
        
        CategoriaDTO categoria = categoriaService.obtenerPorId(id);
        return ResponseEntity.ok(categoria);
    }

    // 4. ACTUALIZAR CATEGORIA
    // Retorna HTTP 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        log.info("API REST - Petición PUT para actualizar la categoría con ID: {}", id);
        
        CategoriaDTO actualizada = categoriaService.actualizar(id, categoria);
        return ResponseEntity.ok(actualizada);
    }

    // 5. ELIMINAR CATEGORIA
    // Retorna HTTP 200 OK con mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica de la categoría con ID: {}", id);
        
        categoriaService.eliminar(id);
        return ResponseEntity.ok("La categoría con ID " + id + " ha sido desactivada con éxito.");
    }
}
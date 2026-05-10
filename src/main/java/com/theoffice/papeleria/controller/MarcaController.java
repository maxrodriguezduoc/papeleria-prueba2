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
@RequestMapping("/api/marcas")
@Slf4j
public class MarcaController {
     @Autowired
    private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<MarcaDTO> marcas = marcaService.obtenerTodos();
        if (!marcas.isEmpty()) {
            return new ResponseEntity<>(marcas, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay marcas activas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(marcaService.buscarPorId(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Marca no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Marca marca) {
        try {
            return new ResponseEntity<>(marcaService.crearMarca(marca), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody Marca marca) {
        try {
            return new ResponseEntity<>(marcaService.actualizarMarca(id, marca), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            marcaService.eliminarMarca(id);
            return new ResponseEntity<>("Marca eliminada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

package com.theoffice.papeleria.controller;

import com.theoffice.papeleria.dto.ClienteDTO;
import com.theoffice.papeleria.model.Cliente;
import com.theoffice.papeleria.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // 1. CREAR CLIENTE
    // Retorna HTTP 201 Created
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody Cliente cliente) {
        log.info("API REST - Petición POST para crear un cliente con RUT: {}", cliente.getRut());
        
        ClienteDTO nuevoCliente = clienteService.crear(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // 2. OBTENER TODOS LOS CLIENTES ACTIVOS
    // Retorna HTTP 200 OK
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        log.info("API REST - Petición GET para listar todos los clientes activos");
        
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        
        if (clientes.isEmpty()) {
            log.info("La consulta de clientes no retornó resultados");
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si la lista está vacía
        }
        
        return ResponseEntity.ok(clientes);
    }

    // 3. OBTENER CLIENTE POR ID
    // Retorna HTTP 200 OK
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
        log.info("API REST - Petición GET para buscar cliente con ID: {}", id);
        
        ClienteDTO cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cliente);
    }

    // 4. ACTUALIZAR CLIENTE
    // Retorna HTTP 200 OK
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        log.info("API REST - Petición PUT para actualizar el cliente con ID: {}", id);
        
        ClienteDTO actualizado = clienteService.actualizar(id, cliente);
        return ResponseEntity.ok(actualizado);
    }

    // 5. ELIMINAR / DAR DE BAJA CLIENTE (Borrado Lógico)
    // Retorna HTTP 200 OK con un mensaje de confirmación
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        log.warn("API REST - Petición DELETE para baja lógica del cliente con ID: {}", id);
        
        clienteService.eliminar(id);
        return ResponseEntity.ok("El cliente con ID " + id + " ha sido desactivado con éxito de la papelería.");
    }
}
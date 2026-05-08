package com.theoffice.papeleria.service;

import com.theoffice.papeleria.dto.ClienteDTO;
import com.theoffice.papeleria.model.Cliente;
import com.theoffice.papeleria.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO crear(Cliente cliente) {
        log.info("Intentando registrar cliente con RUT: {}", cliente.getRut());

        if (!validarRut(cliente.getRut())) {
            log.error("Error: El RUT {} no tiene un formato válido", cliente.getRut());
            throw new RuntimeException("El formato del RUT es incorrecto.");
        }

        clienteRepository.save(cliente);
        log.info("Cliente guardado con éxito. ID asignado: {}", cliente.getIdCliente());
        
        return convertirADTO(cliente);
    }

    public List<ClienteDTO> obtenerTodos() {
        log.info("Obteniendo lista completa de clientes");
        return clienteRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ClienteDTO obtenerPorId(Integer id) {
        log.info("Buscando cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el cliente con ID: " + id));
        return convertirADTO(cliente);
    }

    public ClienteDTO actualizar(Integer id, Cliente cliente) {
        log.info("Actualizando datos del cliente con ID: {}", id);
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: Cliente no encontrado"));

        existente.setIdCliente(cliente.getIdCliente());
        existente.setRut(cliente.getRut());
        existente.setNombreCompleto(cliente.getNombreCompleto());

        clienteRepository.save(existente);
        log.info("Datos actualizados correctamente para el ID: {}", id);
        
        return convertirADTO(existente);
    }

    public void eliminar(Integer id) {
        log.warn("Se ha solicitado eliminar el cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: El ID no existe.");
        }
        clienteRepository.deleteById(id);
        log.info("Cliente con ID {} eliminado satisfactoriamente", id);
    }

    private boolean validarRut(String rut) {
        // Validación de formato (ejemplo: 12345678-9)
        if(rut != null && rut.matches("^[0-9]{7,8}-[0-9Kk]$")){
            return true;
        }
        return false;
    }

    private ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setRut(cliente.getRut());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        return dto;
    }
}
package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.ComunaDTO;
import com.theoffice.papeleria.model.Comuna;
import com.theoffice.papeleria.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    // Obtener una lista de los comunas
    public List<ComunaDTO> obtenerTodos(){
        return comunaRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar Comuna por ID
    public ComunaDTO buscarPorId(Integer id){
        Comuna comuna = comunaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("COMUNA NO ENCONTRADA!"));
        return convertirADTO(comuna);
    }

    // Metodo para eliminar Comuna
    public String eliminarComuna(Integer id){
        try {
            Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! COMUNA NO ENCONTRADA!"));

            comunaRepository.delete(comuna);
            return "COMUNA " + comuna.getNombreComuna() + " ELIMINADA!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar una Comuna
    public Comuna guardarComuna(Comuna comuna){
        return comunaRepository.save(comuna);
    }

    // Metodo para actualizar comunas
    public Comuna actualizarComuna(Integer id, Comuna comuna){
        Comuna comunaExistente = comunaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("COMUNA NO ENCONTRADA!"));

        if (comuna.getNombreComuna() != null){
            comunaExistente.setNombreComuna(comuna.getNombreComuna());
        }
        if (comuna.getCodigoPostal() != null){
            comunaExistente.setCodigoPostal(comuna.getCodigoPostal());
        }
        return comunaRepository.save(comunaExistente);
    }

    // Metodo para convertir Entidades en DTO
    private ComunaDTO convertirADTO(Comuna comuna){
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setCodigoPostal(comuna.getCodigoPostal());
        return dto;
    }
}

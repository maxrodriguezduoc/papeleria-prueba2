package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.MaterialDTO;
import com.theoffice.papeleria.model.Material;
import com.theoffice.papeleria.repository.MaterialRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // Obtener una lista de los materiales
    public List<MaterialDTO> obtenerTodos(){
        return materialRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar Materiales por ID
    public MaterialDTO buscarPorId(Integer id){
        Material material = materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("MATERIAL NO ENCONTRADO!"));
        return convertirADTO(material);
    }

    // Metodo para eliminar Material
    public String eliminarCMaterial(Integer id){
        try {
            Material material = materialRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! MATERIAL NO ENCONTRADO!"));

            materialRepository.delete(material);
            return "MATERIAL " + material.getNombreMaterial() + " ELIMINADO!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar un Material
    public Material guardarMaterial(Material material){
        return materialRepository.save(material);
    }

    // Metodo para actualizar materiales
    public Material actualizarMaterial(Integer id, Material material){
        Material materialExistente = materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("MATERIAL NO ENCONTRADO!"));

        if (material.getNombreMaterial() != null){
            materialExistente.setNombreMaterial(material.getNombreMaterial());
        }
        return materialRepository.save(materialExistente);
    }

    // Metodo para convertir Entidades en DTO
    private MaterialDTO convertirADTO(Material material){
        MaterialDTO dto = new MaterialDTO();
        dto.setIdMaterial(material.getIdMaterial());
        dto.setNombreMaterial(material.getNombreMaterial());
        return dto;
    }

}

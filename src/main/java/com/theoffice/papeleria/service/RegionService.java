package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.RegionDTO;
import com.theoffice.papeleria.model.Region;
import com.theoffice.papeleria.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    // Obtener una lista de las regiones
    public List<RegionDTO> obtenerTodos(){
        return regionRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar Regiones por ID
    public RegionDTO buscarPorId(Integer id){
        Region region = regionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("REGION NO ENCONTRADA!"));
        return convertirADTO(region);
    }

    // Metodo para eliminar Regiones
    public String eliminarRegion(Integer id){
        try {
            Region region = regionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! REGION NO ENCONTRADA!"));

            regionRepository.delete(region);
            return "REGION " + region.getNombreRegion() + " ELIMINADA!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar un Regiones
    public Region guardarRegion(Region region){
        return regionRepository.save(region);
    }

    // Metodo para editar Regiones
    public Region actualizarRegion(Integer id, Region region){
        Region regionExistente = regionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("REGION NO ENCONTRADA!"));

        if (region.getNombreRegion() != null){
            regionExistente.setNombreRegion(region.getNombreRegion());
        }
        return regionRepository.save(regionExistente);
    }

    // Metodo para convertir Entidades en DTO
    private RegionDTO convertirADTO(Region region){
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombreRegion(region.getNombreRegion());
        return dto;
    }

}

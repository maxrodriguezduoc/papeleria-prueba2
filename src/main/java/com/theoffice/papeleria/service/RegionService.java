package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.RegionDTO;
import com.theoffice.papeleria.model.Region;
import com.theoffice.papeleria.repository.RegionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos() {
        log.info("Obteniendo lista de regiones");

        return regionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RegionDTO buscarPorId(Integer id) {
        log.info("Buscando región con ID: {}", id);

        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al buscar! Region no encontrada!");
                    return new RuntimeException("Regio no encontrada!");
                });

        return convertirADTO(region);
    }

    public void eliminarRegion(Integer id) {
        log.info("Intentando eliminar región con ID: {}", id);

        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al buscar region! Region no encontrada!", id);
                    return new RuntimeException("Región no encontrada!");
                });

        region.setActivo(false);
        regionRepository.save(region);

        log.info("Region eliminada exitosamente!!");
    }

    public RegionDTO guardarRegion(RegionDTO dto) {
        log.info("Creando región: {}", dto.getNombreRegion());

        if (dto.getNombreRegion() == null || dto.getNombreRegion().trim().isEmpty()) {
            throw new RuntimeException("Nombre de region obligatorio!");
        }

        Region region = new Region();
        region.setNombreRegion(dto.getNombreRegion().trim());
        region.setActivo(true);

        Region guardada = regionRepository.save(region);

        log.info("Region guardada exitosamente!");

        return convertirADTO(guardada);
    }

    public RegionDTO actualizarRegion(Integer id, RegionDTO dto) {
        log.info("Actualizando región con ID: {}", id);

        Region existente = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al buscar region! Region no encontrada!");
                    return new RuntimeException("Región no encontrada!");
                });

        if (dto.getNombreRegion() != null) {
            existente.setNombreRegion(dto.getNombreRegion().trim());
        }

        Region actualizada = regionRepository.save(existente);

        log.info("Región actualizada exitosamente!");

        return convertirADTO(actualizada);
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombreRegion(region.getNombreRegion());
        dto.setActivo(region.isActivo());
        return dto;
    }
}

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
                    log.error("Región con ID {} no encontrada", id);
                    return new RuntimeException("Región no encontrada");
                });

        return convertirADTO(region);
    }

    public void eliminarRegion(Integer id) {
        log.info("Intentando eliminar región con ID: {}", id);

        Region region = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Región con ID {} no encontrada", id);
                    return new RuntimeException("Región no encontrada");
                });

        regionRepository.delete(region);

        log.info("Región eliminada exitosamente!");
    }

    public RegionDTO guardarRegion(RegionDTO dto) {
        log.info("Creando región: {}", dto.getNombreRegion());

        Region region = new Region();
        region.setNombreRegion(dto.getNombreRegion());

        Region guardada = regionRepository.save(region);

        log.info("Región creada exitosamente");

        return convertirADTO(guardada);
    }

    public RegionDTO actualizarRegion(Integer id, RegionDTO dto) {
        log.info("Actualizando región con ID: {}", id);

        Region existente = regionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Región con ID {} no encontrada", id);
                    return new RuntimeException("Región no encontrada");
                });

        existente.setNombreRegion(dto.getNombreRegion());

        Region actualizada = regionRepository.save(existente);

        log.info("Región actualizada exitosamente!");

        return convertirADTO(actualizada);
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setNombreRegion(region.getNombreRegion());
        return dto;
    }
}

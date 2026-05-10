package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.ComunaDTO;
import com.theoffice.papeleria.model.Comuna;
import com.theoffice.papeleria.model.Region;
import com.theoffice.papeleria.repository.ComunaRepository;
import com.theoffice.papeleria.repository.RegionRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private RegionRepository regionRepository;

    public List<ComunaDTO> obtenerTodos() {
        log.info("Obteniendo lista de comunas");

        return comunaRepository.findAll().stream()
                .filter(Comuna::isActivo)
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        log.info("Buscando comuna con ID: {}", id);

        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada!"));

        if (!comuna.isActivo()) {
            log.warn("Comuna inactiva!");
            throw new RuntimeException("Debe seleccionar una comuna activa!");
        }

        return convertirADTO(comuna);
    }

    public void eliminarComuna(Integer id) {
        log.info("Intentando eliminar comuna con ID: {}", id);

        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al eliminar comuna! Comuna no encontrada!"));

        if (!comuna.isActivo()) {
            log.info("Comuna inactiva");
            return;
        }

        comuna.setActivo(false);
        comunaRepository.save(comuna);

        log.info("Comuna eliminada exitosamente!");
    }

    public ComunaDTO guardarComuna(ComunaDTO dto) {
        log.info("Creando comuna: {}", dto.getNombreComuna());

        if (dto.getNombreComuna() == null || dto.getNombreComuna().trim().isEmpty()) {
            throw new RuntimeException("Nombre de comuna obligatorio!");
        }

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new RuntimeException("Region no encontrada!"));

        if (!region.isActivo()) {
            throw new RuntimeException("Se debe seleccionar una region activa");
        }

        Comuna comuna = new Comuna();
        comuna.setNombreComuna(dto.getNombreComuna().trim());
        comuna.setCodigoPostal(dto.getCodigoPostal());
        comuna.setRegion(region);
        comuna.setActivo(true);

        Comuna guardada = comunaRepository.save(comuna);

        log.info("Comuna registrada exitosamente!");

        return convertirADTO(guardada);
    }

    public ComunaDTO actualizarComuna(Integer id, ComunaDTO dto) {
        log.info("Actualizando comuna con ID: {}", id);

        Comuna existente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada!"));

        if (!existente.isActivo()) {
            throw new RuntimeException("Se debe seleccionar una comun activa!");
        }

        if (dto.getNombreComuna() == null || dto.getNombreComuna().trim().isEmpty()) {
            throw new RuntimeException("Nombre de comuna obligatorio!");
        }

        existente.setNombreComuna(dto.getNombreComuna().trim());
        existente.setCodigoPostal(dto.getCodigoPostal());

        if (dto.getRegionId() != null) {
            Region region = regionRepository.findById(dto.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Region no encontrada!"));

            if (!region.isActivo()) {
                throw new RuntimeException("Se debe seleccionar una region activa!");
            }

            existente.setRegion(region);
        }

        comunaRepository.save(existente);

        log.info("Comuna actualizada exitosamente!");

        return convertirADTO(existente);
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setCodigoPostal(comuna.getCodigoPostal());
        dto.setRegionId(comuna.getRegion().getIdRegion());
        dto.setActivo(comuna.isActivo());
        return dto;
    }
}

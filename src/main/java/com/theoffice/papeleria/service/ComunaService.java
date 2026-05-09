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
    private RegionRepository regionRepository;

    public List<ComunaDTO> obtenerTodos() {
        log.info("Obteniendo lista de comunas");

        return comunaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id) {
        log.info("Buscando comuna con ID: {}", id);

        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna con ID {} no encontrada", id);
                    return new RuntimeException("Comuna no encontrada");
                });

        return convertirADTO(comuna);
    }

    public void eliminarComuna(Integer id) {
        log.info("Intentando eliminar comuna con ID: {}", id);

        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna con ID {} no encontrada", id);
                    return new RuntimeException("Comuna no encontrada");
                });

        comunaRepository.delete(comuna);

        log.info("Comuna con ID {} eliminada correctamente", id);
    }

    public ComunaDTO guardarComuna(ComunaDTO dto) {
        log.info("Creando comuna: {}", dto.getNombreComuna());

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> {
                    log.error("Region con ID {} no encontrada", dto.getRegionId());
                    return new RuntimeException("Region no encontrada");
                });

        Comuna comuna = new Comuna();
        comuna.setNombreComuna(dto.getNombreComuna());
        comuna.setCodigoPostal(dto.getCodigoPostal());
        comuna.setRegion(region);

        Comuna guardada = comunaRepository.save(comuna);

        log.info("Comuna creada con ID: {}", guardada.getIdComuna());

        return convertirADTO(guardada);
    }

    public ComunaDTO actualizarComuna(Integer id, ComunaDTO dto) {
        log.info("Actualizando comuna con ID: {}", id);

        Comuna existente = comunaRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Comuna con ID {} no encontrada", id);
                    return new RuntimeException("Comuna no encontrada");
                });

        existente.setNombreComuna(dto.getNombreComuna());
        existente.setCodigoPostal(dto.getCodigoPostal());

        if (dto.getRegionId() != null) {
            Region region = regionRepository.findById(dto.getRegionId())
                    .orElseThrow(() -> new RuntimeException("Region no encontrada"));
            existente.setRegion(region);
        }

        Comuna actualizada = comunaRepository.save(existente);

        log.info("Comuna con ID {} actualizada correctamente", id);

        return convertirADTO(actualizada);
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        dto.setCodigoPostal(comuna.getCodigoPostal());

        if (comuna.getRegion() != null) {
            dto.setRegionId(comuna.getRegion().getIdRegion());
        }

        return dto;
    }
}

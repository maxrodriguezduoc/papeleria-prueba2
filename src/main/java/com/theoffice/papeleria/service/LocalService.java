package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.LocalDTO;
import com.theoffice.papeleria.model.Comuna;
import com.theoffice.papeleria.model.Local;
import com.theoffice.papeleria.repository.ComunaRepository;
import com.theoffice.papeleria.repository.LocalRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    public List<LocalDTO> obtenerTodos() {
        log.info("Obteniendo lista de locales");

        return localRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public LocalDTO buscarPorId(Integer id) {
        log.info("Buscando local con ID: {}", id);

        Local local = localRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al buscar Local! Local no encontrado!");
                    return new RuntimeException("Local no encontrado!");
                });

        return convertirADTO(local);
    }

    public void eliminarLocal(Integer id) {
        log.info("Intentando eliminar local con ID: {}", id);

        Local local = localRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Local no encontrado!");
                    return new RuntimeException("Local no encontrado!");
                });

        local.setActivo(false);
        localRepository.save(local);

        log.info("Local eliminado correctamente!");
    }

    public LocalDTO guardarLocal(LocalDTO dto) {
        log.info("Creando local: {}", dto.getNombreLocal());

        if (dto.getNombreLocal() == null || dto.getNombreLocal().trim().isEmpty()) {
            throw new RuntimeException("Nombre de local obligatorio!");
        }

        if (dto.getDireccion() == null || dto.getDireccion().trim().isEmpty()) {
            throw new RuntimeException("Direccion de local obligatorio!");
        }

        Comuna comuna = comunaRepository.findById(dto.getComunaId())
                .orElseThrow(() -> {
                    log.error("Comuna no encontrada!");
                    return new RuntimeException("Comuna no encontrada!");
                });

        Local local = new Local();
        local.setNombreLocal(dto.getNombreLocal().trim());
        local.setDireccion(dto.getDireccion().trim());
        local.setComuna(comuna);
        local.setActivo(true);

        Local guardado = localRepository.save(local);

        log.info("Local guardado exitosamente!");

        return convertirADTO(guardado);
    }

    public LocalDTO actualizarLocal(Integer id, LocalDTO dto) {
        log.info("Actualizando local con ID: {}", id);

        Local existente = localRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error al actualizar! Local no encontrado!");
                    return new RuntimeException("Local no encontrado!");
                });

        if (dto.getNombreLocal() != null) {
            existente.setNombreLocal(dto.getNombreLocal().trim());
        }

        if (dto.getDireccion() != null) {
            existente.setDireccion(dto.getDireccion().trim());
        }

        if (dto.getComunaId() != null) {
            Comuna comuna = comunaRepository.findById(dto.getComunaId())
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada!"));
            existente.setComuna(comuna);
        }
        Local actualizado = localRepository.save(existente);
        log.info("Local actualizado exitosamente!");
        return convertirADTO(actualizado);
    }

    private LocalDTO convertirADTO(Local local) {
        LocalDTO dto = new LocalDTO();
        dto.setIdLocal(local.getIdLocal());
        dto.setNombreLocal(local.getNombreLocal());
        dto.setDireccion(local.getDireccion());
        dto.setActivo(local.isActivo());

        if (local.getComuna() != null) {
            dto.setComunaId(local.getComuna().getIdComuna());
        }
        return dto;
    }
}

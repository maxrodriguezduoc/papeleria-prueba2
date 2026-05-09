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
                    log.error("Local con ID {} no encontrado", id);
                    return new RuntimeException("Local no encontrado");
                });

        return convertirADTO(local);
    }

    public void eliminarLocal(Integer id) {
        log.info("Intentando eliminar local con ID: {}", id);

        Local local = localRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Local con ID {} no encontrado", id);
                    return new RuntimeException("Local no encontrado");
                });

        localRepository.delete(local);

        log.info("Local eliminado exitosamente!");
    }

    public LocalDTO guardarLocal(LocalDTO dto) {
        log.info("Creando local: {}", dto.getNombreLocal());

        Comuna comuna = comunaRepository.findById(dto.getComunaId())
                .orElseThrow(() -> {
                    log.error("Comuna con ID {} no encontrada", dto.getComunaId());
                    return new RuntimeException("Comuna no encontrada");
                });

        Local local = new Local();
        local.setNombreLocal(dto.getNombreLocal());
        local.setDireccion(dto.getDireccion());
        local.setComuna(comuna);

        Local guardado = localRepository.save(local);

        log.info("Local creado exitosamente!");

        return convertirADTO(guardado);
    }

    public LocalDTO actualizarLocal(Integer id, LocalDTO dto) {
        log.info("Actualizando local con ID: {}", id);

        Local existente = localRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Local con ID {} no encontrado", id);
                    return new RuntimeException("Local no encontrado");
                });

        existente.setNombreLocal(dto.getNombreLocal());
        existente.setDireccion(dto.getDireccion());

        if (dto.getComunaId() != null) {
            Comuna comuna = comunaRepository.findById(dto.getComunaId())
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
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

        if (local.getComuna() != null) {
            dto.setComunaId(local.getComuna().getIdComuna());
        }

        return dto;
    }
}

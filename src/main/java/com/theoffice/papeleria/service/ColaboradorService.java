package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.ColaboradorDTO;
import com.theoffice.papeleria.model.Cargo;
import com.theoffice.papeleria.model.Colaborador;
import com.theoffice.papeleria.model.Local;
import com.theoffice.papeleria.repository.CargoRepository;
import com.theoffice.papeleria.repository.ColaboradorRepository;
import com.theoffice.papeleria.repository.LocalRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;
    private CargoRepository cargoRepository;
    private LocalRepository localRepository;

    public List<ColaboradorDTO> obtenerTodos() {
        log.info("Obteniendo lista de colaboradores!");

        return colaboradorRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ColaboradorDTO buscarPorId(Integer id) {
        log.info("Buscando colaborador con ID: {}", id);

        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Colaborador con ID {} no encontrado", id);
                    return new RuntimeException("Colaborador no encontrado");
                });

        return convertirADTO(colaborador);
    }

    public void eliminarColaborador(Integer id) {
        log.info("Intentando eliminar colaborador con ID: {}", id);

        Colaborador colaborador = colaboradorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Colaborador con ID {} no encontrado", id);
                    return new RuntimeException("Colaborador no encontrado");
                });

        colaboradorRepository.delete(colaborador);

        log.info("Colaborador eliminado exitosamente!");
    }

    public ColaboradorDTO guardarColaborador(ColaboradorDTO dto) {
        log.info("Creando colaborador: {}", dto.getNombreColaborador());

        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> {
                    log.error("Cargo con ID {} no encontrado", dto.getCargoId());
                    return new RuntimeException("Cargo no encontrado");
                });

        Local local = localRepository.findById(dto.getLocalId())
                .orElseThrow(() -> {
                    log.error("Local con ID {} no encontrado", dto.getLocalId());
                    return new RuntimeException("Local no encontrado");
                });

        Colaborador colaborador = new Colaborador();
        colaborador.setNombreColaborador(dto.getNombreColaborador());
        colaborador.setCargo(cargo);
        colaborador.setLocal(local);

        Colaborador guardado = colaboradorRepository.save(colaborador);

        log.info("Colaborador guardado con ID: {}", guardado.getIdColaborador());

        return convertirADTO(guardado);
    }

    public ColaboradorDTO actualizarColaborador(Integer id, ColaboradorDTO dto) {
        log.info("Actualizando colaborador con ID: {}", id);

        Colaborador existente = colaboradorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Colaborador con ID {} no encontrado", id);
                    return new RuntimeException("Colaborador no encontrado");
                });

        existente.setNombreColaborador(dto.getNombreColaborador());

        if (dto.getCargoId() != null) {
            Cargo cargo = cargoRepository.findById(dto.getCargoId())
                    .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
            existente.setCargo(cargo);
        }

        if (dto.getLocalId() != null) {
            Local local = localRepository.findById(dto.getLocalId())
                    .orElseThrow(() -> new RuntimeException("Local no encontrado"));
            existente.setLocal(local);
        }

        Colaborador actualizado = colaboradorRepository.save(existente);

        log.info("Colaborador con ID {} actualizado correctamente", id);

        return convertirADTO(actualizado);
    }

    private ColaboradorDTO convertirADTO(Colaborador colaborador){
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setIdColaborador(colaborador.getIdColaborador());
        dto.setNombreColaborador(colaborador.getNombreColaborador());
        return dto;
    }
}

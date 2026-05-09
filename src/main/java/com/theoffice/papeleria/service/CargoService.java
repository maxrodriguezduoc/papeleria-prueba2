package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.CargoDTO;
import com.theoffice.papeleria.model.Cargo;
import com.theoffice.papeleria.repository.CargoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoDTO> obtenerTodos() {
        log.info("Obteniendo lista de todos los cargos disponibles!");

        return cargoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public CargoDTO buscarPorId(Integer id) {
        log.info("Buscando cargo con ID: {}", id);

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Cargo con ID {} no encontrado", id);
                    return new RuntimeException("CARGO NO ENCONTRADO!");
                });

        return convertirADTO(cargo);
    }

    public void eliminarCargo(Integer id) {
        log.info("Intentando eliminar cargo con ID: {}", id);

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede eliminar. Cargo con ID {} no encontrado", id);
                    return new RuntimeException("CARGO NO ENCONTRADO");
                });

        cargoRepository.delete(cargo);

        log.info("Cargo celiminado exitosamente!");
    }

    public CargoDTO guardarCargo(CargoDTO dto) {
        log.info("Guardando nuevo cargo: {}", dto.getNombreCargo());

        Cargo cargo = convertirAEntidad(dto);
        Cargo guardado = cargoRepository.save(cargo);

        log.info("Cargo guardado exitosamente!");

        return convertirADTO(guardado);
    }

    public CargoDTO actualizarCargo(Integer id, CargoDTO dto) {
        log.info("Actualizando cargo con ID: {}", id);

        Cargo cargoExistente = cargoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No se puede actualizar. Cargo con ID {} no encontrado", id);
                    return new RuntimeException("CARGO NO ENCONTRADO");
                });

        cargoExistente.setNombreCargo(dto.getNombreCargo());

        Cargo actualizado = cargoRepository.save(cargoExistente);

        log.info("Cargo actualizado exitosamente!");

        return convertirADTO(actualizado);
    }

    private CargoDTO convertirADTO(Cargo cargo) {
        CargoDTO dto = new CargoDTO();
        dto.setIdCargo(cargo.getIdCargo());
        dto.setNombreCargo(cargo.getNombreCargo());
        return dto;
    }

    private Cargo convertirAEntidad(CargoDTO dto) {
        Cargo cargo = new Cargo();
        cargo.setIdCargo(dto.getIdCargo());
        cargo.setNombreCargo(dto.getNombreCargo());
        return cargo;
    }
}

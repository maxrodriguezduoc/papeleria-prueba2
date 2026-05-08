package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.CargoDTO;
import com.theoffice.papeleria.model.Cargo;
import com.theoffice.papeleria.repository.CargoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    // Obtener una lista de los cargos
    public List<CargoDTO> obtenerTodos(){
        return cargoRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar por Cargo por ID
    public CargoDTO buscarPorId(Integer id){
        Cargo cargo = cargoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("CARGO NO ENCONTRADO!"));
        return convertirADTO(cargo);
    }

    // Metodo para eliminar Cargo
    public String eliminarCargo(Integer id){
        try {
            Cargo cargo = cargoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! CARGO NO ENCONTRADO!"));

            cargoRepository.delete(cargo);
            return "CARGO " + cargo.getNombreCargo() + " ELIMINADO!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar un Cargo
    public Cargo guardarCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    // Metodo para actualizar cargo
    public Cargo actualizarCargo(Integer id, Cargo cargo){
        Cargo cargoExistente = cargoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("CARGO NO ENCONTRADO!"));

        if (cargo.getNombreCargo() != null){
            cargoExistente.setNombreCargo(cargo.getNombreCargo());
        }
        return cargoRepository.save(cargoExistente);
    }

    // Metodo para convertir Entidades en DTO
    private CargoDTO convertirADTO(Cargo cargo){
        CargoDTO dto = new CargoDTO();
        dto.setIdCargo(cargo.getIdCargo());
        dto.setNombreCargo(cargo.getNombreCargo());
        return dto;
    }
}

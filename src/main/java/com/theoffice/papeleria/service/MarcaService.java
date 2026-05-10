package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoffice.papeleria.dto.MarcaDTO;
import com.theoffice.papeleria.model.Marca;
import com.theoffice.papeleria.repository.MarcaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcasRepository;

    public MarcaDTO crearMarca(Marca marca){
        log.info("Intetando registrar una nueva marca: {}", marca.getNombre_marca());
        if (marca.getNombre_marca() == null || marca.getNombre_marca().trim().isEmpty()) {
            log.error("Falla al crear: El nombre está vacío");
            throw new RuntimeException("El nombre de la marca es obligatorio.");
        }

        marca.setNombre_marca(marca.getNombre_marca().trim());
        marca.setActivo(true);
        marcasRepository.save(marca);

        log.info("Marca registrada con exito. Id aignado para la marca es: {}", marca.getId_marcas());
        return convertirADTO(marca);
    }

    public List<MarcaDTO> obtenerTodos(){
        log.info("Consultando listado de marcas activas");
        return marcasRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }


    public MarcaDTO buscarPorId(Integer id){
        log.info("Buscando marca con el ID: {}", id);
        Marca marcas = marcasRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡Marca no encontrado!"));
        return convertirADTO(marcas);

    }

    public void eliminarMarca(Integer id){
        log.info("Esta eliminando la marca con el ID: {}", id);
        Marca marca = marcasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID no encontrado"));

        marca.setActivo(false);
        marcasRepository.save(marca);
    }    

    public MarcaDTO actualizarMarca(Integer id, Marca marcas){
        log.info("Actualizando marca con ID: {}", id);
        Marca marca = marcasRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe la marca con el ID:" + id));

        if(marca.getNombre_marca()!= null){
            marca.setNombre_marca(marca.getNombre_marca().trim());
        }
        marcasRepository.save(marca);
        return convertirADTO(marca);

    }
    

    private MarcaDTO convertirADTO(Marca marcas){
        MarcaDTO dto = new MarcaDTO();
        dto.setId_marcas(marcas.getId_marcas());
        dto.setNombre_marca(marcas.getNombre_marca());
        return dto;
    }














    

}

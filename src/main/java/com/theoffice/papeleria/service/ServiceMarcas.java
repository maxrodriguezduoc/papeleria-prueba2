package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoffice.papeleria.dto.MarcasDTO;
import com.theoffice.papeleria.model.Marcas;
import com.theoffice.papeleria.repository.MarcasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceMarcas {

    @Autowired
    private MarcasRepository marcasRepository;

    public List<MarcasDTO> obtenerTodos(){
        return marcasRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }


    public MarcasDTO buscarPorId(Integer id){
        Marcas marcas = marcasRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("¡Marca no encontrado!"));
        return convertirADTO(marcas);

    }

    public String eliminarMarca(Integer id){
        try {
            Marcas marcas = marcasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! La marca con ID " + id + " no existe."));
            marcasRepository.delete(marcas);
            return "El nombre de la marca  '" + marcas.getNombre_marca() + "' ha sido retirada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Marcas guardarMarca (Marcas marcas){
        return marcasRepository.save(marcas);
    }

    public Marcas actualizarMarca(Integer id, Marcas marcas){
        Marcas marca = marcasRepository.findById(id).orElseThrow(() -> new RuntimeException("¡La marca  no existe en los registros!"));

        if(marca.getNombre_marca()!= null){
            marca.setNombre_marca(marca.getNombre_marca());
        }
        return marcasRepository.save(marca);
        
    }

    private MarcasDTO convertirADTO(Marcas marcas){
        MarcasDTO dto = new MarcasDTO();
        dto.setId_marcas(marcas.getId_marcas());
        dto.setNombre_marca(marcas.getNombre_marca());
        return dto;
    }














    

}

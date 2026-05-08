package com.theoffice.papeleria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.LocalDTO;
import com.theoffice.papeleria.model.Local;
import com.theoffice.papeleria.repository.LocalRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    // Obtener una lista de los locales
    public List<LocalDTO> obtenerTodos(){
        return localRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar Local por ID
    public LocalDTO buscarPorId(Integer id){
        Local local = localRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("LOCAL NO ENCONTRADO!"));
        return convertirADTO(local);
    }

    // Metodo para eliminar Locales
    public String eliminarCargo(Integer id){
        try {
            Local local = localRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! LOCAL NO ENCONTRADO!"));

            localRepository.delete(local);
            return "LOCAL " + local.getNombreLocal() + " ELIMINADO!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar un Local
    public Local guardarLocal(Local local){
        return localRepository.save(local);
    }

    // Metodo para actualizar Locales
    public Local actualizarLocal(Integer id, Local local){
        Local localExistente = localRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("LOCAL NO ENCONTRADO!"));

        if (local.getNombreLocal() != null){
            localExistente.setNombreLocal(local.getNombreLocal());
        }
        if (local.getDireccion() != null){
            localExistente.setDireccion(local.getDireccion());
        }
        return localRepository.save(localExistente);
    }

    // Metodo para convertir Entidades en DTO
    private LocalDTO convertirADTO(Local local){
        LocalDTO dto = new LocalDTO();
        dto.setIdLocal(local.getIdLocal());
        dto.setNombreLocal(local.getNombreLocal());
        dto.setDireccion(local.getDireccion());
        return dto;
    }

}

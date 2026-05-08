package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.ColaboradorDTO;
import com.theoffice.papeleria.model.Colaborador;
import com.theoffice.papeleria.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    // Obtener una lista de los colaboradores
    public List<ColaboradorDTO> obtenerTodos(){
        return colaboradorRepository.findAll().stream()
                    .map(this::convertirADTO)
                    .toList();
    }

    // Metodo para buscar por Colaboradores por ID
    public ColaboradorDTO buscarPorId(Integer id){
        Colaborador colaborador = colaboradorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("COLABORADOR NO ENCONTRADO!"));
        return convertirADTO(colaborador);
    }

    // Metodo para eliminar Colaboradores
    public String eliminarColaborador(Integer id){
        try {
            Colaborador colaborador = colaboradorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NO SE PUEDE ELIMINAR! COLABORADOR NO ENCONTRADO!"));

            colaboradorRepository.delete(colaborador);
            return "COLABORADOR " + colaborador.getNombreColaborador() + " ELIMINADO!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Metodo para publicar un Colaborador
    public Colaborador guardarColaborador(Colaborador colaborador){
        return  colaboradorRepository.save(colaborador);
    }

    // Metodo para actualizar Colaboradores
    public Colaborador actualizarColaborador(Integer id, Colaborador colaborador){
        Colaborador colaboradorExistente = colaboradorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("COLABORADOR NO ENCONTRADO!"));

        if (colaborador.getNombreColaborador() != null){
            colaboradorExistente.setNombreColaborador(colaborador.getNombreColaborador());
        }
        return colaboradorRepository.save(colaboradorExistente);
    }

    // Metodo para convertir Entidades en DTO
    private ColaboradorDTO convertirADTO(Colaborador colaborador){
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setIdColaborador(colaborador.getIdColaborador());
        dto.setNombreColaborador(colaborador.getNombreColaborador());
        return dto;
    }

}

package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoffice.papeleria.dto.ColorDTO;
import com.theoffice.papeleria.model.Color;
import com.theoffice.papeleria.repository.ColoresRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceColores {

    @Autowired
    private ColoresRepository coloresRepository;

    public List<ColorDTO> obtenerTodos() {
        return coloresRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }


    public ColorDTO buscarColorPorId(Integer id){
        Color color = coloresRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Color no encontrado!"));
        return convertirADTO(color);
    }

    public String eliminar(Integer id) {
        try {
            Color color = coloresRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El color con ID " + id + " no existe."));
            coloresRepository.delete(color);
            return "El color '" + color.getNombre_color() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Color guardarColor(Color color) {
        return coloresRepository.save(color);
    }

    public Color actualizarColor(Integer id,Color colores){
        Color color = coloresRepository.findById(id).orElseThrow(() -> new RuntimeException("¡El héroe no existe en los registros!"));
        if(color.getNombre_color() != null){
            color.setNombre_color(color.getNombre_color());
        }
        return coloresRepository.save(color);
    }


    private ColorDTO convertirADTO(Color color){
        ColorDTO dto = new ColorDTO();
        dto.setId_color(color.getId_color());
        dto.setNombre_color(color.getNombre_color());
        return dto;
    }










}

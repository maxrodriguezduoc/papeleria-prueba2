package com.theoffice.papeleria.service;

import com.theoffice.papeleria.dto.CategoriasDTO;
import com.theoffice.papeleria.model.Categorias;
import com.theoffice.papeleria.repository.CategoriasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public CategoriasDTO crear(Categorias categorias) {
        log.info("Intentando asociar Producto ID: {} con Categoría ID: {}", 
                 categorias.getProducto().getId_productos(), 
                 categorias.getCategoria().getIdCategoria());

        categorias.setActivo(true);

        Categorias guardado = categoriasRepository.save(categorias);
        log.info("Asociación registrada con éxito. ID de puente asignado: {}", guardado.getIdCategorias());

        return convertirADTO(guardado);
    }

    public List<CategoriasDTO> obtenerTodas() {
        log.info("Consultando el listado de asociaciones de categorías activas");
        
        return categoriasRepository.findAll().stream()
                .filter(Categorias::isActivo)
                .map(this::convertirADTO)
                .toList();
    }

    public CategoriasDTO obtenerPorId(Integer id) {
        log.info("Buscando asociación puente con ID: {}", id);
        
        Categorias categorias = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la asociación con ID: " + id));

        if (!categorias.isActivo()) {
            log.warn("La asociación puente ID {} está marcada como inactiva", id);
            throw new RuntimeException("La asociación solicitada ya no está disponible.");
        }

        return convertirADTO(categorias);
    }

    public CategoriasDTO actualizar(Integer id, Categorias categorias) {
        log.info("Iniciando actualización para la asociación puente ID: {}", id);
        
        Categorias existente = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asociación no encontrada."));

        if (!existente.isActivo()) {
            throw new RuntimeException("No se puede modificar una asociación inactiva.");
        }

        existente.setProducto(categorias.getProducto());
        existente.setCategoria(categorias.getCategoria());

        categoriasRepository.save(existente);
        log.info("Asociación puente ID {} modificada con éxito", id);

        return convertirADTO(existente);
    }

    public void eliminar(Integer id) {
        log.warn("Procesando baja lógica para la asociación puente ID: {}", id);
        
        Categorias categorias = categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede desasociar: El ID ingresado no existe."));

        if (!categorias.isActivo()) {
            log.info("La asociación ID {} ya se encontraba inactiva", id);
            return;
        }

        categorias.setActivo(false);
        categoriasRepository.save(categorias);
        
        log.info("La asociación ID {} ha sido desactivada correctamente", id);
    }

    private CategoriasDTO convertirADTO(Categorias categorias) {
        CategoriasDTO dto = new CategoriasDTO();
        dto.setIdCategorias(categorias.getIdCategorias());
        dto.setIdProducto(categorias.getProducto().getId_productos());
        dto.setNombreProducto(categorias.getProducto().getNombre_producto());
        dto.setIdCategoria(categorias.getCategoria().getIdCategoria());
        dto.setNombreCategoria(categorias.getCategoria().getNombre());
        dto.setActivo(categorias.isActivo());
        return dto;
    }
}
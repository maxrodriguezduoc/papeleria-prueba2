package com.theoffice.papeleria.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theoffice.papeleria.dto.TipoProductosDTO;
import com.theoffice.papeleria.model.TiposProductos;
import com.theoffice.papeleria.repository.TipoProductoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class ServiceTipoProductos {

    @Autowired
    private TipoProductoRepository  tipo_productosRepository;

    
    public List<TipoProductosDTO> obtenerTodos() {
        return tipo_productosRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }
   
    public TipoProductosDTO buscarPorId(Integer id){
        TiposProductos tipo_productos = tipo_productosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("El tipo de producto no encontrado"));
        return convertirADTO(tipo_productos);
    }
    
    public String eliminar (Integer id){
        try {
            TiposProductos tipo_productos = tipo_productosRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El tipo de producto  con ID " + id + " no existe."));
            tipo_productosRepository.delete(tipo_productos);
            return "El tipo de producto '" + tipo_productos.getNombre() + "' ha sido retirado exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
    
    public TiposProductos guardarTipoProd(TiposProductos tipos_productos) {
        return tipo_productosRepository.save(tipos_productos);
    }


    public TiposProductos actualizarTiposProductos(Integer id, TiposProductos tipos_productos){
        TiposProductos tipo_productos = tipo_productosRepository.findById(id).orElseThrow(() -> new RuntimeException("¡El TIPO DE PRODUCTO no existe en los registros!"));
        if (tipo_productos.getNombre() != null) {
            tipo_productos.setNombre(tipo_productos.getNombre());
            
        }
        return tipo_productosRepository.save(tipo_productos);
    }
    
    private TipoProductosDTO convertirADTO(TiposProductos tipo_productos){
        TipoProductosDTO dto = new TipoProductosDTO();
        dto.setId_tipo_producto(tipo_productos.getId_tipo_producto());
        dto.setNombre(tipo_productos.getNombre());
        return dto;
    }















}

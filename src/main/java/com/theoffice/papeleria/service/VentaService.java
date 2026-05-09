package com.theoffice.papeleria.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoffice.papeleria.dto.ProductoDTO;
import com.theoffice.papeleria.dto.VentaDTO;
import com.theoffice.papeleria.model.Producto;
import com.theoffice.papeleria.model.Venta;
import com.theoffice.papeleria.repository.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoService productoService;
    
    public VentaDTO crear(Venta venta, Integer idProducto) {
        log.info("Iniciando creación de venta para Producto ID: {}", idProducto);
        
        // A. Buscamos el producto primero (Igual que como buscas en otros servicios)
        ProductoDTO producto = productoService.buscarPorId(idProducto);

        // B. Validaciones de Regla de Negocio
        if (producto.getStock() < venta.getCantidad()) {
            log.error("Error de stock: Pedido {}, Disponible {}", venta.getCantidad(), producto.getStock());
            throw new RuntimeException("No hay suficiente stock para realizar esta venta.");
        }

        venta.setTotal_venta(producto.getPrecio_producto() * venta.getCantidad());
        venta.setActivo(true);

        Producto productoUpdate = new Producto();
        productoUpdate.setNombre_producto(producto.getNombre_producto());
        productoUpdate.setPrecio_producto(producto.getPrecio_producto());
        productoUpdate.setStock(producto.getStock() - venta.getCantidad());
        productoUpdate.setActivo(true);
        
        productoService.actualizarProducto(idProducto, productoUpdate);

        Venta ventaGuardada = ventaRepository.save(venta);
        
        log.info("Venta creada exitosamente con ID: {}", ventaGuardada.getId_venta());
        
        return convertirADTO(ventaGuardada);
    }


    //es¿ditado
    public List<VentaDTO> obtenerTodos() {
        log.info("Consultando el listado de ventas");
        return ventaRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }
    //editado

    public VentaDTO buscarPorId(Integer id) {
        log.info("Buscando venta con el ID: {}",id);
        Venta ventas = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Venta no encontrada!"));
        return convertirADTO(ventas);
    }
    //editado
    public void eliminar(Integer id) {
        log.info("Esta eliminando la venta con el ID: {}",id);
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id de venta no encontrada"));
        venta.setActivo(false);
        ventaRepository.save(venta);
    }

    public VentaDTO actualizarVentas(Integer id,Venta ventas){
        log.info("Actualizando venta con ID: {}", id);
        Venta venta = ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("¡La venta no existe en los registros!"));
        if(ventas.getCantidad() != null){
            venta.setCantidad(ventas.getCantidad());
        }
        if(ventas.getTotal_venta() != null){
            venta.setTotal_venta(ventas.getTotal_venta());
        }
        ventaRepository.save(ventas);
        return convertirADTO(venta);
    }

    private VentaDTO convertirADTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId_venta(venta.getId_venta());
        dto.setCantidad(venta.getCantidad());
        dto.setTotal_venta(venta.getTotal_venta());
        dto.setFecha_venta(venta.getFecha_venta());
        dto.setActivo(venta.isActivo());
        dto.setNombreProducto(venta.getProducto().getNombre_producto());
        return dto;
    }





}

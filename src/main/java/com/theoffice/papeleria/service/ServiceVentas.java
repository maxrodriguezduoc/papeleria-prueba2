package com.theoffice.papeleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theoffice.papeleria.dto.VentasDTO;
import com.theoffice.papeleria.model.Ventas;
import com.theoffice.papeleria.repository.VentasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceVentas {

    @Autowired
    private VentasRepository ventasRepository;

    public List<VentasDTO> obtenerTodos() {
        return ventasRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public VentasDTO buscarPorId(Integer id) {
        Ventas ventas = ventasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Venta no encontrado!"));
        return convertirADTO(ventas);
    }


    public String eliminar(Integer id) {
        try {
            Ventas ventas = ventasRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El héroe con ID " + id + " no existe."));
            ventasRepository.delete(ventas);
            return "La venta '" + id + "' ha sido retirada exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Ventas guardarVentas(Ventas ventas) {
        return ventasRepository.save(ventas);
    }



    public Ventas actualizarVentas(Integer id,Ventas ventas){
        Ventas venta = ventasRepository.findById(id).orElseThrow(() -> new RuntimeException("¡La venta no existe en los registros!"));
        if(ventas.getCantidad() != null){
            venta.setCantidad(ventas.getCantidad());
        }
        if(ventas.getTotal_venta() != null){
            venta.setTotal_venta(ventas.getTotal_venta());
        }
        return ventasRepository.save(ventas);
    }

    private VentasDTO convertirADTO(Ventas ventas){
        VentasDTO dto = new VentasDTO();
        dto.setId_venta(ventas.getId_venta());
        dto.setCantidad(ventas.getCantidad());
        dto.setTotal_venta(ventas.getTotal_venta());
        return dto;
    }





}

package com.theoffice.papeleria.service;

import com.theoffice.papeleria.dto.PagoDTO;
import com.theoffice.papeleria.model.Pago;
import com.theoffice.papeleria.model.Ventas;
import com.theoffice.papeleria.repository.PagoRepository;
import com.theoffice.papeleria.repository.VentasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private VentasRepository ventasRepository;

    public PagoDTO crear(Pago pago) {
        log.info("Iniciando registro de pago para la Venta ID: {}", pago.getVenta().getId_venta());

        Ventas venta = ventasRepository.findById(pago.getVenta().getId_venta())
                .orElseThrow(() -> new RuntimeException("La venta asociada al pago no existe."));

        String tipo = pago.getTipoPago().getFormaPago();
        if ("Tarjeta".equalsIgnoreCase(tipo) && pago.getTarjeta() == null) {
            log.error("Falla al pagar: Se seleccionó Tarjeta pero el objeto Tarjeta viene nulo");
            throw new RuntimeException("Debe proporcionar los datos de la tarjeta para procesar este pago.");
        }
        if ("Transferencia".equalsIgnoreCase(tipo) && pago.getTransferencia() == null) {
            log.error("Falla al pagar: Se seleccionó Transferencia pero el objeto Transferencia viene nulo");
            throw new RuntimeException("Debe proporcionar los datos de la transferencia para procesar este pago.");
        }

        pago.setActivo(true);
        Pago guardado = pagoRepository.save(pago);
        
        log.info("Pago de ${} registrado con éxito para la Venta ID: {}. ID de Transacción: {}", 
                 guardado.getMonto(), venta.getId_venta(), guardado.getIdPago());

        return convertirADTO(guardado);
    }

    public List<PagoDTO> obtenerPagosPorVenta(Integer idVenta) {
        log.info("Buscando historial de pagos activos para la Venta ID: {}", idVenta);
        
        return pagoRepository.findByVentaIdVentaAndActivoTrue(idVenta).stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO obtenerPorId(Integer id) {
        log.info("Buscando pago con ID: {}", id);
        
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el registro de pago con ID: " + id));

        if (!pago.isActivo()) {
            log.warn("El pago ID {} está marcado como inactivo", id);
            throw new RuntimeException("El registro de pago ya no está disponible.");
        }

        return convertirADTO(pago);
    }

    public void eliminar(Integer id) {
        log.warn("Anulando de forma lógica el pago con ID: {}", id);
        
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede anular: El ID de pago no existe."));

        if (!pago.isActivo()) {
            log.info("El pago ID {} ya se encontraba inactivo", id);
            return;
        }

        pago.setActivo(false);
        pagoRepository.save(pago);
        
        log.info("Pago ID {} desactivado correctamente en el sistema", id);
    }

    private PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setIdPago(pago.getIdPago());
        dto.setIdVenta(pago.getVenta().getId_venta());
        dto.setTotalVenta(pago.getVenta().getTotal_venta());
        dto.setTipoPagoNombre(pago.getTipoPago().getFormaPago());
        dto.setMontoPagado(pago.getMonto());
        dto.setActivo(pago.isActivo());
        return dto;
    }
}
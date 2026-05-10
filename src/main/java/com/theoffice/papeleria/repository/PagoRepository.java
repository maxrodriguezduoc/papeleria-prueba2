package com.theoffice.papeleria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theoffice.papeleria.model.Pago;

public interface PagoRepository extends JpaRepository <Pago, Integer> {
    List<Pago> findByVentaIdVentaAndActivoTrue(Integer idVenta);
}

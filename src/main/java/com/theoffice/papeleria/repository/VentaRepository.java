package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository <Venta, Integer> {

}

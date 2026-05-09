package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.TipoPago;

@Repository
public interface TipoPagoRepository extends JpaRepository <TipoPago, Integer>{

}

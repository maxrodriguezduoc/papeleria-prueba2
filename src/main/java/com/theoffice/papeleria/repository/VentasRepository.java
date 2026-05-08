package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository <Ventas, Integer> {

}

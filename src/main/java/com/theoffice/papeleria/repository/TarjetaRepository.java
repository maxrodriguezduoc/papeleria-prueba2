package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository <Tarjeta, Integer>{

}

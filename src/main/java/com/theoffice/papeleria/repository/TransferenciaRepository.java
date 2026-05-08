package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository <Transferencia, Integer>{

}

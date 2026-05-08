package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theoffice.papeleria.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>{

}

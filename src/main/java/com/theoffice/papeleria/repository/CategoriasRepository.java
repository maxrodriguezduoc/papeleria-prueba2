package com.theoffice.papeleria.repository;

import com.theoffice.papeleria.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

}
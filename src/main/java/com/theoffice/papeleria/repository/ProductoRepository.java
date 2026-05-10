package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.theoffice.papeleria.model.Productos;
@Repository
public interface ProductoRepository extends JpaRepository <Productos, Integer>  {

}

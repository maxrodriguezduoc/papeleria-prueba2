package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.theoffice.papeleria.model.Producto;
@Repository
public interface ProductoRepository extends JpaRepository <Producto, Integer>  {

}

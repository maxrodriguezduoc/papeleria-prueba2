package com.theoffice.papeleria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.theoffice.papeleria.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer>{

}

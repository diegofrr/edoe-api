package com.projetodsc.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.model.Descritor;

@Repository
public interface DescritoresRepository extends JpaRepository<Descritor, Long>{

}

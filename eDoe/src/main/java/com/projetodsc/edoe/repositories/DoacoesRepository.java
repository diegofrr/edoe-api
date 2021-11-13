package com.projetodsc.edoe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.models.dtos.Doacao;

@Repository
public interface DoacoesRepository extends JpaRepository<Doacao, Long>{

	
}

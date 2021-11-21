package com.apiedoe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiedoe.models.Doacao;

@Repository
public interface DoacoesRepository extends JpaRepository<Doacao, Long>{

	
}

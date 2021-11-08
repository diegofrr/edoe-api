package com.projetodsc.edoe.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.model.Descritor;

@Repository
public interface DescritoresRepository extends JpaRepository<Descritor, String>{

	Optional<Descritor> findByDescricao(String descricao);
	boolean existsByDescricao(String descricao);
	
}

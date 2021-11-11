package com.projetodsc.edoe.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetodsc.edoe.models.Descritor;

@Repository
public interface DescritoresRepository extends JpaRepository<Descritor, String>{

	Optional<Descritor> findByDescricao(String descricao);
	boolean existsByDescricao(String descricao);
	Optional<List<Descritor>> findAllByDescricao(String descricao);
	
}

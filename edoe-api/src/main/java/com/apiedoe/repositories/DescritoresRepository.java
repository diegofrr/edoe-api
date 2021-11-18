package com.apiedoe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiedoe.models.Descritor;

@Repository
public interface DescritoresRepository extends JpaRepository<Descritor, String>{

	Optional<Descritor> findByDescricaoIgnoreCase(String descricao);
	boolean existsByDescricaoIgnoreCase(String descricao);
	Optional<List<Descritor>> findAllByDescricaoIgnoreCase(String descricao);
	Optional<List<Descritor>> findByDescricaoIgnoreCaseContaining(String descricao);
	
}

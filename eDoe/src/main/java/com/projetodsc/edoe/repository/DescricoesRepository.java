package com.projetodsc.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetodsc.edoe.model.DescricaoItem;

public interface DescricoesRepository extends JpaRepository<DescricaoItem, String>{

	
}

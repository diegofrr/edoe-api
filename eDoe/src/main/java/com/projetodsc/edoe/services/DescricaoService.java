package com.projetodsc.edoe.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetodsc.edoe.model.DescricaoItem;
import com.projetodsc.edoe.repository.DescricoesRepository;

public class DescricaoService {
	
	@Autowired
	private DescricoesRepository repositorio;
	
	public DescricaoItem addDescricao(DescricaoItem descricao) {
		return repositorio.save(descricao);
	}

}

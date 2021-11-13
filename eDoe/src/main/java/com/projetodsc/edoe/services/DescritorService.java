package com.projetodsc.edoe.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetodsc.edoe.exceptions.DescritorInvalidoException;
import com.projetodsc.edoe.exceptions.DescritorJaExisteException;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.repositories.DescritoresRepository;

@Service
public class DescritorService {

	@Autowired
	private DescritoresRepository repositorio;

	public List<Descritor> getDescritores() {
		return repositorio.findAll();
	}

	public List<Descritor> getDescritoresByDescricao(String descricao) {
		return repositorio.findAllByDescricaoIgnoreCase(descricao).get();
	}

	public Descritor save(Descritor descritor) {
		return repositorio.save(descritor);
	}

	public Descritor addDescritor(Descritor descritor) {
		descritor.setDescricao(descritor.getDescricao().toUpperCase());
		if (descritor.getDescricao() == null || descritor.getDescricao().isBlank()
				|| descritor.getDescricao().isEmpty())
			throw new DescritorInvalidoException("Descritor inválido", "O descritor não pode ficar em branco.");

		if (repositorio.existsByDescricaoIgnoreCase(descritor.getDescricao()))
			throw new DescritorJaExisteException("Descritor já existe!", "Este descritor já existe no sistema.");
		return repositorio.save(descritor);
	}

	public boolean existsByDescricao(String descricoa) {
		return repositorio.existsByDescricaoIgnoreCase(descricoa);
	}

	public List<Descritor> getDescritoresByDescricaoContaining(String string_busca) {
		return repositorio.findByDescricaoIgnoreCaseContaining(string_busca).get();
	}

}

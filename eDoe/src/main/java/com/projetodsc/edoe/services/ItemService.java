package com.projetodsc.edoe.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exception.DescritorNaoExisteException;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.dto.ItemDTO;
import com.projetodsc.edoe.repository.DescritoresRepository;
import com.projetodsc.edoe.repository.ItensRepository;

@Service
public class ItemService {

	@Autowired
	private ItensRepository repositorio;
	
	@Autowired
	private DescritoresRepository descRepositorio;

	public Item addItem(ItemDTO item) {
		if (!descRepositorio.existsByDescricao(item.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe", "Este descritor não está cadastrado no sistema.");
		item.setDescritor(descRepositorio.findByDescricao(item.getDescritor().getDescricao()).get());
		return repositorio.save(item.getItem());
	}

	public List<Item> getItens() {
		return repositorio.findAll();
	}
	
}

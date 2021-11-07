package com.projetodsc.edoe.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		descRepositorio.save(item.getDescritor());
		return repositorio.save(item.getItem());
	}

	public List<Item> getItens() {
		return repositorio.findAll();
	}

}

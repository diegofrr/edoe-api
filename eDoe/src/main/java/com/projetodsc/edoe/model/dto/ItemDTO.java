package com.projetodsc.edoe.model.dto;
import java.util.List;

import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ItemDTO {
	
	private String nome;
	private List<Descritor> descritores;
	private int quantidade;
	private boolean disponivel = false;
	
	public ItemDTO() {}
	
	public ItemDTO(String nome, List<Descritor> descritores, int quantidade) {
		this.nome = nome;
		this.descritores = descritores;
		this.quantidade = quantidade;
		if (quantidade > 0)
			this.disponivel = true;
	}
	
	public Item getItem() {
		return new Item(this.nome, this.descritores, this.quantidade, this.disponivel);
	}


}

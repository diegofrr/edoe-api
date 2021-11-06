package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.DescricaoItem;
import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ItemDTO {
	
	private String nome;
	private int idDescricao;
	private int quantidade;
	private boolean disponivel;
	
	public ItemDTO() {}
	
	public ItemDTO(String nome, int idDescricao, int quantidade) {
		this.nome = nome;
		this.idDescricao = idDescricao;
		this.quantidade = quantidade;
	}
	
	public Item getItem() {
		return new Item(this.nome, this.idDescricao, this.quantidade);
	}


}

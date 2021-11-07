package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ItemDTO {

	private String nome;
	private Descritor descritor;
	private int quantidade;

	public ItemDTO() {
	}

	public ItemDTO(String nome, Descritor descritor, int quantidade) {
		this.nome = nome;
		this.descritor = descritor;
		this.quantidade = quantidade;
	}

	public Item getItem() {
		return new Item(this.nome, this.descritor, this.quantidade);
	}

}

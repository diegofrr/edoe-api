package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ItemDTO {

	private String nome;
	private String descricaoDetalhada;
	private Descritor descritor;
	private int quantidade;

	public ItemDTO() {
	}

	public ItemDTO(String nome, String descricaoDetalhada, Descritor descritor, int quantidade) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.descritor = descritor;
		this.quantidade = quantidade;
	}

	public Item getItem() {
		return new Item(this.nome, this.descricaoDetalhada, this.descritor, this.quantidade);
	}

}

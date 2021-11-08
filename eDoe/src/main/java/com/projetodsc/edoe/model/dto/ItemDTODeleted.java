package com.projetodsc.edoe.model.dto;

import lombok.Data;

@Data
public class ItemDTODeleted {
	
	private String nome;
	private String descricaoDetalhada;
	
	public ItemDTODeleted() {}
	
	public ItemDTODeleted(String nome, String descricaoDetalhada) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
	}

}

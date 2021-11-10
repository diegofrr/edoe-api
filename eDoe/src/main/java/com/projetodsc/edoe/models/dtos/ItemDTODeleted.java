package com.projetodsc.edoe.models.dtos;

import lombok.Data;

@Data
public class ItemDTODeleted {
	
	private String nome;
	private String descricao;
	
	public ItemDTODeleted() {}
	
	public ItemDTODeleted(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

}

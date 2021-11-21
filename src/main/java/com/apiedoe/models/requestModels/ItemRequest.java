package com.apiedoe.models.requestModels;

import com.apiedoe.models.Descritor;
import lombok.Data;

@Data
public class ItemRequest {
	
	private String nome;
	private String descricaoDetalhada;
	private int quantidade;
	private Descritor descritor;

	public ItemRequest() {}
	
	public ItemRequest(String nome, String descricaoDetalhada, int quantidade, Descritor descritor) {
		this.nome = nome.toUpperCase();
		this.descricaoDetalhada = descricaoDetalhada.toUpperCase();
		this.quantidade = quantidade;
		this.descritor = descritor;
	}
}

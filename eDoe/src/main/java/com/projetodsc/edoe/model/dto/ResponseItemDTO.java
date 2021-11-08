package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ResponseItemDTO {
	
	private String nome;
	private String descricao;
	private ResponseDoadorDTO doador;
	
	public ResponseItemDTO(Item item, ResponseDoadorDTO doador) {
		this.nome = item.getNome();
		this.descricao = item.getDescricaoDetalhada();
		this.doador = doador;
	}

}

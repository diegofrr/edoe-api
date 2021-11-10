package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ResponseItemDTO {
	
	private String nome;
	private String descricao;
	private int quantidadeDoacao;
	private ResponseDoadorDTO doador;
	
	public ResponseItemDTO(ItemDoacao item, Usuario doador) {
		this.nome = item.getNome();
		this.descricao = item.getDescricaoDetalhada();
		this.quantidadeDoacao = item.getQuantidadeDoacao();
		this.doador = new ResponseDoadorDTO(doador);
	}

}

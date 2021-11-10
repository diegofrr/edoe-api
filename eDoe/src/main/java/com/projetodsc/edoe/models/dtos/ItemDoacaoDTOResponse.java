package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ItemDoacaoDTOResponse {
	
	private String nome;
	private String descricao;
	private int quantidadeDoacao;
	private DoadorDTOResponse doador;
	
	public ItemDoacaoDTOResponse(ItemDoacao item, Usuario usuario) {
		this.nome = item.getNome();
		this.descricao = item.getDescricaoDetalhada();
		this.quantidadeDoacao = item.getQuantidadeDoacao();
		this.doador = new DoadorDTOResponse(usuario);
	}
}

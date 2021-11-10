package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.ItemDoacao;
import com.projetodsc.edoe.model.ItemNecessario;
import com.projetodsc.edoe.model.Usuario;

import lombok.Data;

@Data
public class ItemDoacaoDTO {

	private String nome;
	private String descricaoDetalhada;
	private Descritor descritor;
	private int quantidadeDoacao;
	private UsuarioDTO doador;

	public ItemDoacaoDTO() {
	}
	
	public ItemDoacaoDTO(ItemDoacao item) {
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidadeDoacao = item.getQuantidadeDoacao();
		this.descritor = item.getDescritor();
		this.doador = new UsuarioDTO(item.getDoador());
	}
	
	public ItemDoacaoDTO(String nome, String descricaoDetalhada, Descritor descritor, int quantidadeDoacao) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.descritor = descritor;
		this.quantidadeDoacao = quantidadeDoacao;
	}
	
	public ItemDoacaoDTO(String nome, String descricaoDetalhada) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public ItemDoacao getItem() {
		return new ItemDoacao(this.nome, this.descricaoDetalhada, this.descritor, this.quantidadeDoacao);
	}

}

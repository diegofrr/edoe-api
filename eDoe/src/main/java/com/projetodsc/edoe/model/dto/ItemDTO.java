package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.Usuario;

import lombok.Data;

@Data
public class ItemDTO {

	private String nome;
	private String descricaoDetalhada;
	private Descritor descritor;
	private int quantidadeDoacao;
	private UsuarioDTO doador;

	public ItemDTO() {
	}
	
	public ItemDTO(Item item) {
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidadeDoacao = item.getQuantidadeDoacao();
		this.descritor = item.getDescritor();
		this.doador = new UsuarioDTO(item.getDoador());
	}
	
	public ItemDTO(String nome, String descricaoDetalhada, Descritor descritor, int quantidadeDoacao) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.descritor = descritor;
		this.quantidadeDoacao = quantidadeDoacao;
	}
	
	public ItemDTO(String nome, String descricaoDetalhada) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public Item getItem() {
		return new Item(this.nome, this.descricaoDetalhada, this.descritor, this.quantidadeDoacao);
	}

}

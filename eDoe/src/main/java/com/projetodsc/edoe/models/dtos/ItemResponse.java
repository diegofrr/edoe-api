package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.Item;
import com.projetodsc.edoe.models.TipoItem;

import lombok.Data;

@Data
public class ItemResponse {
	private String nome;
	private String descricao;
	private int quantidade;
	private Descritor descritor;
	private TipoItem tipo;
	private UsuarioResponse usuario;

	public ItemResponse(ItemDTO item) {
		this.nome = item.getNome();
		this.descricao = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.tipo = item.getTipo();
		this.usuario = new UsuarioResponse(item.getUsuario());
	}

	public ItemResponse(Item item) {
		this.nome = item.getNome();
		this.descricao = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.tipo = item.getTipo();
		this.usuario = new UsuarioResponse(item.getUsuario());
	}

}

package com.apiedoe.models.responseModels;

import com.apiedoe.models.Descritor;
import com.apiedoe.models.Item;
import com.apiedoe.models.TipoItem;
import com.apiedoe.models.dtos.ItemDTO;

import lombok.Data;

@Data
public class ItemResponse {
	private long id;
	private String nome;
	private String descricaoDetalhada;
	private int quantidade;
	private Descritor descritor;
	private TipoItem tipo;
	private UsuarioResponse usuario;

	public ItemResponse() {}
	
	public ItemResponse(ItemDTO item) {
		this.id = item.getItem().getId();
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.tipo = item.getTipo();
		this.usuario = new UsuarioResponse(item.getUsuario());
	}

	public ItemResponse(Item item) {
		this.id = item.getId();
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.tipo = item.getTipo();
		this.usuario = new UsuarioResponse(item.getUsuario());
	}

}

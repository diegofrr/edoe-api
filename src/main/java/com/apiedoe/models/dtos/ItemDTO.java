package com.apiedoe.models.dtos;


import com.apiedoe.models.Descritor;
import com.apiedoe.models.Item;
import com.apiedoe.models.TipoItem;
import com.apiedoe.models.Usuario;

import lombok.Data;

@Data
public class ItemDTO {

	private String nome;
	private String descricaoDetalhada;
	private int quantidade;
	private Descritor descritor;
	private Usuario usuario;
	private TipoItem tipo;

	public ItemDTO() {
	}

	public ItemDTO(String nome, String descricaoDetalhada, int quantidade, Descritor descritor, Usuario usuario,
			TipoItem tipo) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.quantidade = quantidade;
		this.descritor = descritor;
		this.usuario = usuario;
		this.tipo = tipo;
	}

	public ItemDTO(Item item) {
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.usuario = item.getUsuario();
		this.tipo = item.getTipo();
	}

	public Item getItem() {
		return new Item(this.nome, this.descricaoDetalhada, this.quantidade, this.descritor, this.usuario, this.tipo);
	}

}
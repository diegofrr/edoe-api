package com.apiedoe.models.dtos;


import com.apiedoe.models.Descritor;
import com.apiedoe.models.Item;
import com.apiedoe.models.TipoItem;
import com.apiedoe.models.Usuario;
import com.apiedoe.models.requestModels.ItemReq;

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
	
	public ItemDTO(ItemReq item, TipoItem tipo) {
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidade = item.getQuantidade();
		this.descritor = item.getDescritor();
		this.tipo = tipo;
	}

	public Item getItem() {
		return new Item(this.nome, this.descricaoDetalhada, this.quantidade, this.descritor, this.usuario, this.tipo);
	}

}
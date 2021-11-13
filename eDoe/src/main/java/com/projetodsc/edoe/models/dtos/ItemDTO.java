package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.Item;
import com.projetodsc.edoe.models.TipoItem;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ItemDTO {
	
	private String nome;
	private String descricaoDetalhada;
	private int quantidade;
	private Descritor descritor;
	private Usuario usuario;
	private TipoItem tipo;
	
	public ItemDTO() {}
	
	public ItemDTO(String nome, String descricaoDetalhada, int quantidade, Descritor descritor, Usuario usuario, TipoItem tipo) {
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

package com.projetodsc.edoe.model.dto;

import com.projetodsc.edoe.model.DescricaoItem;
import com.projetodsc.edoe.model.Item;
import lombok.Data;

@Data
public class ItemDTO {
	
	private String nome;
	private DescricaoItem descricao;
	private int quantidade;
	private boolean disponivel;
	
	public ItemDTO() {}
	
	public ItemDTO(String nome, DescricaoItem descricao, int quantidade) {
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public Item getItem() {
		return new Item(this.nome, this.descricao, this.quantidade);
	}
	
	public String toString() {
		return "Nome:" + nome + "\n" +
				"Descricao: " + descricao.toString() + "\n" +
				"Quantidade: " + quantidade + "\n";

	}
	

	

}

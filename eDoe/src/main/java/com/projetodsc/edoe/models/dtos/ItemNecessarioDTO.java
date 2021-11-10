package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemNecessario;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ItemNecessarioDTO {
	
	private String nome;
	private String motivacao;
	private int quantidadeNecessaria;
	private Descritor descritor;
	
	public ItemNecessarioDTO() {}
	
	public ItemNecessarioDTO(String nome, String motivacao, int quantidade, Descritor descritor) {
		this.nome = nome;
		this.motivacao = motivacao;
		this.quantidadeNecessaria = quantidade;
		this.descritor = descritor;
	}
	
	public ItemNecessario getItemNecessario() {
		return new ItemNecessario(this.nome, this.motivacao, this.quantidadeNecessaria, this.descritor);
	}

}

package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.ItemNecessario;
import com.projetodsc.edoe.models.Usuario;
import lombok.Data;

@Data
public class ItemNecessarioDTOResponse {
	
	private String nome;
	private String motivacao;
	private int quantidadeNecessaria;
	private ReceptorDTOResponse receptor;
	
	public ItemNecessarioDTOResponse(ItemNecessario item, Usuario usuario) {
		this.nome = item.getNome();
		this.motivacao = item.getMotivacao();
		this.quantidadeNecessaria = item.getQuantidadeNecessaria();
		this.receptor = new ReceptorDTOResponse(usuario);
	}

}

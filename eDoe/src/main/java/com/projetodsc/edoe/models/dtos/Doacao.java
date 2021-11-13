package com.projetodsc.edoe.models.dtos;

import lombok.Data;

@Data
public class Doacao {
	
	private long idItemDoacao;
	private long idItemNecessarios;
	private int quantidadeDoacao;
	
	public Doacao(long idItemDoacao, long idItemNecessario, int quantidade) {
		this.idItemDoacao = idItemDoacao;
		this.idItemNecessarios = idItemNecessario;
		this.quantidadeDoacao = quantidade;
	}

}

package com.projetodsc.edoe.models.dtos;

import lombok.Data;
import java.util.Date;

@Data
public class DoacaoDTO {
	
	private long idItemDoacao;
	private long idItemNecessario;
	private int quantidadeDoacao;
	private Date data;
	
	public DoacaoDTO(long idItemDoacao, long idItemNecessario, int quantidadeDoacao) {
		this.idItemDoacao = idItemDoacao;
		this.idItemNecessario = idItemNecessario;
		this.quantidadeDoacao = quantidadeDoacao;
		this.data = new Date();
	}
	
	public Doacao getDoacao(Doacao dadosDoacao) {
		return new Doacao(dadosDoacao.getIdItemDoacao(), dadosDoacao.getIdItemNecessarios(), dadosDoacao.getQuantidadeDoacao());
	}

}

package com.apiedoe.models.dtos;

import lombok.Data;
import com.apiedoe.models.Doacao;

@Data
public class DoacaoDTO {

	private long idItemDoacao;
	private long idItemNecessario;
	private int quantidadeDoacao;
	
	public DoacaoDTO() {}

	public DoacaoDTO(long idItemDoacao, long idItemNecessario, int quantidadeDoacao) {
		this.idItemDoacao = idItemDoacao;
		this.idItemNecessario = idItemNecessario;
		this.quantidadeDoacao = quantidadeDoacao;
	}

	public Doacao getDoacao(Doacao dadosDoacao) {
		return new Doacao(dadosDoacao.getIdItemDoacao(), dadosDoacao.getIdItemNecessario(),
				dadosDoacao.getQuantidadeDoacao());
	}

	public DoacaoDTO(Doacao dadosDoacao) {
		this.idItemDoacao = dadosDoacao.getIdItemDoacao();
		this.idItemNecessario = dadosDoacao.getIdItemNecessario();
		this.quantidadeDoacao = dadosDoacao.getQuantidadeDoacao();
	}

}

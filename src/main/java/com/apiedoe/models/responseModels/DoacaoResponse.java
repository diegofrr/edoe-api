package com.apiedoe.models.responseModels;

import java.text.SimpleDateFormat;
import com.apiedoe.models.Doacao;
import lombok.Data;

@Data
public class DoacaoResponse {
	private long idItemDoacao;
	private long idItemNecessario;
	private int quantidadeDoacao;
	private String data;

	public DoacaoResponse() {
	}

	public DoacaoResponse(Doacao dadosDoacao) {
		this.idItemDoacao = dadosDoacao.getIdItemDoacao();
		this.idItemNecessario = dadosDoacao.getIdItemNecessario();
		this.quantidadeDoacao = dadosDoacao.getQuantidadeDoacao();
		this.data = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy hh:mm:ss a").format(dadosDoacao.getData());
		
	}

}
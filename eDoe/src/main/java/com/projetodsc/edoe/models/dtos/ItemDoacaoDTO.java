package com.projetodsc.edoe.models.dtos;

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.ItemNecessario;
import com.projetodsc.edoe.models.Usuario;

import lombok.Data;

@Data
public class ItemDoacaoDTO {

	private String nome;
	private String descricaoDetalhada;
	private Descritor descritor;
	private int quantidadeDoacao;
	private DoadorDTOResponse doador;

	public ItemDoacaoDTO() {
	}
	
	public ItemDoacaoDTO(ItemDoacao item) {
		this.nome = item.getNome();
		this.descricaoDetalhada = item.getDescricaoDetalhada();
		this.quantidadeDoacao = item.getQuantidadeDoacao();
		this.descritor = item.getDescritor();
		this.doador = new DoadorDTOResponse(item.getDoador());
	}
	
	public ItemDoacaoDTO(String nome, String descricaoDetalhada, Descritor descritor, int quantidadeDoacao) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.descritor = descritor;
		this.quantidadeDoacao = quantidadeDoacao;
	}
	
	public ItemDoacaoDTO(String nome, String descricaoDetalhada) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public ItemDoacao getItem() {
		return new ItemDoacao(this.nome, this.descricaoDetalhada, this.descritor, this.quantidadeDoacao);
	}

}

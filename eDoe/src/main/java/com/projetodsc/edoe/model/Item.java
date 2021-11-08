package com.projetodsc.edoe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String descricaoDetalhada;

	@Column(nullable = false)
	private int quantidadeDoacao;

	@ManyToOne
	@JoinColumn
	private Descritor descritor;
	
	@ManyToOne
	@JoinColumn
	private Usuario doador;

	public Item() {
	}

	public Item(String nome, String descricaoDetalhada, Descritor descritor, int quantidadeDoacao) {
		this.nome = nome;
		this.descricaoDetalhada = descricaoDetalhada;
		this.descritor = descritor;
		this.quantidadeDoacao = quantidadeDoacao;

	}

}

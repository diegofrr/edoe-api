package com.projetodsc.edoe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ItemNecessario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	private String motivacao;
	
	@Column(nullable = false)
	private int quantidadeNecessaria;
	
	@ManyToOne
	@JoinColumn
	private Descritor descritor;
	
	@ManyToOne
	@JoinColumn
	private Usuario receptor;
	
	public ItemNecessario() {}
	
	public ItemNecessario(String nome, String motivacao, int quantidadeNecessaria, Descritor descritor){
		this.nome = nome;
		this.motivacao = motivacao;
		this.quantidadeNecessaria = quantidadeNecessaria;
		this.descritor = descritor;
	}
	
}

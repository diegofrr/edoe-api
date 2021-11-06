package com.projetodsc.edoe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class Item implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private int quantidade;
	
	@ManyToOne
	private DescricaoItem descricao;
	
	private boolean disponivel;
	
	public Item() {}
	
	public Item(String nome, int idDescricao, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	
	public boolean getDisponibilidade() {
		if (quantidade == 0)
			return false;
		return true;
	}

}

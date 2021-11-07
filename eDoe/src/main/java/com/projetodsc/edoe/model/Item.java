package com.projetodsc.edoe.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	private int quantidade;

	@ManyToMany
	@JoinTable(name = "item_keyword", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "keyword_id")) 
	private List<Descritor> descritores;

	private boolean disponivel = false;

	public Item() {
	}

	public Item(String nome, List<Descritor> descritores, int quantidade, boolean disponivel) {
		this.nome = nome;
		this.descritores = descritores;
		this.quantidade = quantidade;
		if (quantidade > 0)
			this.disponivel = true;
	}

}

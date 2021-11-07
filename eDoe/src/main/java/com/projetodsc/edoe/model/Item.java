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
	private int quantidade;

	@ManyToOne
	@JoinColumn
	private Descritor descritor;

	public Item() {
	}

	public Item(String nome, Descritor descritor, int quantidade) {
		this.nome = nome;
		this.descritor = descritor;
		this.quantidade = quantidade;

	}

}

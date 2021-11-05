package com.projetodsc.edoe.model;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data

public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	
	private String nome;
	private DescritorItem descricao;
	private double preco;
	
	public Item() {}
	
	public Item(long id, String nome, DescritorItem descricao, double preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Item(String nome, DescritorItem descricao, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id+ ", nome=" + nome + ", descricao=" + descricao + ",preco=" + preco + "]";
	}

}

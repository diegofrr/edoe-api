package com.projetodsc.edoe.models.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Doacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private long idItemDoacao;
	
	@Column
	private long idItemNecessarios;
	
	@Column
	private int quantidadeDoacao;
	
	@Column
	private Date data;
	
	public Doacao(long idItemDoacao, long idItemNecessario, int quantidade) {
		this.idItemDoacao = idItemDoacao;
		this.idItemNecessarios = idItemNecessario;
		this.quantidadeDoacao = quantidade;
		this.data = new Date();
	}

}

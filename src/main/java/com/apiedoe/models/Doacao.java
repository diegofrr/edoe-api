package com.apiedoe.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Doacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private long idItemDoacao;

	@Column
	private long idItemNecessario;

	@Column
	private int quantidadeDoacao;

	@Column
	private Date data;

	public Doacao() {
	}

	public Doacao(long idItemDoacao, long idItemNecessario, int quantidade) {
		this.idItemDoacao = idItemDoacao;
		this.idItemNecessario = idItemNecessario;
		this.quantidadeDoacao = quantidade;
		this.data = Calendar.getInstance(TimeZone.getTimeZone("America/Brasilia")).getTime();
	}

}
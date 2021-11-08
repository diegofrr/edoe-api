package com.projetodsc.edoe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Descritor {

	@Id
	@Column(unique = true, nullable = false)
	private String descricao;

	public Descritor() {
	}

	public Descritor(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}

}

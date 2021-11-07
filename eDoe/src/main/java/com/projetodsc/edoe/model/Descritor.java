package com.projetodsc.edoe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Descritor {

	@Id
	public String descricao;

	public Descritor() {
	}

	public Descritor(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}

}

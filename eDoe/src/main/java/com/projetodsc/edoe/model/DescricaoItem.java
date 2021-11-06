package com.projetodsc.edoe.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class DescricaoItem {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique = true, nullable = false)
	public String descricao;
	
	public DescricaoItem() {}

	public DescricaoItem(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return descricao;
	}

}

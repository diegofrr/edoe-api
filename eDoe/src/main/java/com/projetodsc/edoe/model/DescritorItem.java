package com.projetodsc.edoe.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public class DescritorItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private String descricao;
	
	public DescritorItem() {}
	
	public DescritorItem(String descricao) {
		this.descricao = descricao;
	}

	
	

}

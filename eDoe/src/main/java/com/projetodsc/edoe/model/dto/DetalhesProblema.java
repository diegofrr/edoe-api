package com.projetodsc.edoe.model.dto;

import lombok.Data;

@Data
public class DetalhesProblema {
	
	private int status;
	private String type;
	private String title;
	private String detail;

	public DetalhesProblema() {}
	
	public DetalhesProblema(int status, String type, String title, String detail) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
	}
	
}

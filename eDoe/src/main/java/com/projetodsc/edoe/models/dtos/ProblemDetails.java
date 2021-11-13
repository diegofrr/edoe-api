package com.projetodsc.edoe.models.dtos;

import lombok.Data;

@Data
public class ProblemDetails {

	private int status;
	private String type;
	private String title;
	private String detail;

	public ProblemDetails() {
	}

	public ProblemDetails(int status, String type, String title, String detail) {
		this.status = status;
		this.type = type;
		this.title = title;
		this.detail = detail;
	}

}

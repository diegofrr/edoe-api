package com.projetodsc.edoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projetodsc.edoe.model.dto.DetalhesProblema;

@RestControllerAdvice
public class ExceptionsHandler {
	
	private static String ADICIONA_USUARIO_URI = "https://servidor:8080/v1/api/produtos";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DetalhesProblema> usuarioInvalido(UsuarioInvalidoException e){
		DetalhesProblema problema = new DetalhesProblema();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ADICIONA_USUARIO_URI);
		problema.setDetail(e.getDetalhe());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
}

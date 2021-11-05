package com.projetodsc.edoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.projetodsc.edoe.model.dto.ProblemDetails;

@RestControllerAdvice
public class ExceptionsHandler {
	
	private static String ADICIONA_USUARIO_URI = "https://servidor:8080/api/usuarios";
	private static String FALHA_DE_LOGIN_URI = "https://servidor:8080/auth/login";
	
	
	@ExceptionHandler(UsuarioInvalidoException.class)
	public ResponseEntity<ProblemDetails> usuarioInvalido(UsuarioInvalidoException e){
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ADICIONA_USUARIO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	
	@ExceptionHandler(UsuarioNaoExisteException.class)
	public ResponseEntity<ProblemDetails> usuarioInvalido(UsuarioNaoExisteException e){
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ADICIONA_USUARIO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	
	@ExceptionHandler(NaoAutenticadoException.class)
	public ResponseEntity<ProblemDetails> usuarioNaoAutenticado(NaoAutenticadoException e){
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(FALHA_DE_LOGIN_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	
}

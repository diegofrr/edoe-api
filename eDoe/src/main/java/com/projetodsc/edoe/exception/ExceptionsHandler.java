package com.projetodsc.edoe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.projetodsc.edoe.model.dto.ProblemDetails;

@RestControllerAdvice
public class ExceptionsHandler {

	private static String ADICIONA_USUARIO_URI = "https://localhost:8080/api/usuarios";
	private static String FALHA_DE_LOGIN_URI = "https://localhost:8080/api/auth/login";
	private static String DESCRITOR_NAO_EXISTE_URI = "https://localhost:8080/api/descritores/cadastro";
	private static String DESCRITOR_JA_EXISTE_URI = "https://localhost:8080/api/descritores";
	private static String DESCRITOR_INVALIDO_URI = "https://localhost:8080/api/descritores/cadastro";
	private static String NAO_AUTORIZADO_URI = "https://localhost:8080/api/auth/logi";
	
	@ExceptionHandler(UsuarioInvalidoException.class)
	public ResponseEntity<ProblemDetails> usuarioInvalido(UsuarioInvalidoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ADICIONA_USUARIO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(UsuarioNaoExisteException.class)
	public ResponseEntity<ProblemDetails> usuarioInvalido(UsuarioNaoExisteException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ADICIONA_USUARIO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(NaoAutenticadoException.class)
	public ResponseEntity<ProblemDetails> usuarioNaoAutenticado(NaoAutenticadoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.UNAUTHORIZED.value());
		problema.setTitle(e.getTitulo());
		problema.setType(FALHA_DE_LOGIN_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problema);
	}

	@ExceptionHandler(NaoAutorizadoException.class)
	public ResponseEntity<ProblemDetails> usuarioNaoAutorizado(NaoAutorizadoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(NAO_AUTORIZADO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(DescritorJaExisteException.class)
	public ResponseEntity<ProblemDetails> descritorJaExiste(DescritorJaExisteException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(DESCRITOR_JA_EXISTE_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(DescritorNaoExisteException.class)
	public ResponseEntity<ProblemDetails> descritorNaoExiste(DescritorNaoExisteException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(DESCRITOR_NAO_EXISTE_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}

	@ExceptionHandler(DescritorInvalidoException.class)
	public ResponseEntity<ProblemDetails> descritorInvalido(DescritorInvalidoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(DESCRITOR_INVALIDO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
}

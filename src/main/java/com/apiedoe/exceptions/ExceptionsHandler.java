package com.apiedoe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.apiedoe.models.dtos.ProblemDetails;

@RestControllerAdvice
public class ExceptionsHandler {

	private static String ADICIONA_USUARIO_URI = "https://edoe-api.herokuapp.com/api/usuarios";
	private static String FALHA_DE_LOGIN_URI = "https://edoe-api.herokuapp.com/api/auth/login";
	private static String DESCRITOR_NAO_EXISTE_URI = "https://edoe-api.herokuapp.com/api/descritores/cadastro";
	private static String DESCRITOR_JA_EXISTE_URI = "https://edoe-api.herokuapp.com/api/descritores";
	private static String DESCRITOR_INVALIDO_URI = "https://edoe-api.herokuapp.com/api/descritores/cadastro";
	private static String NAO_AUTORIZADO_URI = "https://edoe-api.herokuapp.com/api/auth/login";
	private static String ITEM_NAO_ENCONTRADO_URI = "https://edoe-api.herokuapp.com/api/itens/";
	private static String DOACAO_INVALIDA_URI = "https://edoe-api.herokuapp.com/api/itens/doacoes/realizar-doacao";
	
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
	
	@ExceptionHandler(ItemNaoEncontradoException.class)
	public ResponseEntity<ProblemDetails> descritorInvalido(ItemNaoEncontradoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.NOT_FOUND.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ITEM_NAO_ENCONTRADO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
	
	@ExceptionHandler(ItemInvalidoException.class)
	public ResponseEntity<ProblemDetails> itemInvalido(ItemInvalidoException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.NOT_FOUND.value());
		problema.setTitle(e.getTitulo());
		problema.setType(DOACAO_INVALIDA_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
	
	@ExceptionHandler(DoacaoInvalidaException.class)
	public ResponseEntity<ProblemDetails> doacaoInvalida(DoacaoInvalidaException e) {
		ProblemDetails problema = new ProblemDetails();
		problema.setStatus(HttpStatus.BAD_REQUEST.value());
		problema.setTitle(e.getTitulo());
		problema.setType(ITEM_NAO_ENCONTRADO_URI);
		problema.setDetail(e.getDetalhes());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
}

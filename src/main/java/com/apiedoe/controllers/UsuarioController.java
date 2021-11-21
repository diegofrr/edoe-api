package com.apiedoe.controllers;

import java.util.List;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apiedoe.models.requestModels.UsuarioAlteradoRequest;
import com.apiedoe.models.requestModels.UsuarioCadastro;
import com.apiedoe.models.responseModels.UsuarioResponse;
import com.apiedoe.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public String whitelabel(){
		return "Hello! Leia a documentação Swagger para navegar pelas rotas: https://edoe-api.herokuapp.com/swagger-ui.html#/";
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
		return new ResponseEntity<List<UsuarioResponse>>(usuarioService.listarUsuarios(), HttpStatus.OK);
	}

	@PostMapping("/usuarios/cadastro")
	public ResponseEntity<UsuarioResponse> adicionaUsuario(@RequestBody UsuarioCadastro usuario) throws ServletException {
		return new ResponseEntity<UsuarioResponse>(usuarioService.adicionaUsuario(usuario), HttpStatus.CREATED);

	}	

	@PostMapping("/usuarios/tipos")
	public ResponseEntity<UsuarioResponse> alteraTipoUsuario(@RequestBody UsuarioAlteradoRequest dados,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<UsuarioResponse>(usuarioService.alteraTipo(dados, header), HttpStatus.OK);
	}

}

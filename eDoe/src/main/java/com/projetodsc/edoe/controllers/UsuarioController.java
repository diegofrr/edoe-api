package com.projetodsc.edoe.controllers;

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
import com.projetodsc.edoe.models.dtos.UsuarioDTO;
import com.projetodsc.edoe.models.dtos.UsuarioResponse;
import com.projetodsc.edoe.services.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioResponse>> getUsuarios(){
		return new ResponseEntity<List<UsuarioResponse>>(usuarioService.listarUsuarios(), HttpStatus.OK);
	}
	
	@PostMapping("/usuarios/cadastro")
	public ResponseEntity<UsuarioResponse> adicionaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<UsuarioResponse>(usuarioService.adicionaUsuario(usuarioDTO), HttpStatus.CREATED);
	
	}
	
	@PostMapping("/usuarios/tipos")
	public ResponseEntity<UsuarioResponse> alteraTipoUsuario(@RequestBody UsuarioDTO dados, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<UsuarioResponse>(usuarioService.alteraTipo(dados, header), HttpStatus.OK);
	}
	
}

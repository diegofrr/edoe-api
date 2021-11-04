package com.projetodsc.edoe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.UsuarioDTO;
import com.projetodsc.edoe.services.UsuarioService;


@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
//	@PostMapping("/usuarios/login")
//	public ResponseEntity<Usuario> logar(@RequestBody UsuarioDTO usuarioDTO) {
//		return new ResponseEntity<Usuario>(usuarioService.logar(usuarioDTO), HttpStatus.OK);
//	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getUsuarios(){
		return new ResponseEntity<List<Usuario>>(usuarioService.listaUsuarios(), HttpStatus.OK);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Usuario> adicionaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<Usuario>(usuarioService.adicionaUsuario(usuarioDTO), HttpStatus.OK);
	
	}
	

}

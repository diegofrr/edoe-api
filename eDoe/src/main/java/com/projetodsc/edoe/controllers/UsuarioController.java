package com.projetodsc.edoe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.UsuarioDTO;
import com.projetodsc.edoe.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> consultar(@PathVariable("id") Long id) {
		return null;
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getUsuarios(){
		return new ResponseEntity<List<Usuario>>(usuarioService.listaUsuarios(), HttpStatus.OK);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Optional<Usuario>> adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		try{
			return new ResponseEntity<Optional<Usuario>>(Optional.ofNullable(usuarioService.adicionarUsuario(usuarioDTO)), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	
	}
	

}

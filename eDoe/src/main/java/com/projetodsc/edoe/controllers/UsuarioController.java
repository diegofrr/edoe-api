package com.projetodsc.edoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.UsuarioDTO;
import com.projetodsc.edoe.repository.UsuarioRepository;
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
	public ResponseEntity<Usuario> adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<Usuario>(usuarioService.adicionarUsuario(usuarioDTO), HttpStatus.CREATED);
	}
	

}

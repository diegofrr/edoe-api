package com.apiedoe.controllers;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apiedoe.models.dtos.LoginResponse;
import com.apiedoe.models.dtos.UsuarioDTO;
import com.apiedoe.services.JWTService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private JWTService jwtService;

	@PostMapping("/auth/login")
	public ResponseEntity<LoginResponse> autentica(@RequestBody UsuarioDTO login) throws ServletException {
		return new ResponseEntity<LoginResponse>(jwtService.autentica(login), HttpStatus.OK);
	}

}
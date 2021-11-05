package com.projetodsc.edoe.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetodsc.edoe.model.dto.LoginDTO;
import com.projetodsc.edoe.services.JWTService;
import com.projetodsc.edoe.model.dto.LoginResponse;

@RestController
public class LoginController {
	
	@Autowired
	private JWTService jwtService;
	
	@PostMapping("auth/login")
	public ResponseEntity<LoginResponse> autentica(@RequestBody LoginDTO loginDTO) throws ServletException {
		return new ResponseEntity<LoginResponse>(jwtService.autentica(loginDTO), HttpStatus.OK);
	}
	

}

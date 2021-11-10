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

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.services.DescritorService;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api")
public class DescritorController {
	
	@Autowired
	DescritorService descritorService;
	
	@Autowired
	ItemService itemService;

	@GetMapping("/descritores")
	public ResponseEntity<List<Descritor>> getDescritores(){
		return new ResponseEntity<List<Descritor>>(descritorService.getDescritores(), HttpStatus.OK);
	}
	
	
	@PostMapping("/descritores/cadastro")
	public ResponseEntity<Descritor> addDescritor(@RequestBody Descritor descritor){
		return new ResponseEntity<Descritor>(descritorService.addDescritor(descritor), HttpStatus.OK);
	}
}

package com.projetodsc.edoe.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.dto.ItemDTO;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/itens")
	public ResponseEntity<List<Item>> getItens(){
		return new ResponseEntity<List<Item>>(itemService.getItens(), HttpStatus.OK);
	}
	
	@PostMapping("/itens")
	public ResponseEntity<Item> addItem(@RequestBody ItemDTO itemDTO){
		return new ResponseEntity<Item>(itemService.addItem(itemDTO), HttpStatus.OK);
	}
	
	@PostMapping("/itens/add")
	public ResponseEntity<Item> adicionaItem(@RequestBody ItemDTO itemDTO, @RequestHeader("Authorization") String header){
		return new ResponseEntity<Item>(itemService.adicionaItem(itemDTO, header), HttpStatus.OK);
	}

}

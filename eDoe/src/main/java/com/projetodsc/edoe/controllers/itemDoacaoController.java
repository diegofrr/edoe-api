package com.projetodsc.edoe.controllers;

import java.util.List;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projetodsc.edoe.model.Descritor;
import com.projetodsc.edoe.model.ItemDoacao;
import com.projetodsc.edoe.model.dto.ItemDoacaoDTO;
import com.projetodsc.edoe.model.dto.ItemDTODeleted;
import com.projetodsc.edoe.model.dto.ResponseItemDTO;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class itemDoacaoController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/doacao")
	public ResponseEntity<List<ItemDoacao>> getItens(){
		return new ResponseEntity<List<ItemDoacao>>(itemService.getItens(), HttpStatus.OK);
	}

	
	@PostMapping("/doacao/cadastrar")
	public ResponseEntity<ResponseItemDTO> adicionaItem(@RequestBody ItemDoacaoDTO itemDTO, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ResponseItemDTO>(itemService.addItem(itemDTO, header), HttpStatus.OK);
	}
	
	@DeleteMapping("/doacao/{id}")
	public ResponseEntity<ItemDTODeleted> removeItem(@PathVariable("id") long id_item, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemDTODeleted>(itemService.removeItem(id_item, header), HttpStatus.OK);
	}
	
	@GetMapping("/doacao/buscar={descricao}")
	public ResponseEntity<List<ResponseItemDTO>> getByDescritor(@PathVariable String descricao) throws ServletException {
		return new ResponseEntity<List<ResponseItemDTO>>(itemService.getItensByDescritor(new Descritor(descricao)), HttpStatus.OK);
	}
	
	@PutMapping("/doacao/{id}")
	public ResponseEntity<ItemDoacao> atualizaItem(@PathVariable long id, @RequestBody ItemDoacaoDTO itemAtualizado, @RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemDoacao>(itemService.atualizaItem(id, itemAtualizado, authHeader), HttpStatus.OK);
		
	}
	
	@GetMapping("/doacao/ranking_quantity")
	public ResponseEntity<List<ResponseItemDTO>> getItensOrderByQuantidadeDesc() throws ServletException{
		return new ResponseEntity<List<ResponseItemDTO>>(itemService.getItensOrderByQuantidadeDesc(), HttpStatus.OK);
	}
	

}

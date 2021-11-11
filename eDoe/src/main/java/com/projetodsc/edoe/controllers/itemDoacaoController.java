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

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.dtos.ItemDTODeleted;
import com.projetodsc.edoe.models.dtos.ItemDoacaoDTO;
import com.projetodsc.edoe.models.dtos.ItemDoacaoDTOResponse;
import com.projetodsc.edoe.models.dtos.ItemNecessarioDTOResponse;
import com.projetodsc.edoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class itemDoacaoController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping("/doacoes")
	public ResponseEntity<List<ItemDoacao>> getItens(){
		return new ResponseEntity<List<ItemDoacao>>(itemService.getItens(), HttpStatus.OK);
	}

	
	@PostMapping("/doacoes/cadastro")
	public ResponseEntity<ItemDoacaoDTOResponse> adicionaItem(@RequestBody ItemDoacaoDTO itemDTO, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemDoacaoDTOResponse>(itemService.addItemDoacao(itemDTO, header), HttpStatus.OK);
	}
	
	@DeleteMapping("/doacoes/{id}")
	public ResponseEntity<ItemDTODeleted> removeItem(@PathVariable("id") long id_item, @RequestHeader("Authorization") String header) throws ServletException{
		return new ResponseEntity<ItemDTODeleted>(itemService.removeItemDoacao(id_item, header), HttpStatus.OK);
	}
	
	@GetMapping("/doacoes/{descritor}")
	public ResponseEntity<List<ItemDoacaoDTOResponse>> getByDescritor(@PathVariable String descritor) throws ServletException {
		return new ResponseEntity<List<ItemDoacaoDTOResponse>>(itemService.getItensDoacaoByDescritor(new Descritor(descritor)), HttpStatus.OK);
	}
	
	@PutMapping("/doacoes/{id}")
	public ResponseEntity<ItemDoacaoDTOResponse> atualizaItem(@PathVariable long id, @RequestBody ItemDoacaoDTO itemAtualizado, @RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemDoacaoDTOResponse>(itemService.atualizaItemDoacao(id, itemAtualizado, authHeader), HttpStatus.OK);
	}
	
	@GetMapping("/doacoes/ranking-quantidade")
	public ResponseEntity<List<ItemDoacaoDTOResponse>> getItensOrderByQuantidadeDesc() throws ServletException{
		return new ResponseEntity<List<ItemDoacaoDTOResponse>>(itemService.getItensDoacaoOrderByQuantidadeDesc(), HttpStatus.OK);
	}
	
	@GetMapping("/doacoes/buscar={string_busca}")
	public ResponseEntity<List<ItemDoacaoDTOResponse>> getByParametroBusca(@PathVariable String string_busca) throws ServletException {
		return new ResponseEntity<List<ItemDoacaoDTOResponse>>(itemService.getItensDoacaoByString_busca(string_busca), HttpStatus.OK);
	}
	

}

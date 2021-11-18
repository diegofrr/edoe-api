package com.apiedoe.controllers;

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
import com.apiedoe.models.Descritor;
import com.apiedoe.models.TipoItem;
import com.apiedoe.models.dtos.Doacao;
import com.apiedoe.models.dtos.DoacaoResponse;
import com.apiedoe.models.dtos.ItemDTO;
import com.apiedoe.models.dtos.ItemResponse;
import com.apiedoe.services.ItemService;

@RestController
@RequestMapping("/api/itens")
public class ItemDoacaoController {

	@Autowired
	ItemService itemService;

	@GetMapping("/doacoes")
	public ResponseEntity<List<ItemResponse>> getItens() {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItens(TipoItem.DOACAO), HttpStatus.OK);
	}

	@PostMapping("/doacoes/cadastro")
	public ResponseEntity<ItemResponse> adicionaItem(@RequestBody ItemDTO item,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.adicionaItem(item, TipoItem.DOACAO, header), HttpStatus.CREATED);
	}

	@DeleteMapping("/doacoes/remover/{id}")
	public ResponseEntity<ItemResponse> removeItem(@PathVariable("id") long idItem,
			@RequestHeader("Authorization") String header) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.removeItem(idItem, header), HttpStatus.OK);
	}

	@GetMapping("/doacoes/descritor={descritor}")
	public ResponseEntity<List<ItemResponse>> getByDescritor(@PathVariable String descritor) throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(
				itemService.getItensByDescritor(new Descritor(descritor), TipoItem.DOACAO), HttpStatus.OK);
	}

	@PutMapping("/doacoes/atualizar/{id}")
	public ResponseEntity<ItemResponse> atualizaItem(@PathVariable long id, @RequestBody ItemDTO dadosAtualizados,
			@RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemResponse>(
				itemService.atualizaItem(id, dadosAtualizados, TipoItem.DOACAO, authHeader), HttpStatus.OK);
	}

	@GetMapping("/doacoes/ranking-quantidade")
	public ResponseEntity<List<ItemResponse>> getItensOrderByQuantidadeDesc() throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensOrderByQuantidadeDesc(TipoItem.DOACAO),
				HttpStatus.OK);
	}

	@GetMapping("/doacoes/buscar={stringBusca}")
	public ResponseEntity<List<ItemResponse>> getByParametroBusca(@PathVariable String stringBusca)
			throws ServletException {
		return new ResponseEntity<List<ItemResponse>>(itemService.getItensByStringBusca(stringBusca, TipoItem.DOACAO),
				HttpStatus.OK);
	}

	@GetMapping("/doacoes/realizar-doacao")
	public ResponseEntity<ItemResponse> realizarDoacao(@RequestBody Doacao dadosDoacao,
			@RequestHeader("Authorization") String authHeader) throws ServletException {
		return new ResponseEntity<ItemResponse>(itemService.realizarDoacao(dadosDoacao, authHeader), HttpStatus.OK);

	}

	@GetMapping("/doacoes/historico")
	public ResponseEntity<List<DoacaoResponse>> historioDeDoacoes() throws ServletException {
		return new ResponseEntity<List<DoacaoResponse>>(itemService.historicoDeDoacoes(), HttpStatus.OK);
	}
}
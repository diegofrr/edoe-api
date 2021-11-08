package com.projetodsc.edoe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exception.DescritorNaoExisteException;
import com.projetodsc.edoe.exception.UsuarioInvalidoException;
import com.projetodsc.edoe.model.Item;
import com.projetodsc.edoe.model.Usuario;
import com.projetodsc.edoe.model.dto.ItemDTO;
import com.projetodsc.edoe.repository.DescritoresRepository;
import com.projetodsc.edoe.repository.ItensRepository;
import com.projetodsc.edoe.repository.UsuariosRepository;

@Service
public class ItemService {

	@Autowired
	private ItensRepository itensRepositorio;
	
	@Autowired
	private DescritoresRepository descritoresRepositorio;
	
	@Autowired
	private UsuariosRepository usuariosRepositorio;
	
	@Autowired
	private JWTService jwtService;
		
	public Item adicionaItem(ItemDTO itemDTO, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		
		if (!descritoresRepositorio.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe", "Este descritor não está cadastrado no sistema.");
		
		itemDTO.setDescritor(descritoresRepositorio.findByDescricao(itemDTO.getDescritor().getDescricao()).get());
		Item item = itemDTO.getItem();
		item.setDoador(usuarioDoToken.get());
		return itensRepositorio.save(item);
	}

	public Item addItem(ItemDTO item) {
		if (!descritoresRepositorio.existsByDescricao(item.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe", "Este descritor não está cadastrado no sistema.");
		item.setDescritor(descritoresRepositorio.findByDescricao(item.getDescritor().getDescricao()).get());
		return itensRepositorio.save(item.getItem());
	}

	public List<Item> getItens() {
		return itensRepositorio.findAll();
	}
	
}

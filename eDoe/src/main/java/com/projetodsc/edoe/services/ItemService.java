package com.projetodsc.edoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exceptions.DescritorInvalidoException;
import com.projetodsc.edoe.exceptions.DescritorNaoExisteException;
import com.projetodsc.edoe.exceptions.ItemNaoEncontradoException;
import com.projetodsc.edoe.exceptions.NaoAutorizadoException;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;
import com.projetodsc.edoe.models.ItemNecessario;
import com.projetodsc.edoe.models.TipoUsuario;
import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.ItemDTODeleted;
import com.projetodsc.edoe.models.dtos.ItemDoacaoDTO;
import com.projetodsc.edoe.models.dtos.ItemNecessarioDTO;
import com.projetodsc.edoe.models.dtos.ItemNecessarioDTOResponse;
import com.projetodsc.edoe.models.dtos.ItemDoacaoDTOResponse;
import com.projetodsc.edoe.repositories.DescritoresRepository;
import com.projetodsc.edoe.repositories.ItensDoacaoRepository;
import com.projetodsc.edoe.repositories.ItensNecessarioRepository;
import com.projetodsc.edoe.repositories.UsuariosRepository;

@Service
public class ItemService {

	@Autowired
	private ItensDoacaoRepository itensDoacaoRepositorio;

	@Autowired
	private ItensNecessarioRepository itensNecessarioRepositorio;

	@Autowired
	private UsuariosRepository usuariosRepositorio;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private DescritorService descritorService;
	
	public List<ItemDoacaoDTOResponse> getItensDoacaoByString_busca(String string_busca) {
		List<ItemDoacaoDTOResponse> listResponse = new ArrayList<>();
		for (Descritor descritor : descritorService.getDescritoresByDescricaoContaining(string_busca)) {
			List<ItemDoacao> itensDoac = itensDoacaoRepositorio.findByDescritor(descritor).get();
			for (ItemDoacao it : itensDoac) {
				listResponse.add(new ItemDoacaoDTOResponse(it, it.getDoador()));
			}
		}
		return listResponse;
	}

	public List<ItemNecessarioDTOResponse> getItensNecessarioByString_busca(String string_busca) {
		List<ItemNecessarioDTOResponse> listResponse = new ArrayList<>();
		for (Descritor descritor : descritorService.getDescritoresByDescricaoContaining(string_busca)) {
			List<ItemNecessario> itensNec = itensNecessarioRepositorio.findByDescritor(descritor).get();
			for (ItemNecessario it : itensNec) {
				listResponse.add(new ItemNecessarioDTOResponse(it, it.getReceptor()));
			}
		}
		return listResponse;
	}

	public List<ItemNecessarioDTOResponse> getItensNecessarioOrderByQuantidadeDesc() {
		List<ItemNecessario> itens = itensNecessarioRepositorio.findTop10ByOrderByQuantidadeNecessariaDesc().get();
		List<ItemNecessarioDTOResponse> listResponse = new ArrayList<>();
		for (ItemNecessario i : itens) {
			ItemNecessarioDTOResponse newItem = new ItemNecessarioDTOResponse(i, i.getReceptor());
			listResponse.add(newItem);
		}
		return listResponse;
	}

	public List<ItemDoacaoDTOResponse> getItensDoacaoOrderByQuantidadeDesc() {
		List<ItemDoacao> itens = itensDoacaoRepositorio.findTop10ByOrderByQuantidadeDoacaoDesc().get();
		List<ItemDoacaoDTOResponse> listResponse = new ArrayList<>();
		for (ItemDoacao i : itens) {
			ItemDoacaoDTOResponse newItem = new ItemDoacaoDTOResponse(i, i.getDoador());
			listResponse.add(newItem);
		}
		return listResponse;
	}

	public ItemDoacao alteraDadosItemDoacao(ItemDoacao item, ItemDoacao itemAtt) {
		item.setNome(itemAtt.getNome());
		item.setDescricaoDetalhada(itemAtt.getDescricaoDetalhada());
		item.setQuantidadeDoacao(itemAtt.getQuantidadeDoacao());
		item.setDescritor(itemAtt.getDescritor());
		return item;
	}

	public ItemNecessario alteraDadosItemNecessario(ItemNecessario item, ItemNecessario itemAtt) {
		item.setNome(itemAtt.getNome());
		item.setMotivacao(itemAtt.getMotivacao());
		item.setQuantidadeNecessaria(itemAtt.getQuantidadeNecessaria());
		item.setDescritor(itemAtt.getDescritor());
		return item;
	}

	public ItemDoacaoDTOResponse atualizaItemDoacao(long id, ItemDoacaoDTO itemAtualizado, String authHeader) {
		itemAtualizado.setNome(itemAtualizado.getNome().toUpperCase());
		itemAtualizado.setDescricaoDetalhada(itemAtualizado.getDescricaoDetalhada().toUpperCase());

		if (!itensDoacaoRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");
		ItemDoacao item = itensDoacaoRepositorio.findById(id).get();
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		if (usuarioDoToken.get() != item.getDoador())
			throw new NaoAutorizadoException("Não autorizado", "Este item pertence a outro doador.");
		ItemDoacao itemDoacao = itensDoacaoRepositorio.save(alteraDadosItemDoacao(item, itemAtualizado.getItem()));
		return new ItemDoacaoDTOResponse(itemDoacao, usuarioDoToken.get());
	}

	public ItemNecessarioDTOResponse atualizaItemNecessario(long id, ItemNecessarioDTO itemAtualizado,
			String authHeader) {
		itemAtualizado.setNome(itemAtualizado.getNome().toUpperCase());
		itemAtualizado.setMotivacao(itemAtualizado.getMotivacao().toUpperCase());

		if (!itensNecessarioRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");

		if (!descritorService.existsByDescricao(itemAtualizado.getDescritor().getDescricao()))
			descritorService.save(itemAtualizado.getDescritor());

		ItemNecessario item = itensNecessarioRepositorio.findById(id).get();
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);
		if (usuarioDoToken.get() != item.getReceptor())
			throw new NaoAutorizadoException("Não autorizado", "Este item pertence a outro doador.");
		ItemNecessario itemNecessario = itensNecessarioRepositorio
				.save(alteraDadosItemNecessario(item, itemAtualizado.getItem()));
		return new ItemNecessarioDTOResponse(itemNecessario, usuarioDoToken.get());

	}

	public List<ItemNecessarioDTOResponse> getItensNecessarioByDescritor(Descritor descritor) {
		descritor.setDescricao(descritor.getDescricao().toUpperCase());

		if (!descritorService.existsByDescricao(descritor.getDescricao().toUpperCase()))
			throw new DescritorInvalidoException("Descritor inválido", "Este descritor não existe no sistema.");

		List<ItemNecessarioDTOResponse> response = new ArrayList<>();
		for (ItemNecessario i : itensNecessarioRepositorio.findByDescritor(descritor).get()) {
			response.add(new ItemNecessarioDTOResponse(i, i.getReceptor()));
		}
		return response;
	}

	public List<ItemDoacaoDTOResponse> getItensDoacaoByDescritor(Descritor descritor) {
		descritor.setDescricao(descritor.getDescricao().toUpperCase());

		if (!descritorService.existsByDescricao(descritor.getDescricao().toUpperCase()))
			throw new DescritorInvalidoException("Descritor inválido", "Este descritor não existe no sistema.");

		List<ItemDoacaoDTOResponse> response = new ArrayList<>();
		for (ItemDoacao i : itensDoacaoRepositorio.findByDescritor(descritor).get()) {
			response.add(new ItemDoacaoDTOResponse(i, i.getDoador()));
		}
		return response;
	}

	public ItemNecessarioDTOResponse addItemNecessario(ItemNecessarioDTO itemDTO, String authHeader) {
		itemDTO.setNome(itemDTO.getNome().toUpperCase());
		itemDTO.setMotivacao(itemDTO.getMotivacao().toUpperCase());

		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);

		if (!descritorService.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			descritorService.addDescritor(itemDTO.getDescritor());

		if (usuarioDoToken.get().getTipo() == TipoUsuario.DOADOR) {
			throw new NaoAutorizadoException("Usuário não autorizado",
					"Você precisa ser um receptor para utilizar esta funcionalidade.");
		}

		ItemNecessario item = itemDTO.getItem();
		item.setReceptor(usuarioDoToken.get());
		itensNecessarioRepositorio.save(item);
		return new ItemNecessarioDTOResponse(item, item.getReceptor());

	}

	public ItemDoacaoDTOResponse addItemDoacao(ItemDoacaoDTO itemDTO, String authHeader) {
		itemDTO.setNome(itemDTO.getNome().toUpperCase());
		itemDTO.setDescricaoDetalhada(itemDTO.getDescricaoDetalhada().toUpperCase());

		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);

		if (!descritorService.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe",
					"Este descritor não está cadastrado no sistema.");

		if (usuarioDoToken.get().getTipo() == TipoUsuario.RECEPTOR) {
			throw new NaoAutorizadoException("Usuário não autorizado",
					"Você precisa ser um doador para utilizar esta funcionalidade.");
		}

		ItemDoacao item = itemDTO.getItem();
		item.setDoador(usuarioDoToken.get());
		itensDoacaoRepositorio.save(item);
		return new ItemDoacaoDTOResponse(item, item.getDoador());
	}

	public List<ItemDoacao> getItens() {
		return itensDoacaoRepositorio.findAll();
	}

	public ItemDTODeleted removeItemNecessario(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);

		if (!itensDoacaoRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");

		if (itensNecessarioRepositorio.findById(id).get().getReceptor() != usuarioDoToken.get())
			throw new NaoAutorizadoException("Usuário não autorizado!", "Este item é de propriedade de outro usuário.");
		ItemNecessario itemResponse = itensNecessarioRepositorio.findById(id).get();
		itensNecessarioRepositorio.delete(itemResponse);
		return new ItemDTODeleted(itemResponse.getNome(), itemResponse.getMotivacao());

	}

	public ItemDTODeleted removeItemDoacao(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = usuariosRepositorio.findByEmail(subject);

		if (!itensDoacaoRepositorio.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");

		if (itensDoacaoRepositorio.findById(id).get().getDoador() != usuarioDoToken.get())
			throw new NaoAutorizadoException("Usuário não autorizado!", "Este item é de propriedade de outro usuário.");

		ItemDoacao itemResponse = itensDoacaoRepositorio.findById(id).get();
		itensDoacaoRepositorio.delete(itemResponse);
		return new ItemDTODeleted(itemResponse.getNome(), itemResponse.getDescricaoDetalhada());

	}

}

package com.projetodsc.edoe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetodsc.edoe.exceptions.DescritorInvalidoException;
import com.projetodsc.edoe.exceptions.DescritorNaoExisteException;
import com.projetodsc.edoe.exceptions.DoacaoInvalidaException;
import com.projetodsc.edoe.exceptions.ItemInvalidoException;
import com.projetodsc.edoe.exceptions.ItemNaoEncontradoException;
import com.projetodsc.edoe.exceptions.NaoAutorizadoException;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.Item;
import com.projetodsc.edoe.models.TipoItem;
import com.projetodsc.edoe.models.TipoUsuario;
import com.projetodsc.edoe.models.Usuario;
import com.projetodsc.edoe.models.dtos.ItemResponse;
import com.projetodsc.edoe.models.dtos.Doacao;
import com.projetodsc.edoe.models.dtos.DoacaoResponse;
import com.projetodsc.edoe.models.dtos.ItemDTO;
import com.projetodsc.edoe.repositories.DoacoesRepository;
import com.projetodsc.edoe.repositories.ItensRepository;
import com.projetodsc.edoe.repositories.UsuariosRepository;

@Service
public class ItemService {

	@Autowired
	private ItensRepository repositorioDeItens;
	@Autowired
	private UsuariosRepository repositorioDeUsuarios;
	@Autowired
	private DoacoesRepository repositorioDeDoacoes;
	@Autowired
	private JWTService jwtService;
	@Autowired
	private DescritorService descritorService;

	
	public List<DoacaoResponse> historicoDeDoacoes(){
		List<DoacaoResponse> listResponse = new ArrayList<>(); 
		for (Doacao doacao : repositorioDeDoacoes.findAll()) {
			listResponse.add(new DoacaoResponse(doacao));
		}
		return listResponse;
	}
	
	public ItemResponse realizarDoacao(Doacao dadosDoacao, String authHeader){
		
		if (dadosDoacao.getQuantidadeDoacao() < 1)
			throw new DoacaoInvalidaException("Doação inválida!", "A quantia de doações deve ser maior que 0.");
		
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorioDeUsuarios.findByEmailIgnoreCase(subject);

		if (usuarioDoToken.get().getTipo() == TipoUsuario.RECEPTOR)
			throw new NaoAutorizadoException("Usuário não autorizado", "Esta funcionalidade é apenas para doadores.");

		long idItemDoacao = dadosDoacao.getIdItemDoacao();
		long idItemNecessario = dadosDoacao.getIdItemNecessario();
		int quantidadeDoacao = dadosDoacao.getQuantidadeDoacao();
		
		if (idItemDoacao == idItemNecessario)
			throw new DoacaoInvalidaException("Falha na doação!", "Os itens têm id iguais. Verifique-os e tente novamente.");
		
		if (!repositorioDeItens.existsById(idItemDoacao) || repositorioDeItens.findById(idItemDoacao).get().getTipo() != TipoItem.DOACAO)
			throw new ItemInvalidoException("Item não encontrado", "O item com o id " + idItemDoacao + " não foi encontrado ou não é um item para doação.");
			
		if (!repositorioDeItens.existsById(idItemNecessario) || repositorioDeItens.findById(idItemNecessario).get().getTipo() != TipoItem.NECESSARIO)
			throw new ItemInvalidoException("Item não encontrado", "O item com o id " + idItemNecessario + " não foi encontrado ou não é um item necessário.");
			
		Item itemDoacao = repositorioDeItens.findById(idItemDoacao).get();
		Item itemNecessario = repositorioDeItens.findById(idItemNecessario).get();
		
		if (usuarioDoToken.get() != itemDoacao.getUsuario())
			throw new NaoAutorizadoException("Não autorizado", "Este item pertence a outro doador.");
		
		if (itemDoacao.getUsuario() == itemNecessario.getUsuario())
			throw new DoacaoInvalidaException("Falha da doação", "Você não pode realizar doações para si mesmo.");

		if (itemDoacao.getDescritor() != itemNecessario.getDescritor())
			throw new ItemInvalidoException("Falha na doação!", "Os itens não têm o mesmo descritor");
		
		if (itemDoacao.getQuantidade() < quantidadeDoacao)
			throw new ItemInvalidoException("Falha na doação!", "Você não pode doar mais itens do que tem");
		
		if (quantidadeDoacao > itemNecessario.getQuantidade())
			throw new ItemInvalidoException("Falha na doação!", "A quantidade máxima de doações para este item é " + itemNecessario.getQuantidade());
		
		itemDoacao.setQuantidade(itemDoacao.getQuantidade() - quantidadeDoacao);
		repositorioDeItens.save(itemDoacao);
		itemNecessario.setQuantidade(itemNecessario.getQuantidade() - quantidadeDoacao);
		repositorioDeItens.save(itemNecessario);
		
		repositorioDeDoacoes.save(new Doacao(idItemDoacao, idItemNecessario, quantidadeDoacao));
		
		if (itemDoacao.getQuantidade() == 0)
			repositorioDeItens.delete(itemDoacao);
		if(itemNecessario.getQuantidade() == 0)
			repositorioDeItens.delete(itemNecessario);
		return new ItemResponse(itemDoacao);
	}
	
	public List<ItemResponse> getItensByStringBusca(String stringBusca, TipoItem tipo) {
		List<ItemResponse> listResponse = new ArrayList<>();
		for (Descritor descritor : descritorService.getDescritoresByDescricaoContaining(stringBusca)) {
			List<Item> itensDoac = repositorioDeItens.findByDescritor(descritor).get();
			for (Item item : itensDoac) {
				if (item.getTipo() == tipo)
					listResponse.add(new ItemResponse(item));
			}
		}
		return listResponse;
	}

	public List<ItemResponse> getItensOrderByQuantidadeDesc(TipoItem tipo) {
		List<Item> itens = repositorioDeItens.findAllByOrderByQuantidadeDesc().get();
		List<ItemResponse> listResponse = new ArrayList<>();
		for (Item i : itens) {
			if (i.getTipo() == tipo) {
				ItemResponse item = new ItemResponse(i);
				listResponse.add(item);
				if (listResponse.size() == 10)
					return listResponse;
			}
		}
		return listResponse;
	}

	public Item setDados(Item item, Item itemAtualizado) {
		item.setNome(itemAtualizado.getNome().toUpperCase());
		item.setDescricaoDetalhada(itemAtualizado.getDescricaoDetalhada().toUpperCase());
		item.setQuantidade(itemAtualizado.getQuantidade());
		item.setDescritor(itemAtualizado.getDescritor());
		return item;
	}
	
	public List<ItemResponse> matchesById(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorioDeUsuarios.findByEmailIgnoreCase(subject);

		if (usuarioDoToken.get().getTipo() == TipoUsuario.DOADOR)
			throw new NaoAutorizadoException("Usuário não autorizado", "Esta funcionalidade é apenas para receptores.");
		
		if (!repositorioDeItens.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");
	
		Item item = repositorioDeItens.findById(id).get();
			if (item.getTipo() != TipoItem.NECESSARIO) {
				throw new ItemInvalidoException("Item não encontrado", "Este id pertence a um item de doação.");
			}
		
		List<ItemResponse> listResponse = new ArrayList<>();
		if (!repositorioDeItens.existsByDescritor(item.getDescritor()))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Não existem itens para doação com o descritor " + item.getDescritor().getDescricao());

			
		for (Item i : repositorioDeItens.findAllByDescritorAndTipo(item.getDescritor(), item.getTipo()).get()) {
			listResponse.add(new ItemResponse(i));
		}

		return listResponse;
			
		
		
	}

	public ItemResponse atualizaItem(long id, ItemDTO dadosAtualizados, TipoItem tipo, String authHeader) {
		if (!repositorioDeItens.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");

		Item item = repositorioDeItens.findById(id).get();
		item.setTipo(tipo);
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorioDeUsuarios.findByEmailIgnoreCase(subject);

		if (usuarioDoToken.get() != item.getUsuario())
			throw new NaoAutorizadoException("Não autorizado", "Este item pertence a outro doador.");
		item = repositorioDeItens.save(setDados(item, dadosAtualizados.getItem()));
		return new ItemResponse(item);
	}

	public List<ItemResponse> getItensByDescritor(Descritor descritor, TipoItem tipo) {

		if (!descritorService.existsByDescricao(descritor.getDescricao().toUpperCase()))
			throw new DescritorInvalidoException("Descritor inválido", "Este descritor não existe no sistema.");

		List<ItemResponse> response = new ArrayList<>();
		for (Item i : repositorioDeItens.findByDescritor(descritor).get()) {
			if (i.getTipo() == tipo)
				response.add(new ItemResponse(i));
		}
		return response;
	}

	public ItemResponse adicionaItem(ItemDTO itemDTO, TipoItem tipoItem, String authHeader) {
		itemDTO.setTipo(tipoItem);
		itemDTO.setNome(itemDTO.getNome().toUpperCase());
		itemDTO.setDescricaoDetalhada(itemDTO.getDescricaoDetalhada().toUpperCase());
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorioDeUsuarios.findByEmailIgnoreCase(subject);
		
		if (tipoItem == TipoItem.NECESSARIO && !descritorService.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			descritorService.addDescritor(itemDTO.getDescritor());
		if (!descritorService.existsByDescricao(itemDTO.getDescritor().getDescricao()))
			throw new DescritorNaoExisteException("Descritor não existe",
					"Este descritor não está cadastrado no sistema.");
		
		if (itemDTO.getTipo() == TipoItem.DOACAO && usuarioDoToken.get().getTipo() == TipoUsuario.RECEPTOR)
			throw new NaoAutorizadoException("Usuário não autorizado",
					"Itens para doação só podem ser cadastrados por doadores.");

		if (itemDTO.getTipo() == TipoItem.NECESSARIO && usuarioDoToken.get().getTipo() == TipoUsuario.DOADOR)
			throw new NaoAutorizadoException("Usuário não autorizado",
					"Itens necessários só podem ser cadastrados por receptores.");


		Item item = itemDTO.getItem();
		item.setUsuario(usuarioDoToken.get());
		itemDTO.setUsuario(usuarioDoToken.get());
		repositorioDeItens.save(item);
		return new ItemResponse(itemDTO);
	}

	public List<ItemResponse> getItens(TipoItem tipo) {
		List<ItemResponse> listResponse = new ArrayList<>();
		for (Item item : repositorioDeItens.findAll()) {
			if (item.getTipo() == tipo)
				listResponse.add(new ItemResponse(item));
		}
		return listResponse;
	}

	public ItemResponse removeItem(long id, String authHeader) {
		String subject = jwtService.getSujeitoDoToken(authHeader);
		Optional<Usuario> usuarioDoToken = repositorioDeUsuarios.findByEmailIgnoreCase(subject);

		if (!repositorioDeItens.existsById(id))
			throw new ItemNaoEncontradoException("Item não encontrado!", "Nenhum item com o id " + id + " no sistema.");

		if (repositorioDeItens.findById(id).get().getUsuario() != usuarioDoToken.get())
			throw new NaoAutorizadoException("Usuário não autorizado!", "Este item é de propriedade de outro usuário.");

		Item item = repositorioDeItens.findById(id).get();
		repositorioDeItens.delete(item);
		return new ItemResponse(item);

	}

}

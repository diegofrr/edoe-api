package com.projetodsc.edoe.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemDoacao;

@Repository
public interface ItensDoacaoRepository extends JpaRepository<ItemDoacao, Long>{
	
	boolean existsById(long id);
	Optional<List<ItemDoacao>> findByDescritor(Descritor descritor);
	Optional<List<ItemDoacao>> findTop10ByOrderByQuantidadeDoacaoDesc();
}

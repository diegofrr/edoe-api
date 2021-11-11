package com.projetodsc.edoe.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projetodsc.edoe.models.Descritor;
import com.projetodsc.edoe.models.ItemNecessario;

@Repository
public interface ItensNecessarioRepository extends JpaRepository<ItemNecessario, Long>{
	
	boolean existsById(long id);
	Optional<List<ItemNecessario>> findByDescritor(Descritor descritor);
	Optional<List<ItemNecessario>> findTop10ByOrderByQuantidadeNecessariaDesc();
	Optional<List<ItemNecessario>> findByMotivacaoIgnoreCaseContaining(String motivacao);
	Optional<List<ItemNecessario>> findAllByDescritor(List<Descritor> descritores);
}


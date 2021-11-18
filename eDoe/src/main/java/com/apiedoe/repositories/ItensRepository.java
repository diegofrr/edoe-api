package com.apiedoe.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apiedoe.models.Descritor;
import com.apiedoe.models.Item;
import com.apiedoe.models.TipoItem;

@Repository
public interface ItensRepository extends JpaRepository<Item, Long>{
	
	boolean existsById(long id);
	Optional<List<Item>> findByDescritor(Descritor descritor);
	Optional<List<Item>> findAllByDescritor(Descritor descritor);
	Optional<List<Item>> findAllByOrderByQuantidadeDesc();
	Optional<List<Item>> findAllByDescritorAndTipo(Descritor descritor, TipoItem tipo);
	boolean existsByDescritor(Descritor descritor);
	
}

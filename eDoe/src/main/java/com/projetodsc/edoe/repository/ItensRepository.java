package com.projetodsc.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetodsc.edoe.model.Item;

public interface ItensRepository extends JpaRepository<Item, Long>{
	


}

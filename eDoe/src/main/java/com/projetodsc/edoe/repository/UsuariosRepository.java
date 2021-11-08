package com.projetodsc.edoe.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetodsc.edoe.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, String> {
	
	Optional<Usuario> findByEmail(String email);
	boolean existsByEmail(String email); 

}

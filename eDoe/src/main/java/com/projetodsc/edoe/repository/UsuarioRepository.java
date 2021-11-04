package com.projetodsc.edoe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetodsc.edoe.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByEmail(String email); 
	public Optional<Usuario> findByNome(String nome); 
	public List<Usuario> findByNomeContaining(String padrao);
	public boolean existsByNome(String nome);

}

package com.orders.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orders.entities.Usuario;


@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String>  {

	Optional<Usuario> findByMail(String mail);
	boolean existsByMail(String mail);
	
	Optional<Usuario> findById(String id);
	boolean existsById(String id);
	
	@Query("SELECT c FROM Usuario c WHERE c.mail = :mail")
	public Usuario buscarPorMail(@Param("mail") String mail);
	
	@Query("SELECT c FROM Usuario c WHERE c.lastname LIKE :valor")
	public List<Usuario> listarPorApellido(String valor);

}

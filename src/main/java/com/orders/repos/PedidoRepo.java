package com.orders.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orders.entities.Pedido;
import com.orders.entities.Producto;
import com.orders.entities.Usuario;

@Repository
public interface PedidoRepo extends JpaRepository<Pedido, String> {

	Optional<Producto> findByUsuario(Usuario usuario);
	
	@Query("SELECT c FROM Pedido c WHERE c.usuario.name LIKE :valor")
	public List<Producto> listarPorUsuario(String valor);
        
        @Query("SELECT c FROM Pedido c WHERE c.id LIKE :q OR c.usuario.name LIKE :q OR c.usuario.lastname LIKE :q")
        public List<Pedido> buscarPorQ(@Param("q") String q);
}

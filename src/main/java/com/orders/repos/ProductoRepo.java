package com.orders.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orders.entities.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findById(String id);
    
    @Query("SELECT c FROM Producto c WHERE c.nombre LIKE :valor")
    public List<Producto> listarPorNombre(String valor);

    @Query("SELECT c FROM Producto c WHERE c.descrip LIKE :valor")
    public List<Producto> listarPorDescrip(String valor);

}

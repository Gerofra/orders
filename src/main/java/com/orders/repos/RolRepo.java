package com.orders.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orders.entities.Rol;
import com.orders.enums.RolEnum;

@Repository
public interface RolRepo extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolEnum(RolEnum rolEnum);
	boolean existsByRolEnum(RolEnum rolEnum);
}

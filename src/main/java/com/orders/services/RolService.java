package com.orders.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.entities.Rol;
import com.orders.enums.RolEnum;
import com.orders.repos.RolRepo;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepo rolRepo;
	
	public void save(Rol rol) {
		rolRepo.save(rol);
	}
	
	public Optional<Rol> getById(Integer id) {
		return rolRepo.findById(id);
	}

	public Optional<Rol> getByRolEnum(RolEnum rol) {
		return rolRepo.findByRolEnum(rol);
	}
	
	public boolean existById(Integer id) {
		return rolRepo.existsById(id);
	}

	public boolean existByRolEnum(RolEnum rol) {
		return rolRepo.existsByRolEnum(rol);
	}
}

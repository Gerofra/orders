package com.orders.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import com.orders.enums.RolEnum;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RolEnum rolEnum;

	

	public Rol() {
		super();
	}



	public Rol(Integer id, RolEnum rolEnum) {
		super();
		this.id = id;
		this.rolEnum = rolEnum;
	}



	public Rol(RolEnum rolEnum) {
		super();
		this.rolEnum = rolEnum;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public RolEnum getRolEnum() {
		return rolEnum;
	}



	public void setRolEnum(RolEnum rolEnum) {
		this.rolEnum = rolEnum;
	}



	
	
}

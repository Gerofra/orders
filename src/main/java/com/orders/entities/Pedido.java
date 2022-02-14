package com.orders.entities;




import java.util.Date;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.orders.enums.PedidoEnum;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pedido {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@ManyToOne
	private Usuario usuario;
	

	private String id_productos;
	private String cant_productos;
    private PedidoEnum estado;
    private String detalle;
	private Date fechaPedido;
	private Date fechaEntrega;
	private String total;
	
	
	
	


	
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public PedidoEnum getEstado() {
		return estado;
	}
	public void setEstado(PedidoEnum estado) {
		this.estado = estado;
	}
	public String getId_productos() {
		return id_productos;
	}
	public void setId_productos(String id_productos) {
		this.id_productos = id_productos;
	}
	public String getCant_productos() {
		return cant_productos;
	}
	public void setCant_productos(String cant_productos) {
		this.cant_productos = cant_productos;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public Date getFechaPedido() {
		return fechaPedido;
	}
	


	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	

	
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
	
	
}

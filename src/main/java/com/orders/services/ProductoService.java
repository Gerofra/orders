package com.orders.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.entities.Producto;
import com.orders.errors.ErrorService;
import com.orders.repos.ProductoRepo;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepo productoRepo;


    @Transactional
    public void agregar(String nombre, String marca, String precio, String color, String descrip) throws ErrorService {

        validar(nombre, marca, precio, descrip);

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setPrecio(precio);
        producto.setColor(color);
        producto.setDescrip(descrip);

        productoRepo.save(producto);

    }

    @Transactional
    public void modificar(String nombre, String marca, String precio, String color, String descrip, Long id) throws ErrorService {

        Optional<Producto> respuesta = productoRepo.findById(id);

        if (respuesta.isPresent()) {
            Producto producto = respuesta.get();
            if(nombre != null && !nombre.equals("")) {
            	producto.setNombre(nombre);
            }
            if(marca != null && !marca.equals("")) {
            	producto.setMarca(marca);
            }
            if(precio != null && !precio.equals("")) {
            	producto.setPrecio(precio);
            }
            if(color != null && !color.equals("")) {
            	producto.setColor(color);
            }
            if(descrip != null && !descrip.equals("")) {
            	producto.setDescrip(descrip);
            }

            productoRepo.save(producto);

        } else {
            throw new ErrorService("No se encontró el producto solicitado.");
        }

    }

    @Transactional
    public void delete(Long id) {
        Optional<Producto> respuesta = productoRepo.findById(id);
        if (respuesta.isPresent()) {
            productoRepo.delete(respuesta.get());
        }
    }

    @Transactional
    private void validar(String nombre, String marca, String precio, String descrip) throws ErrorService {

        System.out.println("Entró a validarse.");

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("El nombre del producto no puede ser nulo.");
        }
        if (marca == null || marca.isEmpty()) {
            throw new ErrorService("La marca del producto no puede ser nula.");
        }
        if (precio == null || precio.isEmpty()) {
            throw new ErrorService("El precio del producto no puede ser nulo.");
        }

        if (descrip == null || descrip.isEmpty()) {
            throw new ErrorService("La descripción del producto no puede ser nula..");
        }

        System.out.println("Terminó de validarse.");
    }

    @Transactional
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepo.listarPorNombre(nombre + "%");
    }

    @Transactional
    public List<Producto> buscarPorDescrip(String descrip) {
        return productoRepo.listarPorDescrip(descrip + "%");
    }
    
    @Transactional
    public Producto buscarPorId(Long id) {
        Optional<Producto> respuesta = productoRepo.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        }
		return null;
    }

    /*	
	@Transactional
	public Map<Producto,String> buscarMuchosPorId(String id_pedido) throws ErrorService {
		
		Map<String,String> map = pedidoService.listadoProductos(id_pedido);
		
		Map<Producto,String> productos = new LinkedHashMap<>();
		
		
        for (Map.Entry<String,String> entry : map.entrySet()) {
        	
        	Optional<Producto> respuesta = productoRepo.findById(Long.valueOf(entry.getKey()));

        	if (respuesta.isPresent()) {
        		Producto producto = respuesta.get();
        		productos.put(producto, entry.getValue());
        	}
        		
        }
        
		return productos;
	}
     */
}

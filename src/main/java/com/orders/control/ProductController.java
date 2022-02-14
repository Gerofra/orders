package com.orders.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orders.repos.ProductoRepo;

import com.orders.services.ProductoService;

@Controller

@PreAuthorize("isAuthenticated()")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepo productoRepo;

    @GetMapping("")
    public String products(Model model) {
        model.addAttribute("products", productoRepo.findAll());
        return "products.html";
    }

    @PostMapping("/save")
    public String save(ModelMap modelo, @RequestParam String nombre, @RequestParam String marca, @RequestParam String precio, @RequestParam String color, @RequestParam String descrip) {

        try {
            productoService.agregar(nombre, marca, precio, color, descrip);

        } catch (Exception e) {
            modelo.addAttribute("products", productoRepo.findAll());
            modelo.addAttribute("error", e.getMessage());
            return "products.html";
        }
        modelo.addAttribute("products", productoRepo.findAll());
        modelo.addAttribute("success", "El producto se ha agregado correctamente.");
        return "products";
    }

    
    //Tira error 500 cuando se quiere borrar cualquier elemento, se efectua la transaccion. Cuando se quiere eliminar el ultimo producto no lanza error.
    //Posible solucion: pasarlo a tipo UUID, String.
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, ModelMap modelo) {
        productoService.delete(id);
        modelo.addAttribute("products", productoRepo.findAll());
        modelo.addAttribute("success", "Se ha eliminado el producto correctamente.");
        return "products.html";
    }

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/update")
    public String update(@RequestParam(required = true) Long id, ModelMap modelo) {
    	System.out.println("----------------" + id);
        modelo.addAttribute("products", productoRepo.findAll());
        modelo.addAttribute("productEdit", productoService.buscarPorId(id));
        return "products";
    }
    
    @PostMapping("/saveUpdate")
    public String saveUpdate(ModelMap modelo, @RequestParam String nombre, @RequestParam String marca, @RequestParam String precio, @RequestParam String color, @RequestParam String descrip,  @RequestParam Long id) {

        try {
            productoService.modificar(nombre, marca, precio, color, descrip, id);

        } catch (Exception e) {
            modelo.addAttribute("products", productoRepo.findAll());
            modelo.addAttribute("error", e.getMessage());
            return "products.html";
        }
        modelo.addAttribute("products", productoRepo.findAll());
        modelo.addAttribute("success", "El producto se ha editado correctamente.");
        return "products";
    }
}

package com.orders.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.orders.errors.ErrorService;
import com.orders.repos.PedidoRepo;

import com.orders.services.PedidoService;

@PreAuthorize("isAuthenticated()")
@Controller
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private PedidoRepo pedidoRepo;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("")
    public String orders(Model model, @RequestParam(required = false) String q) throws ErrorService {

        if (q != null) {
            model.addAttribute("orders", pedidoService.listaPedidosQ(q));
        } else {
            model.addAttribute("orders", pedidoRepo.findAll());
        }

        return "orders.html";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable("id") String id, Model model, RedirectAttributes redirect) throws ErrorService {
        System.out.println("ID: " + id);

        redirect.addFlashAttribute("infoPedido", pedidoService.mostrarPedido(id));
        redirect.addFlashAttribute("productosPedido", pedidoService.listadoProductos(id));

        return "redirect:/orders?{id}";
    }

    @PostMapping("")
    public String save(ModelMap modelo, @RequestParam String id, @RequestParam String estado, RedirectAttributes redirect) {

        try {
            pedidoService.cambiarEstado(id, estado);
        } catch (Exception e) {
            modelo.addAttribute("error", e.getMessage());
            return "orders.html";
        }

        redirect.addFlashAttribute("success", "Se ha registrado correctamente.");
        return "redirect:/orders";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id, RedirectAttributes redirect) {
        pedidoService.delete(id);
        redirect.addFlashAttribute("success", "El pedido se ha eliminado correctamente.");
        return "redirect:/orders";

    }

}

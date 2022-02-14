package com.orders.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorControlador implements ErrorController {

	@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
	public String paginaError(Model model, HttpServletRequest request) {
		
		String msg = "";
		
		int codError = (int) request.getAttribute("javax.servlet.error.status_code");
		
		switch (codError) {
		case 400:
			msg = "El recurso solicitado no existe.";
			break;
		case 401:
			msg = "No se encuentra autorizado";
			break;
		case 403:
			msg = "No tiene permiso para acceder al recurso";
			break;
		case 404:
			msg = "El recurso solicitado no se ha encontrado.";
			break;
		case 500:
			msg = "El servidor no ha podido realizar la conexión con éxito.";
			break;
		default:
			break;
		}
		
		model.addAttribute("codigo", codError);
		model.addAttribute("msg", msg);
		
		return "error";
	}
	
}

package com.sistema.facturacion.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.facturacion.Iservice.IserviceApp;
import com.sistema.facturacion.models.entity.Cliente;

@Controller
@RequestMapping("/facturacion")
public class MainController {
	
	@Autowired
	private IserviceApp serviceApp;
	
	@RequestMapping("/usuarios")
	public String showMain(Model model) {
		List<Cliente> clientes = serviceApp.getCliente();
		model.addAttribute("titulo","Main Page");
		model.addAttribute("clientes", clientes);
		return "main";
	}
	
	@RequestMapping("/factura/{id}")
	public String mostrarFacturas(@PathVariable(value = "id") Long id, Model model) {
		Cliente cliente = serviceApp.getClienteById(id);
		model.addAttribute("titulo","Detail Page");
		model.addAttribute("cliente", cliente);
		return "detail";
	}
	
	@RequestMapping("/formUsuarios")
	public String formularioUsuarios(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Usuario");
		model.addAttribute("cliente",cliente);
		return "usersForm";
	}
	
	@RequestMapping("/formUsuarios/{id}")
	public String formularioEditUsuarios(@PathVariable(name = "id") Long id, Model model) {
		Cliente cliente = new Cliente();
		cliente = serviceApp.getClienteById(id);
		model.addAttribute("titulo","Usuario");
		cliente.setId(id);
		model.addAttribute("cliente",cliente);
		return "usersForm";
	}
	
	@RequestMapping(value = "/saveUsuarios", method = RequestMethod.POST)
	public String guardarUsuario(@Valid Cliente cliente,BindingResult br, RedirectAttributes redirect, Model model) {
		if(!br.hasErrors()) {
			if(cliente.getId() == null || cliente.getId() <= 0) {
				System.out.println("Called create "+ cliente.getId());
				redirect.addFlashAttribute("message", "El cliente fue creado correctamente");
				boolean answ = serviceApp.guardarCliente(cliente);
			}else {
				System.out.println("Called edit");
				redirect.addFlashAttribute("message", "El cliente fue editado correctamente");
				boolean answ = serviceApp.editarCliente(cliente);
			}
			return "redirect:usuarios";
		}
		return "usersForm";
	}
	
	@RequestMapping(value = "/borrarUsuarios/{id}")
	public String borrarUsuario(@PathVariable(name = "id") Long id, RedirectAttributes redirect,Model model) {
		Cliente cliente = serviceApp.getClienteById(id);
		if(cliente.getId() != null && cliente.getId() > 0) {
			System.out.println("Called delete");
			serviceApp.borrarCliente(cliente);
			redirect.addFlashAttribute("message", "El cliente fue eliminado correctamente");
		}else {
			System.out.println("Id : "+cliente.getId()+ "nombre: "+cliente.getNombre());
		}
		return "redirect:/facturacion/usuarios";
	}

}

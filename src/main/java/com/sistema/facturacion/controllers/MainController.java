package com.sistema.facturacion.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String guardarUsuario(Cliente cliente, Model model) {
		if(cliente.getId() == null || cliente.getId() <= 0) {
			System.out.println("Called create "+ cliente.getId());
			boolean answ = serviceApp.guardarCliente(cliente);
		}else {
			System.out.println("Called edit");
			boolean answ = serviceApp.editarCliente(cliente);
		}
		return "redirect:usuarios";
	}
	
	@RequestMapping(value = "/borrarUsuarios", method = RequestMethod.POST)
	public String borrarUsuario(Cliente cliente, Model model) {
		return "redirect:usuarios";
	}

}

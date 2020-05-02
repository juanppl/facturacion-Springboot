package com.sistema.facturacion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sistema.facturacion.Iservice.IserviceApp;
import com.sistema.facturacion.models.entity.Cliente;
import com.sistema.facturacion.models.entity.Factura;
import com.sistema.facturacion.models.entity.Producto;

@Controller
@RequestMapping("/modFacturas")
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private IserviceApp serviceApp;
	
	@GetMapping("/factura/{clienteId}")
	public String crearFactura(@PathVariable("clienteId") Long id,Model model) {
		Cliente cliente = serviceApp.getClienteById(id);
		if(cliente == null) {
			return "redirect:/facturacion/usuarios";
		}
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.addAttribute("factura",factura);
		return "/facturas/facturaForm";
	}
	
	@GetMapping(value = "/cargarProductos/{term}", produces = {"application/json"} )
	public @ResponseBody List<Producto> cargarProductos(@PathVariable("term") String term) {
		List<Producto> productos = serviceApp.buscarProductosPorNombre(term);
		return productos;
	}

}

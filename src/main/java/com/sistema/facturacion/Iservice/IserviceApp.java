package com.sistema.facturacion.Iservice;

import java.util.List;

import com.sistema.facturacion.models.entity.Cliente;
import com.sistema.facturacion.models.entity.Producto;

public interface IserviceApp {
	
	public List<Cliente> getCliente();
	
	public Cliente getClienteById(Long id);
	
	public boolean guardarCliente(Cliente cliente);
	
	public boolean editarCliente(Cliente cliente);
	
	public boolean borrarCliente(Cliente cliente);
	
	public List<Producto> buscarProductosPorNombre(String term);

}

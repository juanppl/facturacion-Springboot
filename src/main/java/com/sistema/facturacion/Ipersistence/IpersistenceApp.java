package com.sistema.facturacion.Ipersistence;

import java.util.List;

import com.sistema.facturacion.models.entity.Cliente;

public interface IpersistenceApp {
	
	public List<Cliente> getClientes();
	
	public Cliente getClienteById(Long id);
	
	public boolean guardarCliente(Cliente cliente);

	public boolean editarCliente(Cliente cliente);
	
	public boolean borrarCliente(Cliente cliente);
}

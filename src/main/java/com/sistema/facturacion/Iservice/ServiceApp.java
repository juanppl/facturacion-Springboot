package com.sistema.facturacion.Iservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.facturacion.Ipersistence.IpersistenceApp;
import com.sistema.facturacion.models.entity.Cliente;
import com.sistema.facturacion.models.entity.Producto;

@Service
public class ServiceApp implements IserviceApp{

	@Autowired
	private IpersistenceApp persistenceApp;
	
	@Override
	public List<Cliente> getCliente() {
		return persistenceApp.getClientes();
	}

	@Override
	public Cliente getClienteById(Long id) {
		return persistenceApp.getClienteById(id);
	}

	@Override
	public boolean guardarCliente(Cliente cliente) {
		return persistenceApp.guardarCliente(cliente);
	}

	@Override
	public boolean editarCliente(Cliente cliente) {
		return persistenceApp.editarCliente(cliente);
	}

	@Override
	public boolean borrarCliente(Cliente cliente) {
		return persistenceApp.borrarCliente(cliente);
	}

	@Override
	public List<Producto> buscarProductosPorNombre(String term) {
		return persistenceApp.buscarProductosPorNombre(term);
	}

}

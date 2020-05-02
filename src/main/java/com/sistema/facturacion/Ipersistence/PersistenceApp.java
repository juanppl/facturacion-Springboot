package com.sistema.facturacion.Ipersistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.facturacion.models.entity.Cliente;
import com.sistema.facturacion.models.entity.Producto;

@Repository
public class PersistenceApp implements IpersistenceApp {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cliente> getClientes() {
		try {
			List<Cliente> clientes = em.createNamedQuery("findAllClients", Cliente.class).getResultList();
			return clientes;
		} catch (Exception e) {
			System.out.println("Exception getting all clients + " + e);
			return null;
		}
	}

	@Override
	public Cliente getClienteById(Long id) {
		try {
			Query query = em.createNamedQuery("findClientById", Cliente.class);
			query.setParameter("id", id);
			Cliente cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (Exception e) {
			System.out.println("Exception getting client by id " + e);
			return null;
		}
	}

	@Transactional
	@Override
	public boolean guardarCliente(Cliente cliente) {
		boolean answ = false;
		try {
			em.persist(cliente);
			answ = true;
		} catch (Exception e) {
			System.out.println("Exception saving client " + e);
		}
		return answ;
	}

	@Transactional
	@Override
	public boolean editarCliente(Cliente cliente) {
		boolean answ = false;
		try {
			em.merge(cliente);
			answ = true;
		} catch (Exception e) {
			System.out.println("Exception editing client " + e);
		}
		return false;
	}

	@Transactional
	@Override
	public boolean borrarCliente(Cliente cliente) {
		try {
			em.remove(cliente);
		} catch (Exception e) {
			System.out.println("Exception removing client " + e);
		}
		return false;
	}

	@Override
	public List<Producto> buscarProductosPorNombre(String term) {
		try {
			List<Producto> productos = em.createNamedQuery("findAllProducts", Producto.class).setParameter("term", "%"+term+"%") .getResultList();
			return productos;
		} catch (Exception e) {
			System.out.println("Exception getting all products + " + e);
			return null;
		}
	}

}

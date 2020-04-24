package com.sistema.facturacion.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "findAllClients", query = "select c from Cliente c"),
	@NamedQuery(name = "findClientById", query = "select c from Cliente c where c.id = :id"),
})
@Entity
@Table(name="clientes")
public class Cliente implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "nombre")
	private String nombre;
	
	@NotEmpty
	@Column(name = "apellido")
	private String apellido;
	
	@NotEmpty
	@Email
	@Column(name = "mail")
	private String mail;
	
	@NotNull
	@Column(name = "created")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created;
	
	@Column(name = "foto")
	private String foto;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Factura> facturas; 
	
	public Cliente(){
		facturas = new ArrayList<Factura>();
	}
	
//	@PrePersist
//	public void prePersist() {
//		created = new Date();
//	}
	
	public void agregarFactura(Factura factura) {
		facturas.add(factura);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
}

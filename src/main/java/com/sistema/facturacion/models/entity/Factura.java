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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "facturas")
public class Factura implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "observacion")
	private String observacion;
	
	@NotNull
	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> itemFactura;
	
	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}
	
	public Factura (){
		itemFactura = new ArrayList<ItemFactura>();
	}
	
	public void agregarItemFactura(ItemFactura itemFactura) {
		this.itemFactura.add(itemFactura);
	}
	
	public double getTotal() {
		double total = 0.0;
		int size = itemFactura.size();
		for(int i = 0; i< size ; i++) {
			total += itemFactura.get(i).calcularIva();
		}
		return total;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItemFactura() {
		return itemFactura;
	}

	public void setItemFactura(List<ItemFactura> itemFactura) {
		this.itemFactura = itemFactura;
	}
	

}

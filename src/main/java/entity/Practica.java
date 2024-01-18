package entity;

import java.sql.Date;

public class Practica {
	
	private Integer id_practica;
	private String descripcion;
	private Integer id_equipo;
	protected Integer estado;
	protected Date fecha_baja;
	
	public Practica () {
	}
	
	
	public Practica(Integer id_practica, String descripcion, Integer id_equipo, Integer estado) {
	
		this.id_practica = id_practica;
		this.descripcion = descripcion;
		this.id_equipo = id_equipo;
		this.estado = estado;
	}
	
	
	public Integer getId_practica() {
		return id_practica;
	}
	public void setId_practica(Integer id_practica) {
		this.id_practica = id_practica;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
	}


	public Integer getEstado() {
		return estado;
	}


	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public Date getFecha_baja() {
		return fecha_baja;
	}


	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}


	

	

	
	
	
	
}

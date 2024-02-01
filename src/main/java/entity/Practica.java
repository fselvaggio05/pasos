package entity;

import java.sql.Date;

public class Practica {
	
	private Integer id_practica;
	private String descripcion;
	private Integer duracion;
	private Integer id_equipo;
	protected Integer estado;
	protected Date fecha_baja;
	protected String desc_equipo; //este campo no pertenece a la pracrica, lo agregue para poder mostrarlo en el servlet 
	
	public Practica () {
	}
	
	
	public Practica(Integer id_practica, String descripcion, Integer id_equipo, Integer estado, Integer duracion) {
	
		this.id_practica = id_practica;
		this.descripcion = descripcion;
		this.id_equipo = id_equipo;
		this.estado = estado;
		this.duracion = duracion;
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


	public String getDesc_equipo() {
		return desc_equipo;
	}


	public void setDesc_equipo(String desc_equipo) {
		this.desc_equipo = desc_equipo;
	}


	public Integer getDuracion() {
		return duracion;
	}


	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	
	

	

	

	
	
	
	
}

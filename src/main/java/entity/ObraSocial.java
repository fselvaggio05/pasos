package entity;

import java.time.LocalDate;

public class ObraSocial {
	
	protected Integer id_obra_social;
	protected String nombre;
	protected Boolean estado;
	protected LocalDate fecha_baja;
	
	public ObraSocial()
	{
		
	}
	
	public ObraSocial(Integer id_obra_social, String nombre, Boolean estado, LocalDate fecha_baja) {
		
		this.id_obra_social = id_obra_social;
		this.nombre = nombre;
		this.estado = estado;
		this.fecha_baja = fecha_baja;
	}

	public Integer getId_obra_social() {
		return id_obra_social;
	}
	
	public void setId_obra_social(Integer id_obra_social) {
		this.id_obra_social = id_obra_social;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public LocalDate getFecha_baja() {
		return fecha_baja;
	}
	
	public void setFecha_baja(LocalDate fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
}
package entity;

import java.time.LocalDate;

public class ObraSocial {
	
	protected Integer id_obra_social;
	protected String nombre_os;
	protected Boolean estado_os;
	protected LocalDate fecha_baja_os;
	
	public ObraSocial()
	{
		
	}
	
	public ObraSocial(Integer id_obra_social, String nombre, Boolean estado, LocalDate fecha_baja) {
		
		this.id_obra_social = id_obra_social;
		this.nombre_os = nombre;
		this.estado_os = estado;
		this.fecha_baja_os = fecha_baja;
	}

	public Integer getId_obra_social() {
		return id_obra_social;
	}
	
	public void setId_obra_social(Integer id_obra_social) {
		this.id_obra_social = id_obra_social;
	}
	
	public String getNombre_os() {
		return nombre_os;
	}
	
	public void setNombre(String nombre) {
		this.nombre_os = nombre;
	}
	
	public Boolean getEstado() {
		return estado_os;
	}
	
	public void setEstado(Boolean estado) {
		this.estado_os = estado;
	}
	
	public LocalDate getFecha_baja() {
		return fecha_baja_os;
	}
	
	public void setFecha_baja(LocalDate fecha_baja) {
		this.fecha_baja_os = fecha_baja;
	}
}
package entity;

import java.time.LocalDate;

public class Consultorio {
	
	protected Integer id_consultorio;
	protected String descripcion;
	protected Boolean estado;
	protected LocalDate fecha_baja;
	
	public Consultorio()
	{
		
	}
	
	
	
	public Consultorio(Integer id_consultorio, String descripcion, Boolean estado, LocalDate fecha_baja) {
		
		this.id_consultorio = id_consultorio;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha_baja = fecha_baja;
	}



	public Integer getId_consultorio() {
		return id_consultorio;
	}
	
	public void setId_consultorio(Integer id_consultorio) {
		this.id_consultorio=id_consultorio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
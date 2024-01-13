package entity;

import java.util.Date;

public class Equipo {
	
	protected Integer id_equipo;
	protected String tipo_equipo; //esto deberia ser un enum
	protected String descripcion;
	protected String estado;
	protected Date fecha_baja;
	
	public Equipo()
	{
		
	}
	
	
	
	public Equipo(Integer id_equipo, String tipo_equipo, String descripcion, String estado, Date fecha_baja) {
		
		this.id_equipo = id_equipo;
		this.tipo_equipo = tipo_equipo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.fecha_baja = fecha_baja;
	}



	public Integer getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
	}
	public String getTipo_equipo() {
		return tipo_equipo;
	}
	public void setTipo_equipo(String tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	
	
	

}

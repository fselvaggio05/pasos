package entity;

import java.sql.Date;

public class Practica {
	
	private Integer id_practica;
	private String descripcion;
	private Equipo equipo;
	private Integer estado;
	private Integer duracion;
	private Date fecha_baja;
	private Double monto;
	
	public Practica () {
	}
	
	
	public Practica(Integer id_practica, String descripcion, Equipo equipo, Integer estado, Integer duracion, Double monto) {	
		this.id_practica = id_practica;
		this.descripcion = descripcion;
		this.equipo = equipo;
		this.estado = estado;
		this.duracion = duracion;
		this.monto = monto;
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
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
}

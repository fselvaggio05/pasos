package entity;

import java.time.LocalDate;
import java.util.List;

import entity.Enumeradores.TipoPractica;

public class Practica {
	
	private Integer id_practica;
	private TipoPractica tipo_practica;
	private String descripcion;
	private Equipo equipo;
	private Boolean estado;
	private Integer duracion;
	private LocalDate fecha_baja;
	private List<Monto_Practica> montos;

	public Practica () {
	}
	
	public Practica(Integer id_practica, String descripcion, Equipo equipo, Boolean estado, Integer duracion) {	
		this.id_practica = id_practica;
		this.descripcion = descripcion;
		this.equipo = equipo;
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
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
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

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public TipoPractica getTipo_practica() {
		return tipo_practica;
	}


	public void setTipo_practica(TipoPractica tipo_practica) {
		this.tipo_practica = tipo_practica;
	}
	
	public List<Monto_Practica> getMonto() {
		return montos;
	}


	public void setMonto(List<Monto_Practica> montos) {
		this.montos = montos;
	}
}

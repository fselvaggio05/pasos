package entity;

import java.time.LocalDate;


//Clase correspondiente a la prescripcion ambulatoria
public class Prescripcion {
	
	
	private Integer id_prescripcion;
	private LocalDate fecha_prescripcion;
	private Paciente paciente;
	private Practica practica;
	private Integer cant_sesiones;
	private Integer sesiones_asistidas;
	private LocalDate fecha_alta_prescripcion;
	private Boolean estado;
	private LocalDate fecha_baja_prescripcion;
		
	public LocalDate getFecha_baja_prescripcion() {
		return fecha_baja_prescripcion;
	}

	public void setFecha_baja_prescripcion(LocalDate fecha_baja_prescripcion) {
		this.fecha_baja_prescripcion = fecha_baja_prescripcion;
	}

	public Prescripcion() {		
	}

	public Prescripcion(LocalDate fecha_prescripcion, Paciente paciente, Practica practica, Integer cant_sesiones, Integer sesiones_asistidas, Double monto_aprobado, Double monto_utilizado, LocalDate fecha_alta_prescripcion, Boolean estado) {
		this.fecha_prescripcion = fecha_prescripcion;
		this.paciente = paciente;
		this.practica = practica;
		this.cant_sesiones = cant_sesiones;
		this.sesiones_asistidas = sesiones_asistidas;
		this.fecha_alta_prescripcion = fecha_alta_prescripcion;
		this.estado = estado;
	}
	
	public Integer getId_prescripcion() {
		return id_prescripcion;
	}

	public void setId_prescripcion(Integer id_prescripcion) {
		this.id_prescripcion = id_prescripcion;
	}

	public LocalDate getFecha_prescripcion() {
		return fecha_prescripcion;
	}
	
	public void setFecha_prescripcion(LocalDate fecha_prescripcion) {
		this.fecha_prescripcion = fecha_prescripcion;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Practica getPractica() {
		return practica;
	}

	public void setPractica(Practica practica) {
		this.practica = practica;
	}
	
	public Integer getCant_sesiones() {
		return cant_sesiones;
	}
	
	public void setCant_sesiones(Integer cant_sesiones) {
		this.cant_sesiones = cant_sesiones;
	}
	
	public Integer getSesiones_asistidas() {
		return sesiones_asistidas;
	}
	
	public void setSesiones_asistidas(Integer sesiones_asistidas) {
		this.sesiones_asistidas = sesiones_asistidas;
	}
	
	public LocalDate getFecha_alta_prescripcion() {
		return fecha_alta_prescripcion;
	}
	
	public void setFecha_alta_prescripcion(LocalDate fecha_alta_prescripcion) {
		this.fecha_alta_prescripcion = fecha_alta_prescripcion;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
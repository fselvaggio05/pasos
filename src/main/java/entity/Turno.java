package entity;

import java.time.LocalDate;
import java.util.Date;

public class Turno {
	
	private Integer id_turno;
	private Date fecha_t;
	private Date hora_t;	
	private LocalDate fecha_alta_t;
	private Date fecha_baja_t; //null en la generacion de agenda
	private String observacion; //null en la generacion de agenda
	private String estado_t;
	private Integer id_horario;
	private Integer id_consultorio; //null en la generacion de agenda
	private Integer dni; //null en la generacion de agenda
	public Integer getId_turno() {
		return id_turno;
	}
	public void setId_turno(Integer id_turno) {
		this.id_turno = id_turno;
	}
	public Date getFecha_t() {
		return fecha_t;
	}
	public void setFecha_t(Date fecha_t) {
		this.fecha_t = fecha_t;
	}
	public Date getHora_t() {
		return hora_t;
	}
	public void setHora_t(Date hora_t) {
		this.hora_t = hora_t;
	}
	public LocalDate getFecha_alta_t() {
		return fecha_alta_t;
	}
	public void setFecha_alta_t(LocalDate localDate) {
		this.fecha_alta_t = localDate;
	}
	public Date getFecha_baja_t() {
		return fecha_baja_t;
	}
	public void setFecha_baja_t(Date fecha_baja_t) {
		this.fecha_baja_t = fecha_baja_t;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEstado_t() {
		return estado_t;
	}
	public void setEstado_t(String estado_t) {
		this.estado_t = estado_t;
	}
	public Integer getId_horario() {
		return id_horario;
	}
	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}
	public Integer getId_consultorio() {
		return id_consultorio;
	}
	public void setId_consultorio(Integer id_consultorio) {
		this.id_consultorio = id_consultorio;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	
	
	
	
	

}

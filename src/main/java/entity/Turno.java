package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Turno {
	
	private Integer id_turno;
	private LocalDate fecha_t;
	private LocalTime hora_t;
	private LocalTime hora_hasta_t;	
	private LocalDate fecha_generacion;
	private LocalDate fecha_baja_t; 
	private String observacion; 
	private String estado_t;
	
	
	
	//CLASE HORARIO 
	private Horario horario;
	
	//CLASE CONSULTORIO 
	private Consultorio consultorio;
	
	//CLASE PACIENTE 
	private Paciente paciente;	
	
	//CLASE PRESCRIPCION, PUEDE NO EXISTIR SI EL TURNO ES PARTICULAR
	private Prescripcion prescripcion;
	
	
	
	
	
	
	public Prescripcion getPrescripcion() {
		return prescripcion;
	}

	public void setPrescripcion(Prescripcion prescripcion) {
		this.prescripcion = prescripcion;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


	public Integer getId_turno() {
		return id_turno;
	}
	public void setId_turno(Integer id_turno) {
		this.id_turno = id_turno;
	}
	public LocalDate getFecha_t() {
		return fecha_t;
	}
	public void setFecha_t(LocalDate fecha_t) {
		this.fecha_t = fecha_t;
	}
	public LocalTime getHora_t() {
		return hora_t;
	}
	public void setHora_t(LocalTime hora_desde) {
		this.hora_t = hora_desde;
	}
	public LocalDate getFecha_generacion() {
		return fecha_generacion;
	}
	public void setFecha_generacion(LocalDate localDate) {
		this.fecha_generacion = localDate;
	}
	public LocalDate getFecha_baja_t() {
		return fecha_baja_t;
	}
	public void setFecha_baja_t(LocalDate fecha_baja_t) {
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

	public LocalTime getHora_hasta_t() {
		return hora_hasta_t;
	}

	public void setHora_hasta_t(LocalTime hora_hasta_t) {
		this.hora_hasta_t = hora_hasta_t;
	}

	

}
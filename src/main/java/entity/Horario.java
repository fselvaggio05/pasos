package entity;

import java.time.LocalDate;
import java.time.LocalTime;

import entity.Enumeradores.DiaSemana;

public class Horario {
	
	
	private Integer id_horario;

	private LocalTime hora_desde;
	private LocalTime hora_hasta;
	private LocalDate fecha_baja;
	private LocalDate fecha_alta;
	private DiaSemana dia_semana;
	//private String dia_semana; //TODO: crear enum para dias de la semana
	private Practica practica;	
	private Profesional profesional;
	



	public Horario(Profesional prof, LocalTime hora_desde, LocalTime hora_hasta, LocalDate fecha_baja, LocalDate fecha_alta, DiaSemana dia_semana) {
		
		
		this.profesional = prof;
		this.hora_desde = hora_desde;
		this.hora_hasta = hora_hasta;
		this.fecha_baja = fecha_baja;
		this.fecha_alta = fecha_alta;
		this.dia_semana = dia_semana;
	}	
	
	public Horario() {
	
	}

	public LocalTime getHora_desde() {			
		return hora_desde;
	}
	
	public Practica getPractica() {
		return practica;
	}

	public void setPractica(Practica practica) {
		this.practica = practica;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}


	public void setHora_desde(LocalTime hora_desde) {
		this.hora_desde = hora_desde;
	}
	public LocalTime getHora_hasta() {
		return hora_hasta;
	}
	public void setHora_hasta(LocalTime hora_hasta) {
		this.hora_hasta = hora_hasta;
	}
	public LocalDate getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(LocalDate fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public LocalDate getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public String getDia_semana() {
		return dia_semana.name();
	}
	public void setDia_semana(int dia_semana) {
		DiaSemana dia = DiaSemana.fromDia(dia_semana);
		this.dia_semana = dia;
	}


	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}
}
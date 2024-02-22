package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {
	
	
	private Integer id_horario;
	private Integer matricula;
	private LocalTime hora_desde;
	private LocalTime hora_hasta;
	private LocalDate fecha_baja;
	private LocalDate fecha_alta;
	private String dia_semana; //TODO: crear enum para dias de la semana
	private String apellido_profesional;
	private Integer id_practica;
	private String desc_practica;
	



	public Horario(Integer matricula, LocalTime hora_desde, LocalTime hora_hasta, LocalDate fecha_baja, LocalDate fecha_alta,
			String dia_semana) {
		
		
		this.matricula = matricula;
		this.hora_desde = hora_desde;
		this.hora_hasta = hora_hasta;
		this.fecha_baja = fecha_baja;
		this.fecha_alta = fecha_alta;
		this.dia_semana = dia_semana;
	}
	
	
	
	public Horario() {
	
	}



//	public Horario(Integer matricula2, String dia_semana2, Date hora_desde2, Date hora_hasta2) {
//	
//		this.matricula = matricula2;
//		this.dia_semana = dia_semana2;
//		this.hora_desde = hora_desde2;
//		this.hora_hasta = hora_hasta2;
//	}



	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public LocalTime getHora_desde() {		
		
		return hora_desde;
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
		return dia_semana;
	}
	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public String getApellido_profesional() {
		return apellido_profesional;
	}

	public void setApellido_profesional(String apellido_profesional) {

		this.apellido_profesional = apellido_profesional;
	}


	public Integer getId_practica() {
		return id_practica;
	}


	public void setId_practica(Integer id_practica) {
		this.id_practica = id_practica;
	}



	public String getDesc_practica() {
		return desc_practica;
	}



	public void setDesc_practica(String desc_practica) {
		this.desc_practica = desc_practica;
	}



	public Integer getId_horario() {
		return id_horario;
	}



	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	
	
	
	
	
	
	

}

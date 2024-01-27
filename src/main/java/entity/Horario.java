package entity;

import java.util.Date;

public class Horario {
	
	
	private Integer id_horario;
	private Integer matricula;
	private Date hora_desde;
	private Date hora_hasta;
	private Date fecha_baja;
	private Date fecha_alta;
	private String dia_semana; //TODO: crear enum para dias de la semana
	private String apellido_profesional;
	private Integer id_practica;
	private String desc_practica;
	
	
	
	



	public Horario(Integer matricula, Date hora_desde, Date hora_hasta, Date fecha_baja, Date fecha_alta,
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
	public Date getHora_desde() {
		return hora_desde;
	}
	public void setHora_desde(Date hora_desde) {
		this.hora_desde = hora_desde;
	}
	public Date getHora_hasta() {
		return hora_hasta;
	}
	public void setHora_hasta(Date hora_hasta) {
		this.hora_hasta = hora_hasta;
	}
	public Date getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
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

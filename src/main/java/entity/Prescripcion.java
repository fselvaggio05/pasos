package entity;

import java.time.LocalDate;


//Clase correspondiente a la prescripcion ambulatoria
public class Prescripcion {
	
	
	private Integer id_prescripcion;
	private LocalDate fecha_prescripcion;
	private String nro_afiliado;
	private Integer cod_practica;
	private Integer cant_sesiones;
	private Integer sesiones_asistidas;
	private LocalDate fecha_alta_prescripcion;
	private Integer estado;
	private Paciente Paciente;
	private ObraSocial ObraSocial;

	
		
	public Prescripcion() {
		
	}


	public Prescripcion(LocalDate fecha_prescripcion, String nro_afiliado, Integer cod_practica, Integer cant_sesiones,
			Integer sesiones_asistidas, LocalDate fecha_alta_prescripcion, Integer estado) {
		
		this.fecha_prescripcion = fecha_prescripcion;
		this.nro_afiliado = nro_afiliado;
		this.cod_practica = cod_practica;
		this.cant_sesiones = cant_sesiones;
		this.sesiones_asistidas = sesiones_asistidas;
		this.fecha_alta_prescripcion = fecha_alta_prescripcion;
		this.estado = estado;
	}
	
	
	
	public Integer getId_Prescripcion() {
		return id_prescripcion;
	}


	public void setId_Prescripcion(Integer id_ambulatoria) {
		this.id_prescripcion = id_ambulatoria;
	}


	public LocalDate getFecha_prescripcion() {
		return fecha_prescripcion;
	}
	public void setFecha_prescripcion(LocalDate fecha_prescripcion) {
		this.fecha_prescripcion = fecha_prescripcion;
	}
	public String getNro_afiliado() {
		return nro_afiliado;
	}
	public void setNro_afiliado(String nro_afiliado) {
		this.nro_afiliado = nro_afiliado;
	}
	public Integer getCod_practica() {
		return cod_practica;
	}
	public void setCod_practica(Integer cod_practica) {
		this.cod_practica = cod_practica;
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
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}


	public void setPaciente(Paciente pc) {
		this.Paciente=pc;
		
	}


	public void setObraSocial(ObraSocial os) {
		this.setObraSocial(os);
		
	}


	public ObraSocial getObraSocial() {
		
		return ObraSocial;
	}


	public Paciente getPaciente() {
		// 
		return Paciente;
	}
	
	

	

}

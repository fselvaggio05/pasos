package entity;

public class Practica {
	
	private Integer id_practica;
	private String descripcion;
	private Integer id_equipo;
	
	public Practica () {
	}
	
	
	public Practica(Integer id_practica, String descripcion, Integer id_equipo) {
	
		this.id_practica = id_practica;
		this.descripcion = descripcion;
		this.id_equipo = id_equipo;
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
	public Integer getId_equipo() {
		return id_equipo;
	}
	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
	}
	
	
}

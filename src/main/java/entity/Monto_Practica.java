package entity;

import java.time.LocalDate;

public class Monto_Practica {
	
	private int id_monto;
	private LocalDate fecha_desde;
	public int getId_monto() {
		return id_monto;
	}
	public void setId_monto(int id_monto) {
		this.id_monto = id_monto;
	}
	public LocalDate getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(LocalDate fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public LocalDate getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(LocalDate fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	private LocalDate fecha_hasta;
	private Double  monto;

}

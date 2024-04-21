package service;

import java.time.LocalDate;
import java.util.List;

import entity.MontosPractica;
import entity.Practica;
import repository.MontosPracticaRepository;

public class MontosPracticaService {
	
	private MontosPracticaRepository mpr;

	public MontosPracticaService() {

		this.mpr = new MontosPracticaRepository();
	}

	public List<MontosPractica> getMontosPractica(Practica pr) {
		return mpr.getMontosPractica(pr);
	}

	public String insertarMontoPractica(Integer id_practica,LocalDate fecha_desde,LocalDate fecha_hasta, Double monto) {
		return mpr.insertarMontoPractica(id_practica,fecha_desde,fecha_hasta,monto);
	}

	public String actualizarMonto(Integer idMonto, Double monto) {
		return mpr.actualizarMonto(idMonto,monto);
		
	}

	public boolean validarSuperposicion(Integer id_practica, LocalDate fecha_desde, LocalDate fecha_hasta) {
		return mpr.validarSuperposicion(id_practica,fecha_desde,fecha_hasta);
	}
}

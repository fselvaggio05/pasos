package service;

import java.util.List;

import entity.Consultorio;
import repository.ConsultorioRepository;

public class ConsultorioService {
	protected ConsultorioRepository consRep;
	String respuestaOperacion;
	
	public ConsultorioService()
	{
		this.consRep = new ConsultorioRepository();
	}
	
	public List<Consultorio> getAll()
	{
		return consRep.getAll();
	}
	
	public List<Consultorio> getAllActivos()
	{
		return consRep.getAllActivos();
	}

	public List<Consultorio> getAllInactivos() {			
		return consRep.getAllInactivos();
	}
	
	public String insertarConsultorio(String descConsultorio) {	
		return respuestaOperacion = consRep.insertarConsultorio(descConsultorio);
	}
	
	public String actualizarConsultorio(Integer idConsultorio, String descConsultorio) {
		// TODO Auto-generated method stub	
		return respuestaOperacion = consRep.actualizarConsultorio(idConsultorio, descConsultorio);		
	}

	public String eliminarConsultorio(Integer idConsultorio) {
		// TODO Auto-generated method stub
		return respuestaOperacion = consRep.eliminarConsultorio(idConsultorio);
	}
	
	public String revivirConsultorio(Integer idConsultorio) {
		// TODO Auto-generated method stub
		return respuestaOperacion = consRep.revivirConsultorio(idConsultorio);
	}
}
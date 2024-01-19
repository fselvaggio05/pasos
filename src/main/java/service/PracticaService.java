package service;

import java.util.List;

import entity.Practica;
import repository.PracticaRepository;

public class PracticaService {
	
	protected PracticaRepository prRep;
	String respuestaOperacion;
	
	public PracticaService()
	{
		this.prRep = new PracticaRepository();
	}
	
	public List<Practica> getAllActivas()
	{
		return prRep.getAllActivas();
	}

	public String insertarPractica(Integer idPractica, String descPractica, Integer idEquipo) {
		
		return respuestaOperacion = prRep.insertarPractica(idPractica,descPractica,idEquipo);
		
		
		
	}
	
	public String actualizarPractica(Integer idPractica, String descPractica, Integer idEquipo) {
		// TODO Auto-generated method stub
		
		return respuestaOperacion = prRep.actualizarPractica(idPractica, descPractica, idEquipo);
		
	}


	public String eliminarPractica(Integer idPractica, Integer estado) {
		// TODO Auto-generated method stub
		return respuestaOperacion = prRep.eliminarPractica(idPractica, estado);
		
	}

	public List<Practica> getAllInactivas() {
			
		return prRep.getAllInactivas();
	}

	public String habilitarPractica(Integer idPractica) {
		
		
		// TODO Auto-generated method stub
		return respuestaOperacion = prRep.habilitarPractica(idPractica);
	}

	
	
	

}

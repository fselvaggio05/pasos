package service;

import java.util.List;

import entity.Equipo;
import repository.EquipoRepository;

public class EquipoService {
	protected EquipoRepository eqRep;
	String respuestaOperacion;
	
	public EquipoService()
	{
		this.eqRep = new EquipoRepository();
	}
	
	public List<Equipo> getAll()
	{
		return eqRep.getAll();
	}
	
	public List<Equipo> getAllActivos()
	{
		return eqRep.getAllActivos();
	}

	public String insertarEquipo(String tipoEquipo, String descEquipo) {	
		return respuestaOperacion = eqRep.insertarEquipo(tipoEquipo, descEquipo);
	}
	
	public String actualizarEquipo(Integer idEquipo, String tipoEquipo, String descEquipo) {
		// TODO Auto-generated method stub	
		return respuestaOperacion = eqRep.actualizarEquipo(idEquipo, tipoEquipo, descEquipo);		
	}

	public String eliminarEquipo(Integer idEquipo) {
		// TODO Auto-generated method stub
		return respuestaOperacion = eqRep.eliminarEquipo(idEquipo);
	}
	
	public String revivirEquipo(Integer idEquipo) {
		// TODO Auto-generated method stub
		return respuestaOperacion = eqRep.revivirEquipo(idEquipo);
	}


	public List<Equipo> getAllInactivos() {			
		return eqRep.getAllInactivos();
	}
}
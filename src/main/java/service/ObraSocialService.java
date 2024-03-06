package service;

import java.util.List;
import entity.ObraSocial;
import repository.ObraSocialRepository;

public class ObraSocialService {
	protected ObraSocialRepository osRep;
	String respuestaOperacion;
	
	public ObraSocialService()
	{
		this.osRep = new ObraSocialRepository();
	}
	
	public List<ObraSocial> getAll()
	{
		return osRep.getAll();
	}
	
	public List<ObraSocial> getAllActivas()
	{
		return osRep.getAllActivas();
	}

	public List<ObraSocial> getAllInactivas() {			
		return osRep.getAllInactivas();
	}
	
	public String insertarObraSocial(int id_obra_social, String nombre) {	
		return respuestaOperacion = osRep.insertarObraSocial(id_obra_social,nombre);
	}
	
	public String actualizarObraSocial(Integer id_obra_social, String nombre) {
		// TODO Auto-generated method stub	
		return respuestaOperacion = osRep.actualizarObraSocial(id_obra_social,nombre);		
	}

	public String eliminarObraSocial(Integer id_obra_social) {
		// TODO Auto-generated method stub
		return respuestaOperacion = osRep.eliminarObraSocial(id_obra_social);
	}
	
	public String revivirObraSocial(Integer id_obra_social) {
		// TODO Auto-generated method stub
		return respuestaOperacion = osRep.revivirObraSocial(id_obra_social);
	}

	public ObraSocial getObraSocial(Integer id_obra_social) {
		// TODO Auto-generated method stub
		return this.osRep.getObraSocial(id_obra_social);
	}
}
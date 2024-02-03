package service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	public String insertarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) {
		
		return respuestaOperacion = prRep.insertarPractica(idPractica,descPractica,duracion,idEquipo);
		
		
		
	}
	
	public String actualizarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) {
		// TODO Auto-generated method stub
		
		return respuestaOperacion = prRep.actualizarPractica(idPractica, descPractica, duracion, idEquipo);
		
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

	public Integer getDuracionPractica(Integer id_practica) {
			
		return 	prRep.getDuracionPractica(id_practica);
	}

	public List<Practica> getPracticasPorProf(Integer matricula) {
		
		List<Practica> practicasProfesional = prRep.getPracticasPorProf(matricula);
		return practicasProfesional;
	}

	

	
	
	
	

}

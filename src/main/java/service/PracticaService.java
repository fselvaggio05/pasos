package service;

import java.util.List;

import entity.Practica;
import repository.PracticaRepository;

public class PracticaService {
	
	protected PracticaRepository prRep;
	
	public PracticaService()
	{
		this.prRep = new PracticaRepository();
	}
	
	public List<Practica> getAll()
	{
		return prRep.getAll();
	}

	public void insertarPractica(String descPractica, Integer idEquipo) {
		prRep.insertarPractica(descPractica,idEquipo);
		
		
	}
	
	

}

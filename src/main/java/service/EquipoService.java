package service;

import java.util.List;

import entity.Equipo;
import repository.EquipoRepository;

public class EquipoService {
	
	protected EquipoRepository eqRep;
	
	public EquipoService()
	{
		this.eqRep = new EquipoRepository();
	}

	public List<Equipo> getAll() {
		
		return eqRep.getAll();
		
	
	}
	
	
	
	
	
	

}

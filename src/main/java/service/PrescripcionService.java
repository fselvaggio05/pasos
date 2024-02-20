package service;

import entity.Prescripcion;
import repository.PrescripcionRepository;

public class PrescripcionService {
	
	private PrescripcionRepository prescRep;
	
	public PrescripcionService()
	{
		this.prescRep = new PrescripcionRepository();
	}
	
	
	public String insertarPrescripcion(Prescripcion pr)
	{
				
		return prescRep.insertarPrescripcion(pr);
		
	}

}

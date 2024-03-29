package service;

import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import repository.PrescripcionRepository;

public class PrescripcionService {
	
	private PrescripcionRepository prescRep;
	
	public PrescripcionService()
	{
		this.prescRep = new PrescripcionRepository();
	}
	
	
	public Integer insertarPrescripcion(Prescripcion pr)
	{
				
		return prescRep.insertarPrescripcion(pr);
		
	}


	public Prescripcion buscarPrescripcionesPaciente(Paciente pac, Prescripcion pr) {
		
			
		return prescRep.buscarPrescrionesPaciente(pac, pr);
		 
	}


	public Prescripcion buscarPrescripcionActiva(Paciente pac, Practica pr) {
		
		return prescRep.buscarPrescripcionActiva(pac,pr);
	}


	public void incrementarSesionesAsistidas(Prescripcion prescripcion) {
		
		prescRep.incrementarSesionesAsistidas(prescripcion);
		
	}


	public void desactivarVigenciaPrescripcion(Prescripcion prescripcion) {
		
		prescRep.desactivarVigenciaPrescripcion(prescripcion);
		
	}


	

}

package service;

import java.time.LocalDate;
import java.util.List;

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
	
	
	public String insertarPrescripcion(Paciente paciente,LocalDate fechaPrescripcion,Integer id_practica,Integer cantSesiones)
	{
				
		return prescRep.insertarPrescripcion(paciente,fechaPrescripcion,id_practica,cantSesiones);
		
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

	public List<Prescripcion> getAll() {
		return prescRep.getAll();
	}

	public List<Prescripcion> getAllPaciente(Paciente pac) {
		return prescRep.getAllPaciente(pac);
	}


	public String anularPrescripcion(Integer idPrescripcion) {
		return prescRep.anularPrescripcion(idPrescripcion);
	}
	
	public List<Prescripcion> buscarTodasLasPrescripciones(Paciente pac)
	{
		return prescRep.buscarTodasLasPrescripciones(pac);
	}


	public Prescripcion getPrescripcion(Integer idPrescripcion) {
		return prescRep.getPrescripcion(idPrescripcion);
	}
}

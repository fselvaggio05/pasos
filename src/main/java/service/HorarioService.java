package service;

import java.util.List;

import entity.Horario;
import repository.HorarioRepository;

public class HorarioService {
	
	protected HorarioRepository horRep;
	
	public HorarioService()
	{
		this.horRep = new HorarioRepository();
	}
	
	

	public List<Horario> getAllActivos() {
		
		List<Horario> horarios = horRep.getAllActivos();		
		return horarios;
	}
	
	
	public List<Horario> getHorariosProfesional(Integer matricula)
	{
		List<Horario> horariosProf = horRep.getHorariosProfesional(matricula);		
		return horariosProf;
	}

}

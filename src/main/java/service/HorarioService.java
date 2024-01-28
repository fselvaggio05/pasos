package service;

import java.util.Date;
import java.util.List;

import conexionDB.FactoryConnection;
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
	
	public List<Horario> getAllInactivos() {
		List<Horario> horarios = horRep.getAllInactivos();		
		return horarios;
	}

	
	
	public List<Horario> getHorariosActivosProfesional(Integer matricula)
	{
		List<Horario> horariosProf = horRep.getHorariosActivosProfesional(matricula);		
		return horariosProf;
	}
	
	
	public List<Horario> getHorariosInactivosProfesional(Integer matricula)
	{
		List<Horario> horariosProf = horRep.getHorariosInactivosProfesional(matricula);		
		return horariosProf;
	}



	public String insertarHorario(Horario hr) {
		
		return horRep.insertarHorario(hr);
		
	}



	public String activarHorario(Integer idHorario) {
		
		return horRep.activarHorario(idHorario);
		
	}



	public String inactivarHorario(Integer idHorario) {
		
		return horRep.inactivarHorario(idHorario);
		
		
	}



	public String actualizarHorario(Horario hr) {
		
		return horRep.actualizarHorario(hr);
	}



	







	



	
}

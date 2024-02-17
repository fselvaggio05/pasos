package service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import entity.Horario;
import repository.HorarioRepository;

public class HorarioService {
	
	private HorarioRepository horRep;
	private PracticaService prServ;
	private ConsultorioService conServ;
	
	public HorarioService() {

		this.horRep = new HorarioRepository();
		this.prServ = new PracticaService();
		this.conServ = new ConsultorioService();
	}

	public List<Horario> getAllActivos() {

		List<Horario> horarios = horRep.getAllActivos();
		return horarios;
	}

	public List<Horario> getAllInactivos() {
		List<Horario> horarios = horRep.getAllInactivos();
		return horarios;
	}

	public List<Horario> getHorariosActivosProfesional(Integer matricula) {
		List<Horario> horariosProf = horRep.getHorariosActivosProfesional(matricula);
		return horariosProf;
	}

	public List<Horario> getHorariosInactivosProfesional(Integer matricula) {
		List<Horario> horariosProf = horRep.getHorariosInactivosProfesional(matricula);
		return horariosProf;
	}

	public String insertarHorario(Horario hr) {
		return horRep.insertarHorario(hr);
	}

	public String revivirHorario(Integer idHorario) {
		return horRep.revivirHorario(idHorario);
	}

	public String inactivarHorario(Integer idHorario) {
		return horRep.inactivarHorario(idHorario);
	}

	public Boolean calculaHorario(Horario hr) {
		Integer duracionPractica;
		duracionPractica = prServ.getDuracionPractica(hr.getId_practica());
		long minutosDiferencia = Duration.between(hr.getHora_desde(), hr.getHora_hasta()).toMinutes();
		
		if (minutosDiferencia % duracionPractica == 0) {
			return true;
		} else {
			return false;
		}
	}


	public boolean validaConsulorio(Horario hr) {
				
		Integer cantConsultorio = conServ.getAllActivos().size();		
		Integer cantHorarios = this.obtenerHorariosCreados(hr);
		
		if(cantConsultorio>cantHorarios)
		{
			return true;
		}
		
		else 
		{
			return false;
		}
		
		
		
		
	}



	public Integer obtenerHorariosCreados(Horario hr) {
		
		Integer cantHorarios = horRep.obtenerHorariosCreados(hr);
		
		return cantHorarios;
	}

}

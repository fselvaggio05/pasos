package service;

import java.time.Duration;
import java.util.List;
import entity.Horario;
import entity.Profesional;
import repository.HorarioRepository;
import repository.TurnoRepository;

public class HorarioService {
	
	private HorarioRepository horRep;
	private PracticaService prServ;
	private ConsultorioService conServ;
	private TurnoRepository turRep;
	
	public HorarioService() {

		this.horRep = new HorarioRepository();
		this.prServ = new PracticaService();
		this.conServ = new ConsultorioService();
		this.turRep = new TurnoRepository();
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
		duracionPractica = prServ.getDuracionPractica(hr.getPractica());
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

	public List<Profesional> getProfesionales(Integer id_practica) {
		
		return horRep.getProfesionales(id_practica);
		
		
	}

	public boolean validarHorario(Integer idHorario) {
		Integer turnosPendientes = turRep.validarHorario(idHorario);
		if(turnosPendientes>0) {
			return false;
		}
		else {
			return true;
		}
	}

}

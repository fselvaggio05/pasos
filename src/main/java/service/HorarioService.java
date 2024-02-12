package service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import entity.Horario;
import repository.HorarioRepository;

public class HorarioService {

	protected HorarioRepository horRep;
	protected PracticaService prServ;

	public HorarioService() {
		this.horRep = new HorarioRepository();
		this.prServ = new PracticaService();
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

	public Boolean calculaHorario(Integer id_practica, LocalTime desde, LocalTime hasta) {
		Integer duracionPractica;
		duracionPractica = prServ.getDuracionPractica(id_practica);
		long minutosDiferencia = Duration.between(desde, hasta).toMinutes();
		if (minutosDiferencia % duracionPractica == 0) {
			return true;
		} else {
			return false;
		}
	}
}

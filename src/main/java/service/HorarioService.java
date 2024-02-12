package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import conexionDB.FactoryConnection;
import entity.Horario;
import repository.HorarioRepository;
import repository.PracticaRepository;

public class HorarioService {
	
	private HorarioRepository horRep;
	private PracticaService prServ;
	private ConsultorioService conServ;
	
	public HorarioService()
	{
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



	public Boolean calculaHorario(Integer id_practica, LocalTime desde, LocalTime hasta) {
	
			Integer duracionPractica;
			
			duracionPractica = prServ.getDuracionPractica(id_practica);
//			
			//TODO: REVEER ESTE METODO. CUANDO MODIFIQUE LOS TIPOS, SE ME DESCAJETO
//			long dsd = desde.getHour();
//			hasta.minusHours(dsd);
//			Long diferenciaHora = hasta.getTime()- desde.getTime();
//			Long minutosDiferencia = TimeUnit.MILLISECONDS.toMinutes(diferenciaHora); 
////			
//			if(minutosDiferencia%duracionPractica == 0)
//			{
//				return true;
//			}
//			
//			else
//			{
//				return false;
//			}
			return null;
			
			

		
	}



	public boolean validaConsulorio(Horario hr) {
		
		
		Integer cantConsultorio = conServ.getAllActivos().size();
		
		Integer cantHorarios = this.obtenerHorariosCreados(hr);
		
		
		return false;
		
		
		
	}



	private Integer obtenerHorariosCreados(Horario hr) {
		
		Integer cantHorarios = this.horRep.obtenerHorariosCreados(hr);
		
		return cantHorarios;
	}









	



	
}

package service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.google.protobuf.Duration;

import entity.Horario;
import entity.Turno;
import repository.TurnoRepository;

public class TurnoService {
	
	private TurnoRepository turRep;
	private PracticaService prServ;
	
	public TurnoService()
	{
		this.turRep = new TurnoRepository();
		this.prServ = new PracticaService();
	}

	public String abrirAgenda(List<Horario> horarios) {
		
		Integer duracionPractica;
		Date hora_desde;
		List<Turno> turnos;
		
		
		for (Horario h : horarios)
		{
			duracionPractica = prServ.getDuracionPractica(h.getId_practica());
			
			hora_desde = h.getHora_desde();
			
			while(hora_desde <= h.getHora_hasta())
			{
				Turno tur = new Turno();
				tur.setFecha_alta_t(LocalDate.now());
				tur.setEstado_t("Libre");
				tur.setFecha_t(dia_turno); //ver como tomar los dias para ir generando para cada dia 
				tur.setHora_t(hora_desde);
				tur.setId_horario(h.getId_horario());
				turnos.add(tur);				
				hora_desde = hora_desde + duracionPractica;
				
			}
			
			
		}
		
		turRep.abrirAgenda(turnos);
		
		return "mensaje";
		
		
		
	}

}

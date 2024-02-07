package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.protobuf.Duration;

import entity.Horario;
import entity.Turno;
import repository.TurnoRepository;

public class TurnoService {
	
	private TurnoRepository turRep;
	private PracticaService prServ;
	private LocalDate fecha_turno;
	
	public TurnoService()
	{
		this.turRep = new TurnoRepository();
		this.prServ = new PracticaService();
	}

public String abrirAgenda(List<Horario> horarios) {
		
		Integer duracionPractica;
		LocalTime hora_desde;
		LocalDate ultima_fecha_generacion;	
		LocalDate fecha_turno_desde;
		LocalDate fecha_turno_hasta;
		
		
		List<Turno> turnos = new ArrayList<Turno>();
		
		for (Horario h : horarios)
		{
			duracionPractica = prServ.getDuracionPractica(h.getId_practica());
			ultima_fecha_generacion = this.getUltimaFechaGeneracion();
			fecha_turno_hasta = LocalDate.now().plusDays(14);
			
			
			fecha_turno_desde = ultima_fecha_generacion.plusDays(15);
			
			
			
			while(fecha_turno_desde.compareTo(fecha_turno_hasta)<0)
			{
				hora_desde = h.getHora_desde();
				String diaTurno =fecha_turno_desde.format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha 
							
				if(diaTurno.equals(h.getDia_semana()))
				{
				
					while(hora_desde.compareTo(h.getHora_hasta())<0)
					{
						Turno tur = new Turno();
						tur.setFecha_generacion(LocalDate.now());
						
						ultima_fecha_generacion = this.getUltimaFechaGeneracion();
						
						tur.setFecha_t(fecha_turno_desde); 
						tur.setHora_t(hora_desde);
						tur.setEstado_t("Libre");
						tur.setId_horario(h.getId_horario());
						turnos.add(tur);				
						hora_desde= hora_desde.plusMinutes(duracionPractica);
						System.out.println(h.getDia_semana());
						System.out.println(diaTurno);
						
					}
					
				}
				
					fecha_turno_desde = fecha_turno_desde.plusDays(1);
			}
			
		}
		
		turRep.abrirAgenda(turnos);
		
		return "mensaje";
		
		
		
	}
	
	
	public LocalDate getUltimaFechaGeneracion()
	{
		return turRep.getUltimaFechaGeneracion();
		
		
	}

}

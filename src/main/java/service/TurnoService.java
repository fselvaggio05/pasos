package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;


import entity.Horario;
import entity.Turno;
import repository.TurnoRepository;

public class TurnoService {
	
	private TurnoRepository turRep;
	private PracticaService prServ;
	private LocalDate fecha_turno;
	private String respuesta;
	
	public TurnoService()
	{
		this.turRep = new TurnoRepository();
		this.prServ = new PracticaService();
	}

public String abrirAgenda(List<Horario> horarios) {
		
		Integer duracionPractica;
		LocalTime hora_desde;
		List<Turno> turnos = new ArrayList<Turno>();
		
		List<LocalDate> diasGeneracion = this.obtenerDiasGeneracionAgenda();
		
		if(diasGeneracion.size()!=0)
		{
			for (int i=0; i<diasGeneracion.size();i++)
			{
							
				for(Horario h : horarios)
				{
					String nombreDia = diasGeneracion.get(i).format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha
					
					if(nombreDia.equals(h.getDia_semana()))
					{
						duracionPractica = prServ.getDuracionPractica(h.getId_practica());
						hora_desde = h.getHora_desde();
						
						while(hora_desde.compareTo(h.getHora_hasta())<0)
							{
								Turno tur = new Turno();
								tur.setFecha_generacion(LocalDate.now());					
								tur.setFecha_t(diasGeneracion.get(i)); 
								tur.setHora_t(hora_desde);
								tur.setEstado_t("Libre");
								tur.setId_horario(h.getId_horario());
								turnos.add(tur);				
								hora_desde= hora_desde.plusMinutes(duracionPractica);							
							}
					}
				}
				
				
			}
			
			respuesta = turRep.abrirAgenda(turnos);
			
			
		}
		
		else
		{
			respuesta= null;
		}
		
		
		
		
		return respuesta;
		
	}
	
	
public LocalDate getUltimaFechaGeneracion()
	{
		return turRep.getUltimaFechaGeneracion();
		
		
	}


public List<LocalDate> obtenerDiasGeneracionAgenda()
{
	List<LocalDate> feriados = new ArrayList<LocalDate>();
	List<LocalDate> feriadosMes = new ArrayList<LocalDate>();
	List<LocalDate> diasGeneracionAgenda = new ArrayList<LocalDate>();
	List<LocalDate> diasHabiles = new ArrayList<LocalDate>();
	LocalDate ultima_fecha_generacion = null;
	LocalDate fecha_turno_desde;
	LocalDate fecha_turno_hasta=null;
	
		
	feriados = turRep.getFeriados();
	
	
//  GENERO UNA SUBLISTA DE LOS FERIADOS DEL MES 

	for(LocalDate f : feriados)
	{
		if(f.getMonth().equals(LocalDate.now().getMonth()))
		{
			feriadosMes.add(f);
		}
	}
	
	
	ultima_fecha_generacion = this.getUltimaFechaGeneracion();
	
	if(ultima_fecha_generacion==null)
	{
		fecha_turno_desde = LocalDate.now();
				
	}
	
	else
	{
		fecha_turno_desde = ultima_fecha_generacion.plusDays(15);
		
	}
	
	
	fecha_turno_hasta = LocalDate.now().plusDays(14);

	

	while(fecha_turno_desde.compareTo(fecha_turno_hasta)<=0)
	{
		diasGeneracionAgenda.add(fecha_turno_desde);
		fecha_turno_desde = fecha_turno_desde.plusDays(1);
	}
	
	
	for(LocalDate diaHabil : diasGeneracionAgenda)
	{
		String nombreDia = diaHabil.format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); 
		System.out.println(nombreDia);
				
		if((!feriadosMes.contains(diaHabil)) && (!nombreDia.equals("domingo"))) 
		{
			diasHabiles.add(diaHabil);
		}
	}
	
	return diasHabiles;
	
}

public List<Turno> buscarTurnosPendientes(Integer dni) {
	
	List<Turno> turnos = new ArrayList<Turno>();
	turnos = turRep.buscarTurnosPendientes(dni);
	
	return turnos;
	
	
}

public List<Horario> obtenerSeleccionados(String[] seleccionados, List<Horario> horarios) {
	
	List<Horario> horariosSeleccionados = new ArrayList<Horario>();
	Integer idHorario = null;
	
	for(int i=0; i<seleccionados.length ;i++)
	{
		idHorario = Integer.parseInt(seleccionados[i]);
		
		for(Horario h : horarios)
		{
			if(idHorario == h.getId_horario())
			{
				horariosSeleccionados.add(h);
				break;
			}
			
		}
		
	}
	
	
	return horariosSeleccionados;
}



}

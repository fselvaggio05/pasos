package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

import entity.Feriado;
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
//		LocalDate ultima_fecha_generacion;	
//		LocalDate fecha_turno_desde;
//		LocalDate fecha_turno_hasta;
		List<Turno> turnos = new ArrayList<Turno>();
		
		List<LocalDate> diasGeneracion = this.obtenerDiasGeneracionAgenda();
		
		
		
//		List<Feriado> feriados = new ArrayList<Feriado>();
//		List<Feriado> feriadosMes = new ArrayList<Feriado>();
//		feriados = this.getFeriados();
//		
//		
////		GENERO UNA SUBLISTA DE LOS FERIADOS DEL MES 
//		
//		for(Feriado f : feriados)
//		{
//			if(f.getFecha_feriado().getMonth().equals(LocalDate.now().getMonth()))
//			{
//				feriadosMes.add(f);
//			}
//		}
//		
//		for (Feriado f : feriadosMes)
//		{
//			System.out.println(f.getFecha_feriado());
//			System.out.println(f.getDescripcion());
//		}
//		
		
		//METODO PARA GENERAR UN ARRAY UNICAMENTE CON LOS DIAS QUE SE VAN A GENERAR AGENDA 
		
		
		
		for (int i=0; i<diasGeneracion.size();i++)
		{
						
			for(Horario h : horarios)
			{
				String nombreDia =diasGeneracion.get(i).format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha
				
				if(nombreDia.equals(h.getDia_semana()))
				{
					duracionPractica = prServ.getDuracionPractica(h.getId_practica());
					hora_desde = h.getHora_desde();
					
					while(hora_desde.compareTo(h.getHora_hasta())<0)
						{
							Turno tur = new Turno();
							tur.setFecha_generacion(LocalDate.now());						
//								ultima_fecha_generacion = this.getUltimaFechaGeneracion();
							
							tur.setFecha_t(diasGeneracion.get(i)); 
							tur.setHora_t(hora_desde);
							tur.setEstado_t("Libre");
							tur.setId_horario(h.getId_horario());
							turnos.add(tur);				
							hora_desde= hora_desde.plusMinutes(duracionPractica);
//								System.out.println(h.getDia_semana());
//								System.out.println(diaTurno);
							
						}
				}
			}
			
			
		}
		
		respuesta = turRep.abrirAgenda(turnos);
		
		return respuesta;
		
		
		
		
//		METODO ANTERIOR A LA REFORMA 		
//				for (Horario h : horarios)
//				{
//					duracionPractica = prServ.getDuracionPractica(h.getId_practica());
//		//			ultima_fecha_generacion = this.getUltimaFechaGeneracion();
//		//			fecha_turno_hasta = LocalDate.now().plusDays(14);			
//		//			fecha_turno_desde = ultima_fecha_generacion.plusDays(15);
//					
//		
//					
//								
//		//					String diaTurno =fecha_turno_desde.format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha
//		//					
//		//					if(diaTurno.equals(h.getDia_semana()))
//		//					{						
//							
//						while(fecha_turno_desde.compareTo(fecha_turno_hasta)<0)
//						{
//		//					
//		//					
//		//					for (Feriado f : feriadosMes)
//		//					{
//		//						if((f.getFecha_feriado().equals(fecha_turno_desde)))
//		//						{
//								
//								hora_desde = h.getHora_desde();
//								 
//								String diaTurno =fecha_turno_desde.format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha
//											
//								if(diaTurno.equals(h.getDia_semana()))
//								{
//								
//									while(hora_desde.compareTo(h.getHora_hasta())<0)
//									{
//										Turno tur = new Turno();
//										tur.setFecha_generacion(LocalDate.now());						
//			//								ultima_fecha_generacion = this.getUltimaFechaGeneracion();
//										
//										tur.setFecha_t(fecha_turno_desde); 
//										tur.setHora_t(hora_desde);
//										tur.setEstado_t("Libre");
//										tur.setId_horario(h.getId_horario());
//										turnos.add(tur);				
//										hora_desde= hora_desde.plusMinutes(duracionPractica);
//			//								System.out.println(h.getDia_semana());
//			//								System.out.println(diaTurno);
//										
//									}
//									
//								}
//								
//						fecha_turno_desde = fecha_turno_desde.plusDays(1);
//						}
//					
//							
		
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
	
	for (LocalDate f : feriadosMes)
	{
		System.out.println(f);
		
	}
	
	ultima_fecha_generacion = this.getUltimaFechaGeneracion();
	fecha_turno_desde = ultima_fecha_generacion.plusDays(15);
	fecha_turno_hasta = LocalDate.now().plusDays(14);
	

	
	while(fecha_turno_desde.compareTo(fecha_turno_hasta)<0)
	{
		diasGeneracionAgenda.add(fecha_turno_desde);
		fecha_turno_desde = fecha_turno_desde.plusDays(1);
	}
	
	
	for(LocalDate diaHabil : diasGeneracionAgenda)
	{
		if(!feriadosMes.contains(diaHabil))
		{
			diasHabiles.add(diaHabil);
		}
	}
	
	return diasHabiles;
	
}



}

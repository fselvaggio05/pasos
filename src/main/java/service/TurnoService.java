package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;

import entity.Consultorio;
import entity.Horario;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Turno;
import repository.TurnoRepository;

public class TurnoService {
	
	private TurnoRepository turRep;
	private PracticaService prServ;
	private ConsultorioService conServ;
	private HorarioService horServ;
	private PrescripcionService prescServ;
	private String respuesta;
	
	public TurnoService()
	{
		this.turRep = new TurnoRepository();
		this.prServ = new PracticaService();
		this.conServ = new ConsultorioService();
		this.horServ = new HorarioService();
		this.prescServ = new PrescripcionService();
	}

public String abrirAgenda(List<Horario> horarios) {
		
		Integer duracionPractica;
		LocalTime hora_desde;
		LocalTime hora_desde_consultorio;
		Integer cantConsultorios = null;
		Boolean asignado = null;
		LocalTime hora_hasta_turno = null;
		
		List<Turno> turnos = new ArrayList<Turno>();	
		List<LocalDate> diasGeneracion = this.obtenerDiasGeneracionAgenda();
		
	
		
		if(diasGeneracion.size()!=0)
		{
			for (int i=0; i<diasGeneracion.size();i++)
			{
				String nombreDia = diasGeneracion.get(i).format(DateTimeFormatter.ofPattern("EEEE",Locale.getDefault())); //EEEE, dd MMMM, yyyy formato completo fecha
//				hora_desde_consultorio = null;
				
				
				  for(Horario h : horarios)
				  {			
//						if(hora_desde_consultorio == null)
//						{
//							hora_desde_consultorio = h.getHora_desde();
//						}
					
						if(nombreDia.equals(h.getDia_semana()))
						 {
//						
								duracionPractica = prServ.getDuracionPractica(h.getPractica());
								hora_desde = h.getHora_desde();
								hora_desde_consultorio = hora_desde;
								
									while(hora_desde.compareTo(h.getHora_hasta())<0)
									{
										
										Turno tur = new Turno();
										tur.setFecha_generacion(LocalDate.now());					
										tur.setFecha_t(diasGeneracion.get(i)); 
										tur.setHora_t(hora_desde);
										hora_hasta_turno = hora_desde.plusMinutes(duracionPractica);
										tur.setHora_hasta_t(hora_hasta_turno);
										tur.setEstado_t("Libre");
										tur.setHorario(h);
										LocalDate fecha = diasGeneracion.get(i);
																				
										if(this.validarConsultorioDisponible(fecha,hora_desde,hora_hasta_turno))
										{
											tur.setConsultorio(conServ.getConsultorioDisponible(fecha,hora_desde,hora_hasta_turno));
											turRep.abrirAgenda(tur);											
										}
											
									
										hora_desde= hora_desde.plusMinutes(duracionPractica);	
									}
						}
				   }	  
				  
			}
				  
	
			respuesta = "OK";
		}
		
		else
		{
			respuesta = null;
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

public List<Turno> buscarTurnosAsignados(Integer dni) {
	
	return turRep.buscarTurnosAsignados(dni);	
	
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


public boolean validarConsultorioDisponible(LocalDate fecha, LocalTime hora_desde, LocalTime hora_hasta)
{
	Integer cantTurnos = turRep.validarConsultorioDisponible(fecha,hora_desde,hora_hasta);
	Integer cantConsultorios = conServ.getAllActivos().size();
	
	if(cantConsultorios>cantTurnos)
	{
		return true;
	}
	
	else
	{
		return false;
	}
	
}

public List<Turno> buscarTurnosDisponibles(Integer matricula) {
	
	return turRep.buscarTurnosDisponibles(matricula);
}


//19/03
public String registroTurno(Paciente pac, Integer id_turno) {
	
	//busco la practica que corresponde al turno solicitado
	Practica pr = this.buscarPracticaTurno(id_turno);
	
	//consulto si hay una prescripcion vigente para esa practica
	Prescripcion presc = prescServ.buscarPrescripcionActiva(pac,pr);	
	
	//si no hay una prescripcion activa y vigente para el paciente, registro el turno con el campo que corresponde a la prescripcion en 0
	if(presc==null)
	{
		
		turRep.registroTurnoSinPrescripcion(pac,id_turno);		
	}
	
	else
	{
		turRep.registroTurnoConPrescripcion(pac,id_turno,presc.getId_Prescripcion());
		
	}
	return "OK";
}


//19/03
private Practica buscarPracticaTurno(Integer id_turno) {
	
	return turRep.buscarPracticaTurno(id_turno);
}

public String registrarAsistencia(Paciente pac, Integer idTurno) {
	
	Turno tur = this.buscarTurno(idTurno);	
//		
//	if(tur.getFecha_t().isEqual(LocalDate.now())) //verificacion desactivada para hacer pruebas
//	{
		turRep.registrarAsistencia(pac, tur);
		
		if(tur.getPrescripcion()!=null)
		{
			this.buscarPrescripcion(tur);
			
			if(tur.getPrescripcion().getCant_sesiones()-1 == tur.getPrescripcion().getSesiones_asistidas())
			{
				prescServ.desactivarVigenciaPrescripcion(tur.getPrescripcion());
			}
			
			else
			{
				prescServ.incrementarSesionesAsistidas(tur.getPrescripcion());	
			}
			
		}
		
		return respuesta = "OK";		
		
//	}
//	
//	else
//	{
//		return null;
//	}
//		
	
}

private void buscarPrescripcion(Turno tur) {
	
	turRep.buscarPrescripcion(tur);
	
}

public Turno buscarTurno(Integer id_turno)
{
	return turRep.buscarTurno(id_turno);
}

public String asignarPrescripcionATurno(Turno tur, Integer id_prescripcion)
{
	return turRep.asignarPrescripcionATurno(tur,id_prescripcion);
}
}

package controller;

import java.io.IOException;
import java.util.List;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Profesional;
import entity.Turno;
import entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.HorarioService;
import service.PacienteService;
import service.PracticaService;
import service.PrescripcionService;
import service.TurnoService;

@WebServlet("/registroTurno")

public class TurnoServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	private HorarioService horServ;
	private PracticaService prServ;
	private TurnoService turServ;
	private PacienteService pacServ;
	private PrescripcionService prescServ;
	private List<Practica> practicas;
	
	

	public TurnoServlet() {
		this.horServ = new HorarioService();
		this.prServ = new PracticaService();
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
		this.prescServ = new PrescripcionService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		Usuario us = (Usuario) session.getAttribute("usuario");
		
		practicas = prServ.getAllActivas();
		request.setAttribute("practicas", practicas); 		
 		
 		if(us.getTipo_usuario()==3)
		{
 			Paciente pac = pacServ.buscarPaciente(us.getDni());
			request.setAttribute("paciente", pac);
			List<Prescripcion> prescripciones = prescServ.buscarTodasLasPrescripciones(pac);
			request.setAttribute("prescripciones", prescripciones);
		}
 		
 		else
 		{
 			if(request.getAttribute("prescripciones")==null) {
            List<Prescripcion> prescripciones = prescServ.getAll();
            request.setAttribute("prescripciones", prescripciones);
 			}
 		}
 		  
		
				
		if(request.getParameter("idPrescripcion")!=null) //Me fijo si viene de la ventana de Prescripcion
		{	
			String idPrescripcion = request.getParameter("idPrescripcion");
			Prescripcion prescripcion = prescServ.getPrescripcion(Integer.parseInt(idPrescripcion));
			List<Profesional> profesionales = horServ.getProfesionales(prescripcion.getPractica().getId_practica());
			request.setAttribute("prescripcion", prescripcion);
			request.setAttribute("idPrescripcion", prescripcion.getId_prescripcion());
			request.setAttribute("practicaSeleccionada", prescripcion.getPractica().getId_practica());
			request.setAttribute("profesionales", profesionales);
			if(request.getParameter("profesionalSeleccionado")!=null) 
			{
				Integer matricula = Integer.parseInt(request.getParameter("profesionalSeleccionado"));
				request.setAttribute("profesionalSeleccionado", matricula);
			}				
		}
		if(request.getAttribute("practicaSeleccionada")!=null) //me fijo si ya elegi la practica
		{
			Integer practicaSeleccionada = (Integer) request.getAttribute("practicaSeleccionada");
			if(request.getAttribute("profesionalSeleccionado")!=null) //me fijo si ya elegi el profesional
			{
				Integer profesionalSeleccionado = (Integer) request.getAttribute("profesionalSeleccionado");
				if(request.getAttribute("paciente")!=null) //Me fijo si ya busqué el Paciente para registrar el turno
				{
					Paciente paciente = (Paciente) request.getAttribute("paciente");
					request.setAttribute("paciente", paciente);
					Integer idTurno = (Integer) request.getAttribute("idTurno");
					request.setAttribute("idTurno", idTurno);
					Turno turno = (Turno) request.getAttribute("turno");
					request.setAttribute("turno", turno);
					
				}
				List<Turno> turnos = turServ.buscarTurnosDisponibles(profesionalSeleccionado,practicaSeleccionada);
				request.setAttribute("turnos", turnos);
			}
			List<Profesional> profesionales = horServ.getProfesionales(practicaSeleccionada);
			request.setAttribute("profesionales", profesionales);
			request.setAttribute("practicaSeleccionada", practicaSeleccionada);
		}
		String targetPage = "registroTurno.jsp";
		if(request.getParameter("idPrescripcion") != null) {
		    targetPage += "?idPrescripcion=" + request.getParameter("idPrescripcion");
		}
		practicas = prServ.getAllActivas();
		request.setAttribute("practicas", practicas);
		request.getRequestDispatcher(targetPage).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPrescripcion = null;
		String respuestaOperacion = null;
		String mensaje = null;
		Paciente pac = null;		
		String operacion = request.getParameter("operacion");
		if(request.getParameter("idPrescripcion")!=null) 
		{
			idPrescripcion = request.getParameter("idPrescripcion");
		}
		
		switch (operacion) {		
		case "buscarProfesional": {
			Integer id_practica = Integer.parseInt(request.getParameter("practicas"));
			request.setAttribute("practicaSeleccionada",id_practica); 			
			List<Profesional> profesionales = horServ.getProfesionales(id_practica); 
			request.setAttribute("profesionales", profesionales);
			if(idPrescripcion!=null) 
			{
				response.sendRedirect(request.getContextPath() + "/registroTurno?idPrescripcion=" + idPrescripcion);
			}
			else 
			{
				this.doGet(request, response);	
			}						
			break;
		}		
		
		case "buscarTurnos":
		{
			Integer practicaSeleccionada = Integer.parseInt(request.getParameter("practicaSeleccionada"));
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			request.setAttribute("practicaSeleccionada", practicaSeleccionada);
			request.setAttribute("profesionalSeleccionado", matricula); 			
			List<Turno> turnosDisponibles = turServ.buscarTurnosDisponibles(matricula,practicaSeleccionada);
			request.setAttribute("turnos", turnosDisponibles);
			if(turnosDisponibles!=null) 
			{
				if(idPrescripcion!=null) 
				{
					 String redirectURL = request.getContextPath() + "/registroTurno?idPrescripcion=" + idPrescripcion;
					    redirectURL += "&practicaSeleccionada=" + practicaSeleccionada;
					    redirectURL += "&profesionalSeleccionado=" + matricula;
					    response.sendRedirect(redirectURL);
				}
				else 
				{
					this.doGet(request, response);	
				}
			}
			else 
			{
				respuestaOperacion = "No hay turnos diponibles para ese profesional.";
				request.setAttribute("mensaje", respuestaOperacion);						
			}
			break;
		}		
		
		case "buscarPaciente":
		{
			Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
			Integer idTurno = Integer.parseInt(request.getParameter("idTurnoHidden"));
			Turno turno = turServ.buscarTurno(idTurno);
			request.setAttribute("dniPacienteBuscado", dni);
			request.setAttribute("idTurno",idTurno);
			request.setAttribute("turno", turno);
			request.setAttribute("practicaSeleccionada", turno.getHorario().getPractica().getId_practica());
			request.setAttribute("profesionalSeleccionado", turno.getHorario().getProfesional().getMatricula());
			pac = pacServ.buscarPaciente(dni);			
			if(pac == null)
			{			
				respuestaOperacion="El Paciente no está registrado.";
				request.setAttribute("mensaje", respuestaOperacion);
			}			
			else
			{
				request.setAttribute("paciente", pac);					
			}			
			if(idPrescripcion!=null) 
			{
				response.sendRedirect(request.getContextPath() + "/registroTurno?idPrescripcion=" + idPrescripcion);
			}
			else 
			{
				this.doGet(request, response);	
			}	
			break;
		}		
		
		case "registroTurno":
		{
			Integer id_turno = Integer.parseInt(request.getParameter("idTurno"));
			
			if(idPrescripcion!=null) 
			{
				Integer idPresc = Integer.parseInt(idPrescripcion);
				Prescripcion prescripcion = prescServ.getPrescripcion(idPresc);
				if(turServ.validarPrescripcionAgotada(idPresc)) {
					respuestaOperacion = turServ.registroTurnoPrescripcion(id_turno, prescripcion.getPaciente(), idPresc);
					request.setAttribute("mensaje", respuestaOperacion);
				}
				else {
					mensaje ="Ya reservó la totalidad de los turnos.";
					request.setAttribute("mensaje", mensaje);
				}
				
			}
			else 
			{
				Integer dniPac = Integer.parseInt(request.getParameter("dniPaciente"));				
				pac = pacServ.buscarPaciente(dniPac);
				
				if(pac!=null)
				{				
					respuestaOperacion = turServ.registroTurno(pac,id_turno);	
									
					if (respuestaOperacion == "OK") {
						mensaje = "Turno registrado exitosamente.";
						request.setAttribute("mensaje", mensaje);					
					}
				}
				else {				
						mensaje = "Debe seleccionar un paciente.";
						request.setAttribute("mensaje", mensaje);					
					}				
				}			

			}
			if(request.getAttribute("mensaje")!=null) {
				this.doGet(request, response);
			}
			else 
			{
				request.setAttribute("profesionales",null);
				request.setAttribute("turnos", null);
				request.setAttribute("dniPacienteBuscado", null);
				request.setAttribute("paciente", null);		
				request.setAttribute("practicaSeleccionada", null);				
				if(idPrescripcion!=null)
				{
					response.sendRedirect(request.getContextPath() + "/registroTurno?idPrescripcion=" + idPrescripcion);
				}
				else 
				{
					this.doGet(request, response);	
				}	
			}
			break;			
		}
	}	
}
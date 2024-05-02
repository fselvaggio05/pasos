package controller;

import java.io.IOException;
import java.util.List;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;
import entity.Profesional;
import entity.Turno;
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
		if(request.getParameter("idPrescripcion")!=null) //Me fijo si viene de la ventana de Prescripcion
		{
			practicas = prServ.getAllActivas();
			Integer idPrescripcion = Integer.parseInt(request.getParameter("idPrescripcion"));
			Prescripcion prescripcion = prescServ.getPrescripcion(idPrescripcion);
			request.setAttribute("practicas", practicas);
			request.setAttribute("prescripcion", prescripcion);
			request.setAttribute("practicaSeleccionada", prescripcion.getPractica().getId_practica());
			request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
		}
		else if(request.getAttribute("practicaSeleccionada")!=null) //me fijo si ya elegi la practica
		{
			practicas = prServ.getAllActivas();
			Integer idPrescripcion = Integer.parseInt((String) request.getAttribute("idPrescripcion"));
			Prescripcion prescripcion = prescServ.getPrescripcion(idPrescripcion);
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			List<Turno> turnosDisponibles = turServ.buscarTurnosDisponibles(matricula);
			request.setAttribute("practicas", practicas);
			request.setAttribute("prescripcion", prescripcion);
			request.setAttribute("practicaSeleccionada", prescripcion.getPractica().getId_practica());
			request.setAttribute("turnos",turnosDisponibles);
			request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
		}
		else if(request.getAttribute("profesionalSeleccionado")!=null) 
		{
			
		}
		else 
		{
			practicas = prServ.getAllActivas();
			request.setAttribute("practicas", practicas);		
			request.getRequestDispatcher("registroTurno.jsp").forward(request, response);
		}			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String respuestaOperacion = null;
		String mensaje = null;
		Paciente pac = null;		
		HttpSession session = request.getSession();			
		String operacion = request.getParameter("operacion");
				
		switch (operacion) {		
		case "buscarProfesional": {
			Integer id_practica = Integer.parseInt(request.getParameter("practicas"));			
			request.setAttribute("practicaSeleccionada",id_practica); 			
			List<Profesional> profesionales = horServ.getProfesionales(id_practica); 
			request.setAttribute("profesionales", profesionales);
			this.doGet(request, response);					
			break;
		}		
		
		case "buscarTurnos":
		{
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			request.setAttribute("profesionalSeleccionado", matricula); 			
			List<Turno> turnosDisponibles = turServ.buscarTurnosDisponibles(matricula);
			session.setAttribute("turnos", turnosDisponibles);
			this.doGet(request, response);			
			break;
		}		
		
		case "buscarPaciente":
		{
			Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
			request.setAttribute("dniPacienteBuscado", dni); 			
			pac = pacServ.buscarPaciente(dni);			
			if(pac==null)
			{			
				respuestaOperacion="No se ha encontrado el paciente";
			}			
			else
			{
				session.setAttribute("paciente", pac);
				this.doGet(request, response);				
			}			
			break;
		}		
		
		case "registroTurno":
		{
			pac = (Paciente)session.getAttribute("paciente");
			Integer id_turno = Integer.parseInt(request.getParameter("idTurno"));
			
			if(pac!=null)
			{				
				respuestaOperacion = turServ.registroTurno(pac,id_turno);	
				
				if (respuestaOperacion == "OK") {
					mensaje = "Turno registrado exitosamente    ";
					request.setAttribute("mensaje", mensaje);					
				}
			}
			else {				
					mensaje = "Debe seleccionar un paciente   ";
					request.setAttribute("mensaje", mensaje);					
				}				
			}			
			session.setAttribute("profesionales",null);
			session.setAttribute("turnos", null);
			session.setAttribute("dniPacienteBuscado", null);
			session.setAttribute("paciente", null);		
			session.setAttribute("practicaSeleccionada", null);				
			this.doGet(request, response);
			break;			
		}
	}	
}

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
	private List<Practica> practicas;
	private PrescripcionService prescServ;
	

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
			session.setAttribute("paciente", pac);
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
 		  
		request.getRequestDispatcher("registroTurno.jsp").forward(request, response);	
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
			session.setAttribute("practicaSeleccionada",id_practica); 			
			List<Profesional> profesionales = horServ.getProfesionales(id_practica); 
			session.setAttribute("profesionales", profesionales);
			this.doGet(request, response);					
			break;
		}		
		
		case "buscarTurnos":
		{
			Integer matricula = Integer.parseInt(request.getParameter("profesional"));
			session.setAttribute("profesionalSeleccionado", matricula); 			
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
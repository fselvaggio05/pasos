
package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.Horario;
import entity.Paciente;
import entity.Practica;
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
import service.ProfesionalService;
import service.TurnoService;

@WebServlet("/registroTurno")

public class TurnoServlet extends HttpServlet {

	private HorarioService horServ;
	private ProfesionalService profServ;
	private PracticaService prServ;
	private TurnoService turServ;
	private PacienteService pacServ;
	private List<Horario> horarios; 
	private List<Practica> practicas;
	

	public TurnoServlet() {
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
		this.prServ = new PracticaService();
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		practicas = prServ.getAllActivas();
		HttpSession session = request.getSession(); 
		session.setAttribute("practicas", practicas);		
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
		//	request.setAttribute("practicaSeleccionada", id_practica);
//			request.getRequestDispatcher("registroTurno").forward(request, response);
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
			respuestaOperacion = turServ.registroTurno(pac.getDni(),id_turno);			
			
			if (respuestaOperacion == "OK") {

				mensaje = "Turno registrado exitosamente    ";
				request.setAttribute("mensaje", mensaje);
				
			}

			else {
				
				if(respuestaOperacion == null)
				{
					mensaje = "No se ha podido registrar el turno   ";
					request.setAttribute("mensaje", mensaje);
					
				}
				
				else
				{
					mensaje="No se ha encontrado el paciente    ";
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
}



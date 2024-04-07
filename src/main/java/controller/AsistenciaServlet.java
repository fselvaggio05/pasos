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

@WebServlet("/registroAsistencia")

public class AsistenciaServlet extends HttpServlet {
	
	private TurnoService turServ;
	private PacienteService pacServ;
	
	

	public AsistenciaServlet() {
		
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("registrarAsistencia.jsp").forward(request, response);		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		HttpSession session = request.getSession();
		
		

		switch (operacion) {
		
		case "buscarPaciente": 
		{
			Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
			Paciente pac = pacServ.buscarPaciente(dni); //busco el paciente para mostrar los datos tambine junto con los turnos registrados
			session.setAttribute("paciente", pac);
			List<Turno> turnos = new ArrayList<Turno>();			
			turnos = turServ.buscarTurnosAsignadosPaciente(pac.getDni());
			session.setAttribute("turnos", turnos);	
			break;
			
			
		}

		case "regitrarAsistencia": {
			Integer idTurno = Integer.parseInt(request.getParameter("idTurno"));
			Paciente pac = (Paciente)session.getAttribute("paciente");
			respuestaOperacion = turServ.registrarAsistencia(pac,idTurno);
			//consultar la prescripcion asociada para registrar la asistencia en la cantidad de sesiones asistidas 

			if (respuestaOperacion == "OK") {

				mensaje = "Asistencia registrada exitosamente";
				request.setAttribute("mensaje", mensaje);		
			}

			else {
				
				if(respuestaOperacion == null)
				{
					mensaje = "El turno a registrar no es del dia de hoy    ";
					request.setAttribute("mensaje", mensaje);
				}
				
				else
				{
					mensaje = respuestaOperacion;
					request.setAttribute("mensaje", mensaje);
					
				}
				
			}
			
			session.setAttribute("paciente", null);
			session.setAttribute("turnos", null);
			
			break;
			
		}

	}
		
		
		
		this.doGet(request, response);

}
}

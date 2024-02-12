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
		
		

		switch (operacion) {
		
		case "buscarPaciente": 
		{
			Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
			Paciente pac = pacServ.buscarPaciente(dni); //busco el paciente para mostrar los datos tambine junto con los turnos registrados
			List<Turno> turnos = new ArrayList<Turno>();			
			turnos = turServ.buscarTurnosPendientes(pac.getDni());
			request.setAttribute("turnos", turnos);	
			break;
			
			
		}

		case "generar": {
									
			
		}

	}
		
		if (respuestaOperacion == "OK") {

			mensaje = "Agenda generada exitosamente    ";
			request.setAttribute("mensaje", mensaje);		
		}

		else {
			
			if(respuestaOperacion == null)
			{
				mensaje = "No existen horarios para la/s fecha/s de generacion de agenda    ";
				request.setAttribute("mensaje", mensaje);
			}
			
			else
			{
				mensaje = respuestaOperacion;
				request.setAttribute("mensaje", mensaje);
				
			}
			
		}
		
		
		this.doGet(request, response);

}
}

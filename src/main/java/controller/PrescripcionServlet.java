package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.Horario;
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
import service.HorarioService;
import service.PacienteService;
import service.PracticaService;
import service.PrescripcionService;
import service.ProfesionalService;
import service.TurnoService;

@WebServlet("/prescripcion")

public class PrescripcionServlet extends HttpServlet {
	
	private TurnoService turServ;
	private PacienteService pacServ;
	private PrescripcionService prescServ;
	
	

	public PrescripcionServlet() {
		
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
		this.prescServ = new PrescripcionService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("registroPrescripcion.jsp").forward(request, response);		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		 
		
		

		switch (operacion) {
		
		
		case "buscarPaciente": 
		{
			Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
			Paciente pac = pacServ.buscarPaciente(dni); 
			
			//busco el paciente para mostrar sus datos ademas de los turnos registrados
			
			if(pac!=null)
			{
				List<Turno> turnos = new ArrayList<Turno>();			
				turnos = turServ.buscarTurnosAsignados(pac.getDni());
				request.setAttribute("turnos", turnos);	
				request.setAttribute("paciente", pac);
				respuestaOperacion = "Paciente ok";
				
			}
			
			else
			{
				respuestaOperacion = "Paciente no encontrado";
			}
			
			break;
			
			
		}

		case "alta": 
		{
			
			Prescripcion pr = new Prescripcion();
			pr.setCant_sesiones(Integer.parseInt(request.getParameter("cantSesiones")));
			String salida = request.getParameter("idPractica");
			System.out.println(salida);
			pr.setCod_practica(Integer.parseInt(request.getParameter("idPractica")));
			pr.setFecha_prescripcion(LocalDate.parse(request.getParameter("fechaPresc")));
			pr.setNro_afiliado(Integer.parseInt(request.getParameter("nroAfiliado")));
			pr.setSesiones_asistidas(1);
			pr.setFecha_alta_prescripcion(LocalDate.now());
			
			respuestaOperacion = prescServ.insertarPrescripcion(pr);									
			break;
		}

	}
		
		if (respuestaOperacion == "OK") {

			mensaje = "Prescripcion registrada exitosamente    ";
			request.setAttribute("mensaje", mensaje);		
		}

		else {
			
			if(respuestaOperacion.endsWith("encontrado"))
			{
				mensaje = "El DNI del paciente no se encuentra registrado    ";
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

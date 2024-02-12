package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.Horario;
import entity.Practica;
import entity.Profesional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.HorarioService;
import service.PracticaService;
import service.ProfesionalService;

@WebServlet("/horarios")

public class HorarioServlet extends HttpServlet {

	protected HorarioService horServ;
	protected ProfesionalService profServ;
	protected PracticaService prServ; // vinculo con las practicas para cargarlas en el alta del horario

	public HorarioServlet() {
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
		this.prServ = new PracticaService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Horario> horarios = horServ.getAllActivos();
		List<Profesional> profesionales = profServ.getAll();
		
		//TODO: TRAER EL CONTENIDO DE LA TABLA PRACTICAS-PROFESIONAL. ACA ESTOY TRAYENDO TODAS
		List<Practica> practicas = prServ.getAllActivas();
		request.setAttribute("horarios", horarios);
		request.setAttribute("profesionales", profesionales);
		request.setAttribute("practicas", practicas);

		request.getRequestDispatcher("altaHorario_activo.jsp").forward(request, response);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje, mensaje2;
		Integer idHorario;
		Boolean calculoHorario;

		switch (operacion) {
		
		case "buscarProfesional": 
		{
			Integer matricula = Integer.parseInt(request.getParameter("matricula"));
			List<Horario> horarios = horServ.getHorariosActivosProfesional(matricula);
			List<Profesional> profesionales = profServ.getAll();
			request.setAttribute("profesionales", profesionales);
			request.setAttribute("horarios", horarios);
			request.getRequestDispatcher("horarios.jsp").forward(request, response);

			break;

		}

		case "altaHorario": {

			Horario hr = new Horario();
			
			
			LocalTime desde = null;
			LocalTime hasta = null;
			Integer id_practica = null;
			
			
			id_practica = Integer.parseInt(request.getParameter("id_practica"));	
			
			
			//DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
			desde = LocalTime.parse(request.getParameter("hora_desde"));
			hasta = LocalTime.parse(request.getParameter("hora_hasta"));
			//desde = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_desde"));
			//hasta = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_hasta"));
			
			
			//TODO: ARREGLAR LOS DESPELOTES POR LOS CAMBIOS DE TIPO
			calculoHorario = horServ.calculaHorario(id_practica, desde, hasta);		
			
			if(calculoHorario == true)
			{
				hr.setMatricula(Integer.parseInt(request.getParameter("matriculaProf")));
				hr.setDia_semana(request.getParameter("dia_semana"));
				hr.setId_practica(id_practica);				
				hr.setHora_desde(desde);
				hr.setHora_hasta(hasta);
				
				horServ.validaConsulorio(hr);
				
				respuestaOperacion = horServ.insertarHorario(hr);
				
				
			}
			
			else {				
				respuestaOperacion = "El horario ingresado debe ajustarse a la duracion de la practica  ";
	
			}		

			break;
		}

		case "activar": {
			idHorario = Integer.parseInt(request.getParameter("idEnviado"));
			respuestaOperacion = horServ.inactivarHorario(idHorario);
			

			break;
		}

		case "editar": {
			
			Horario hr = new Horario();
			LocalTime desde= null;
			LocalTime hasta = null;
			
			desde = LocalTime.parse(request.getParameter("hora_desde"));
			hasta = LocalTime.parse(request.getParameter("hora_hasta"));
			
			hr.setId_horario(Integer.parseInt(request.getParameter("id_horario")));
			hr.setDia_semana(request.getParameter("dia_semana"));
			hr.setId_practica(Integer.parseInt(request.getParameter("id_practica")));
			hr.setHora_desde(desde);
			hr.setHora_hasta(hasta);			
		
			respuestaOperacion = horServ.actualizarHorario(hr);
			
			break;
			

		}

		}

		if (respuestaOperacion == "OK") {

			mensaje = "Operacion realizada correctamente   ";
			request.setAttribute("mensaje", mensaje);		
		}

		else {
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
		}
		
		
		this.doGet(request, response);

	}

}

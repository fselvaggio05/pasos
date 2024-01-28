package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		List<Practica> practicas = prServ.getAllActivas();
		request.setAttribute("horarios", horarios);
		request.setAttribute("profesionales", profesionales);
		request.setAttribute("practicas", practicas);

		request.getRequestDispatcher("altaHorario_activo.jsp").forward(request, response);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje;
		Integer idHorario;

		switch (operacion) {
		
		case "buscarProfesional": 
		{
			Integer matricula = Integer.parseInt(request.getParameter("matricula"));
			List<Horario> horarios = horServ.getHorariosActivosProfesional(matricula);
			List<Profesional> profesionales = profServ.getAll();
			request.setAttribute("profesionales", profesionales);
			request.setAttribute("horarios", horarios);
			request.getRequestDispatcher("altaHorario_activo.jsp").forward(request, response);

			break;

		}

		case "altaHorario": {

			Horario hr = new Horario();
			
			
			Date desde = null;
			Date hasta = null;
			
			hr.setMatricula(Integer.parseInt(request.getParameter("matriculaProf")));
			hr.setDia_semana(request.getParameter("dia_semana"));
			try {
				desde = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_desde"));
				hasta = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_hasta"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hr.setId_practica(Integer.parseInt(request.getParameter("id_practica")));
			hr.setHora_desde(desde);
			hr.setHora_hasta(hasta);
			respuestaOperacion = horServ.insertarHorario(hr);

			// agregar mensaje por javascript de mensaje hecho ok.

			this.doGet(request, response);
			break;
		}

		case "activar": {
			idHorario = Integer.parseInt(request.getParameter("idEnviado"));
			respuestaOperacion = horServ.inactivarHorario(idHorario);
			

			break;
		}

		case "editar": {
			
			Horario hr = new Horario();
			Date desde= null;
			Date hasta = null;
			
			try {
				desde = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_desde"));
				hasta = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_hasta"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

			mensaje = "La operacion se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);		
		}

		else {
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
		}
		
		
		this.doGet(request, response);

	}

}

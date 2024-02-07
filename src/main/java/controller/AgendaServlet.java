package controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import service.TurnoService;

@WebServlet("/generarAgendas")

public class AgendaServlet extends HttpServlet {

	private HorarioService horServ;
	private ProfesionalService profServ;
	private PracticaService prServ;
	private TurnoService turServ;
	private List<Horario> horarios; 
	

	public AgendaServlet() {
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
		this.prServ = new PracticaService();
		this.turServ = new TurnoService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		horarios = horServ.getAllActivos();
		request.setAttribute("horarios", horarios);
		request.getRequestDispatcher("aperturaAgenda.jsp").forward(request, response);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje=null;
		

		switch (operacion) {
		
		case "eliminar": 
		{
//			Integer matricula = Integer.parseInt(request.getParameter("matricula"));
//			List<Horario> horarios = horServ.getHorariosActivosProfesional(matricula);
//			List<Profesional> profesionales = profServ.getAll();
//			request.setAttribute("profesionales", profesionales);
//			request.setAttribute("horarios", horarios);
//			request.getRequestDispatcher("altaHorario_activo.jsp").forward(request, response);
			System.out.println("esto es eliminar seleccionados");
			break;

		}

		case "generar": {
									
			turServ.abrirAgenda(horarios);
			
			
			
			break;
		}

		case "activar": {
//			idHorario = Integer.parseInt(request.getParameter("idEnviado"));
//			respuestaOperacion = horServ.inactivarHorario(idHorario);
			

			break;
		}

		case "editar": {
			
//			Horario hr = new Horario();
//			Date desde= null;
//			Date hasta = null;
//			
//			try {
//				desde = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_desde"));
//				hasta = new SimpleDateFormat("HH:mm").parse(request.getParameter("hora_hasta"));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			hr.setId_horario(Integer.parseInt(request.getParameter("id_horario")));
//			hr.setDia_semana(request.getParameter("dia_semana"));
//			hr.setId_practica(Integer.parseInt(request.getParameter("id_practica")));
//			hr.setHora_desde(desde);
//			hr.setHora_hasta(hasta);			
//		
//			respuestaOperacion = horServ.actualizarHorario(hr);
//			
//			break;
//			

		}

		}
//
//		if (respuestaOperacion == "OK") {
//
//			mensaje = "La operacion se ha realizado correctamente";
//			request.setAttribute("mensaje", mensaje);		
//		}
//
//		else {
//			mensaje = respuestaOperacion;
//			request.setAttribute("mensaje", mensaje);
//		}
//		
//		
		this.doGet(request, response);

	}

}

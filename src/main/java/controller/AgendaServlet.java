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
		request.getRequestDispatcher("generarAgenda.jsp").forward(request, response);		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		Integer cantSeleccionados = null;
		Integer cantTotalHorarios = null;
		
		switch (operacion) {
		
		case "generar": {
			
			
			String[] seleccionados = request.getParameterValues("seleccionados");
			
			List<Horario> horariosSeleccionados = new ArrayList<Horario>();
			horariosSeleccionados = turServ.obtenerSeleccionados(seleccionados, horarios);
			
			cantSeleccionados = horariosSeleccionados.size();
			cantTotalHorarios = horarios.size();
			
			respuestaOperacion = turServ.abrirAgenda(horariosSeleccionados);			
			break;
		}

	}
		
		if (respuestaOperacion == "OK") {

			mensaje = "Agenda generada exitosamente para "+cantSeleccionados+ " horarios seleccionados de " +cantTotalHorarios+" horarios";
			request.setAttribute("mensaje", mensaje);		
		}

		else {
			
			if(respuestaOperacion == null)
			{
				mensaje = "La generacion de agenda ya fue realizada el dia de hoy   ";
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

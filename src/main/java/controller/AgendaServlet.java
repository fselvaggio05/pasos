package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Horario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.HorarioService;
import service.TurnoService;

@WebServlet("/generarAgendas")

public class AgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HorarioService horServ;
	private TurnoService turServ;
	private List<Horario> horarios; 
	

	public AgendaServlet() {
		this.horServ = new HorarioService();
		this.turServ = new TurnoService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();		
		if(session!=null) 
		{
			horarios = horServ.getAllActivos();
			request.setAttribute("horarios", horarios);
			request.getRequestDispatcher("generarAgenda.jsp").forward(request, response);
		}	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		Integer cantSeleccionados = null;
		Integer cantTotalHorarios = null;
		
		switch (operacion) {
		
		case "generar": {
			
			if(request.getParameter("seleccionados")==null) {
				respuestaOperacion="Por favor, seleccione horarios para abrir agenda.";
			}
			else {
				String[] seleccionados = request.getParameterValues("seleccionados");
				
				List<Horario> horariosSeleccionados = new ArrayList<Horario>();
				horariosSeleccionados = turServ.obtenerSeleccionados(seleccionados, horarios);
				
				cantSeleccionados = horariosSeleccionados.size();
				cantTotalHorarios = horarios.size();
				
				respuestaOperacion = turServ.abrirAgenda(horariosSeleccionados);
			}
			break;
		}
	}
		
		if (respuestaOperacion == "OK") {

			mensaje = "Agenda generada exitosamente para "+cantSeleccionados+ " horario/s seleccionados de " +cantTotalHorarios+" horarios  ";
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
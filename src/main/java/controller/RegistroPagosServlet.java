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
import service.PracticaService;
import service.ProfesionalService;
import service.TurnoService;

@WebServlet("/registroPagos")

public class RegistroPagosServlet extends HttpServlet {

	private HorarioService horServ;
	private ProfesionalService profServ;
	private PracticaService prServ;
	private TurnoService turServ;
	 
	

	public RegistroPagosServlet() {
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
		this.prServ = new PracticaService();
		this.turServ = new TurnoService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("registroPracticasAbonadas.jsp").forward(request, response);
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mensaje = null;
		String operacion = request.getParameter("operacion");		
		
		List<Turno> turnosFacturados = new ArrayList<Turno> ();
		
		switch(operacion)
		{
		
			case "buscarTurnosFacturados":
				
			{
				LocalDate fechaDesde = LocalDate.parse(request.getParameter("fechaDesde"));
				LocalDate fechaHasta = LocalDate.parse(request.getParameter("fechaHasta"));
				turnosFacturados = turServ.buscarTurnosPendientesCobro(fechaDesde,fechaHasta);				
				request.setAttribute("turnosPorCobrar", turnosFacturados);				
				break;
			}
			
			
			case "registrarPago":
				
			{
				String[] seleccionados = request.getParameterValues("seleccionados");
				turnosFacturados = turServ.obtenerTurnos(seleccionados);			
				mensaje = turServ.registrarPagoTurnos(turnosFacturados);
				break;
			}
		
		}
		
		this.doGet(request, response);	
		

}
}

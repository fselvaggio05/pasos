package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entity.Profesional;
import entity.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProfesionalService;
import service.TurnoService;

@WebServlet("/registroPagos")

public class RegistroPagosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProfesionalService profServ;
	private TurnoService turServ;
	 
	

	public RegistroPagosServlet() {
		this.profServ = new ProfesionalService();
		this.turServ = new TurnoService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Profesional> profesionales = profServ.getAll();
        request.setAttribute("profesionales", profesionales);
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
				Integer matricula = Integer.parseInt(request.getParameter("profesional"));
				LocalDate fechaDesde = LocalDate.parse(request.getParameter("fecha_desde"));
				LocalDate fechaHasta = LocalDate.parse(request.getParameter("fecha_hasta"));
				turnosFacturados = turServ.buscarTurnosPendientesCobro(matricula,fechaDesde,fechaHasta);				
				request.setAttribute("turnosPorCobrar", turnosFacturados);				
				request.setAttribute("profesionalSeleccionado", matricula);
                request.setAttribute("fecha_desde", fechaDesde);
                request.setAttribute("fecha_hasta", fechaHasta);
				break;
			}
			
			
			case "registrarPago":
				
			{
				String[] seleccionados = request.getParameterValues("seleccionados");
				turnosFacturados = turServ.obtenerTurnos(seleccionados);			
				if(turServ.registrarPagoTurnos(turnosFacturados).equals("OK")){
					mensaje = "Cobro registrado correctamente.";
				}
				else{
					mensaje = "No se pudo registrar el cobro.";
				};
				request.setAttribute("mensaje", mensaje);
				break;
			}
		
		}
		
		this.doGet(request, response);	
		

}
}

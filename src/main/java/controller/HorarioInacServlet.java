package controller;

import java.io.IOException;
import java.util.List;

import entity.Horario;
import entity.Profesional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.HorarioService;
import service.ProfesionalService;


@WebServlet("/horariosIn")

public class HorarioInacServlet extends HttpServlet{
	
	protected HorarioService horServ;
	protected ProfesionalService profServ;
	
	
	public HorarioInacServlet()
	{
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//TODO: DEFINIR COMO MANEJAR LOS HORARIOS CON EL FILTRADO O SIN 
		
			List<Horario> horarios = horServ.getAllInactivos();
			List<Profesional> profesionales = profServ.getAll();
			request.setAttribute("horarios", horarios);
			request.setAttribute("profesionales", profesionales);		
			request.getRequestDispatcher("altaHorario_inactivo.jsp").forward(request,response);
			
	}	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String operacion = request.getParameter("operacion");
		Integer idHorario;
		String respuestaOperacion =null;
		String mensaje;
		
		switch(operacion)
		{
		case "buscarProfesional":
			{
			Integer matricula = Integer.parseInt(request.getParameter("matricula"));
			List<Horario> horarios = horServ.getHorariosInactivosProfesional(matricula);
			List<Profesional> profesionales = profServ.getAll();
			request.setAttribute("profesionales", profesionales);
			request.setAttribute("horarios", horarios);
			request.getRequestDispatcher("altaHorario_inactivo.jsp").forward(request, response);

			break;
			}
			
		case "activar":
			{
			idHorario = Integer.parseInt(request.getParameter("idEnviado"));
			respuestaOperacion = horServ.revivirHorario(idHorario);
			break;
			}
		
		}
		

		if (respuestaOperacion == "OK")
		{
		
			mensaje = "La operacion se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);			
			
		}
		
		else 
		{
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);			
			
			
		}
		
		this.doGet(request, response);
		
	}

	

}

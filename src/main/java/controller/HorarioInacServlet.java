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
			request.getRequestDispatcher("altaHorario_inactivo.jsp").forward(request,response);;
		
		
		
	}
	

}

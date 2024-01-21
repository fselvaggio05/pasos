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




@WebServlet ("/horarios")


public class HorarioServlet extends HttpServlet{
	
	protected HorarioService horServ;
	protected ProfesionalService profServ;
	
	
	public HorarioServlet()
	{
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		List<Horario> horarios = horServ.getAllActivos();
		List<Profesional> profesionales = profServ.getAll();
		request.setAttribute("horarios", horarios);
		request.setAttribute("profesionales", profesionales);		
		request.getRequestDispatcher("altaHorario.jsp").forward(request,response);;
		
	}
	



public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	String operacion = request.getParameter("operacion");
	
	switch(operacion)
	{
	case "buscarProfesional":
	{
		Integer matricula = Integer.parseInt(request.getParameter("matricula"));
		List<Horario> horarios = horServ.getHorariosProfesional(matricula);
		List<Profesional> profesionales = profServ.getAll();
		request.setAttribute("profesionales", profesionales);
		request.setAttribute("horarios", horarios);
		request.getRequestDispatcher("altaHorario.jsp").forward(request, response);

	}
		
	}
	

	}

}




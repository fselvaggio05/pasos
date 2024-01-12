package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PracticaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Practica;

/**
 * Servlet implementation class Practicas
 */

@WebServlet("/practicas")
public class PracticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected PracticaService prServ;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PracticaServlet() {
    	this.prServ= new PracticaService();
    }

    	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * // TODO Auto-generated method stub List<String> nombres = new ArrayList<>();
		 * nombres.add("Juan"); nombres.add("María"); nombres.add("Pedro");
		 * 
		 * request.setAttribute("listaNombres", nombres);
		 * request.getRequestDispatcher("index.jsp").forward(request,response);
		 */
		
		    List<Practica> practicas = prServ.getAll();
	        request.setAttribute("practicas", practicas);
	        request.getRequestDispatcher("altaPractica.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String descPractica;
		Integer idEquipo;
		
		descPractica = request.getParameter("descPractica");
		idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
		
		prServ.insertarPractica(descPractica, idEquipo);
		
	}

}

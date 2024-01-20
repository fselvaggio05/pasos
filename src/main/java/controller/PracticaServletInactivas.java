package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.EquipoService;
import service.PracticaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xml.utils.SystemIDResolver;

import entity.Equipo;
import entity.Practica;

/**
 * Servlet implementation class Practicas
 */

@WebServlet("/practicasIn")

public class PracticaServletInactivas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected PracticaService prServ;
	protected EquipoService eqServ;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PracticaServletInactivas() {
    	this.prServ= new PracticaService();
    	this.eqServ = new EquipoService();
    	
    }

    	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		
		   
		    List<Practica> practicasInactivas = prServ.getAllInactivas();
		    
		    List<Equipo> equipos = eqServ.getAll();
	       
	        request.setAttribute("practicasI", practicasInactivas);
	        request.setAttribute("equipos", equipos);
	        request.getRequestDispatcher("altaPractica_inactiva.jsp").forward(request,response);
	        
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer idPractica;
		String descPractica;
		Integer idEquipo;
		Integer estado;
		String mensaje;
		String respuestaOperacion=null;
		
		String opcion = request.getParameter("operacion");

		
		switch(opcion)
		{		
			
			case "eliminar":
			{
				idPractica = Integer.parseInt(request.getParameter("idPractica"));
			    respuestaOperacion = prServ.habilitarPractica(idPractica);
			    break;
			}

		}
		
		
		if (respuestaOperacion == "OK")
		{
		
			mensaje = "La operacion se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
			
		}
		
		else 
		{
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
			
			
		}
		
		
}	



	
	
	
}
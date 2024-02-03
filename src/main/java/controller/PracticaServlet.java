package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EquipoService;
import service.PracticaService;

import java.io.IOException;
import java.util.List;
import entity.Equipo;
import entity.Practica;

/**
 * Servlet implementation class Practicas
 */

@WebServlet("/practicas")

public class PracticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected PracticaService prServ;
	protected EquipoService eqServ;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PracticaServlet() {
    	this.prServ= new PracticaService();
    	this.eqServ = new EquipoService();
    	
    }

    	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		
		    List<Practica> practicasActivas = prServ.getAllActivas();
		    List<Practica> practicasInactivas = prServ.getAllInactivas();		    
		    List<Equipo> equipos = eqServ.getAll();
		    
	        request.setAttribute("practicasA", practicasActivas);
	        request.setAttribute("practicasI", practicasInactivas);
	        request.setAttribute("equipos", equipos);
	        request.getRequestDispatcher("altaPractica_activa.jsp").forward(request,response);
	        
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer idPractica;
		String descPractica;
		Integer duracion;
		Integer idEquipo;
		Integer estado;
		String mensaje;
		String respuestaOperacion=null;
		
		String opcion = request.getParameter("operacion");

		
		switch(opcion)
		{		
			case "alta":
			{

				idPractica = Integer.parseInt(request.getParameter("idPractica"));
				descPractica = request.getParameter("descPractica");
				duracion = Integer.parseInt(request.getParameter("duracion"));
				idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
				respuestaOperacion = prServ.insertarPractica(idPractica,descPractica,duracion, idEquipo);
				break;
			}
			
			case "actualizar":
			{

				idPractica = Integer.parseInt(request.getParameter("idPractica"));
				descPractica = request.getParameter("descPractica");
				duracion = Integer.parseInt(request.getParameter("duracion"));
				idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
				
				respuestaOperacion = prServ.actualizarPractica(idPractica, descPractica, duracion, idEquipo);
				break;
			}
			
			case "eliminar":
			{
				idPractica = Integer.parseInt(request.getParameter("idEnviado"));
				estado = Integer.parseInt(request.getParameter("estado"));
			    respuestaOperacion = prServ.eliminarPractica(idPractica, estado);
			    break;
			}

		}
		
		

		
		if (respuestaOperacion == "OK")
		{
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mensaje = "La operacion se ha realizado correctamente";
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
			
		}
		
		else 
		{
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			this.doGet(request, response);
			
			
		}
		
		
}	



	
	
	
}
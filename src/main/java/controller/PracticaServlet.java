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

			HttpSession session = request.getSession();
		
		    List<Practica> practicas = prServ.getAll();
		    List<Equipo> equipos = eqServ.getAll();
	        request.setAttribute("practicas", practicas);
	        request.setAttribute("equipos", equipos);
	             
	        request.getRequestDispatcher("altaPractica.jsp").forward(request,response);
	        
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer idPractica;
		String descPractica;
		Integer idEquipo;
		String mensaje;

		idPractica = Integer.parseInt(request.getParameter("idPractica"));
		descPractica = request.getParameter("descPractica");
		idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
		String respuestaOperacion = prServ.insertarPractica(idPractica,descPractica, idEquipo);
		
		if (respuestaOperacion == "OK")
		{
		
			mensaje = "La practica se ha ingresado correctamente";
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




	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			Integer idPractica;
			String mensaje;
			idPractica = Integer.parseInt(request.getParameter("idPractica"));
			String respuestaOperacion = prServ.eliminarPractica(idPractica);
			
			if (respuestaOperacion == "OK")
			{
			
				mensaje = "La practica se ha ingresado correctamente";
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
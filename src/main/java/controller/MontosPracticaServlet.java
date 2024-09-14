package controller;

import java.io.IOException;
import java.time.LocalDate;
import entity.Practica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MontosPracticaService;
import service.PracticaService;

@WebServlet("/MontosPractica")

public class MontosPracticaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MontosPracticaService mps;
	private PracticaService practServ;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperar el parámetro idPractica de la URL
        practServ = new PracticaService();
        mps = new MontosPracticaService();
        String id_practica;
        if(request.getParameter("idPractica")==null) {
        	id_practica = request.getAttribute("idPractica").toString();
        }else {
        	id_practica = request.getParameter("idPractica");	
        }
		Integer idPractica = Integer.parseInt(id_practica);
        Practica practica = practServ.getPracticaPorID(idPractica);
        request.setAttribute("practica", practica);
		request.getRequestDispatcher("montosPractica.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mps = new MontosPracticaService();
		Integer idPractica;
		HttpSession session = request.getSession();
		if(request.getParameter("idPractica")==null) {
			idPractica = Integer.parseInt(request.getParameter("idPracticaAlta"));
		}else {
			idPractica = Integer.parseInt(request.getParameter("idPractica"));
			
		}
		Integer idMonto;
		LocalDate fecha_desde;
		LocalDate fecha_hasta;
		Double monto;
		String mensaje;
		String respuestaOperacion=null;																									
		String opcion = request.getParameter("operacion");
		
		switch(opcion) {
		case "alta": 
		{
			fecha_desde = LocalDate.parse(request.getParameter("fechaDesde"));
			fecha_hasta = LocalDate.parse(request.getParameter("fechaHasta"));
			monto = Double.parseDouble(request.getParameter("monto"));
			if(mps.validarSuperposicion(idPractica,fecha_desde,fecha_hasta)) 
			{
				respuestaOperacion = "El rango ingresado se superpone con otro existente.";
			}
			else 
			{
				respuestaOperacion = mps.insertarMontoPractica(idPractica, fecha_desde, fecha_hasta, monto);

			}
			break;			
		}
		
		case "actualizar": 
		{
			idMonto = Integer.parseInt(request.getParameter("idMonto"));
			monto = Double.parseDouble(request.getParameter("monto"));
			respuestaOperacion = mps.actualizarMonto(idMonto,monto);
			break;
		}
		
		}
		if ("OK".equals(respuestaOperacion))
		{																										
			mensaje = "Operacion realizada correctamente.";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("idPractica", idPractica);
			response.sendRedirect(request.getContextPath() + "/MontosPractica?idPractica=" + idPractica);
			//this.doGet(request, response);																										
		}																									
		else 
		{																										
			mensaje = respuestaOperacion;
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("idPractica", idPractica);
			response.sendRedirect(request.getContextPath() + "/MontosPractica?idPractica=" + idPractica);
			//this.doGet(request, response);																									
		}	
	}
}

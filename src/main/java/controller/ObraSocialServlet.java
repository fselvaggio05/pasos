package controller;

import java.io.IOException;

import java.util.List;
import entity.ObraSocial;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ObraSocialService;

@WebServlet("/obrassociales")

public class ObraSocialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected ObraSocialService osServ;

	public ObraSocialServlet() {
		this.osServ = new ObraSocialService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ObraSocial> obrassocialesActivas = osServ.getAllActivas();
		List<ObraSocial> obrassocialesInactivas = osServ.getAllInactivas();
		request.setAttribute("tablaActivas", obrassocialesActivas);
		request.setAttribute("tablaInactivas", obrassocialesInactivas);
		request.getRequestDispatcher("abmObraSocial.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
													String mensaje;
													String respuestaOperacion = null;
													String opcion = request.getParameter("operacion");

													
													
													switch (opcion) {
													case "alta": {
														int idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														String nomObraSocial = request.getParameter("nomObraSocial");
														respuestaOperacion = osServ.insertarObraSocial(idObraSocial, nomObraSocial);
														break;
													}
													case "actualizar": {
														int idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														
														String nomObraSocial = request.getParameter("nomObraSocial");
														respuestaOperacion = osServ.actualizarObraSocial(idObraSocial, nomObraSocial);
														break;
													}
													case "eliminar": {
														int idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														respuestaOperacion = osServ.eliminarObraSocial(idObraSocial);
														break;
													}
													case "revivir": {
														int idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														respuestaOperacion = osServ.revivirObraSocial(idObraSocial);
														break;
													}
													}
											
													if (respuestaOperacion == "OK") {
														mensaje = "Operacion realizada correctamente";
														request.setAttribute("mensaje", mensaje);
														this.doGet(request, response);
													} else {
														mensaje = respuestaOperacion;
														request.setAttribute("mensaje", mensaje);
														this.doGet(request, response);
		}
	}
}
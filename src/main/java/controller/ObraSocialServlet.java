package controller;

import java.io.IOException;

import java.util.List;

import entity.Equipo;
import entity.ObraSocial;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EquipoService;
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
		request.setAttribute("tablaObrasSocialesActivas", obrassocialesActivas);
		request.setAttribute("tablaObrasSocialesInactivas", obrassocialesInactivas);
		request.getRequestDispatcher("abmObraSocial.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
													Integer idObraSocial;
													
													String nomObraSocial;
													String mensaje;
													String respuestaOperacion = null;
													String opcion = request.getParameter("operacion");

													switch (opcion) {
													case "alta": {
														
														nomObraSocial = request.getParameter("nomObraSocial");
														respuestaOperacion = osServ.insertarObraSocial(idObraSocial, nomObraSocial);
														break;
													}
													case "actualizar": {
														idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														
														nomObraSocial = request.getParameter("nomObraSocial");
														respuestaOperacion = osServ.actualizarObraSocial(idObraSocial, nomObraSocial);
														break;
													}
													case "eliminar": {
														idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
														respuestaOperacion = osServ.eliminarObraSocial(idObraSocial);
														break;
													}
													case "revivir": {
														idObraSocial = Integer.parseInt(request.getParameter("idObraSocial"));
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
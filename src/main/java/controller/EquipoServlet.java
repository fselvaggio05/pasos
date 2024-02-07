package controller;

import java.io.IOException;

import java.util.List;

import entity.Equipo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EquipoService;

@WebServlet("/equipos")

public class EquipoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected EquipoService eqServ;

	public EquipoServlet() {
		this.eqServ = new EquipoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Equipo> equiposActivos = eqServ.getAllActivos();
		List<Equipo> equiposInactivos = eqServ.getAllInactivos();
		request.setAttribute("tablaEquiposActivos", equiposActivos);
		request.setAttribute("tablaEquiposInactivos", equiposInactivos);
		request.getRequestDispatcher("abmEquipo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idEquipo;
		String tipoEquipo;
		String descEquipo;
		String mensaje;
		String respuestaOperacion = null;
		String opcion = request.getParameter("operacion");

		switch (opcion) {
		case "alta": {
			tipoEquipo = request.getParameter("tipoEquipo");
			descEquipo = request.getParameter("descEquipo");
			respuestaOperacion = eqServ.insertarEquipo(tipoEquipo, descEquipo);
			break;
		}
		case "actualizar": {
			idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
			tipoEquipo = request.getParameter("tipoEquipo");
			descEquipo = request.getParameter("descEquipo");
			respuestaOperacion = eqServ.actualizarEquipo(idEquipo, tipoEquipo, descEquipo);
			break;
		}
		case "eliminar": {
			idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
			respuestaOperacion = eqServ.eliminarEquipo(idEquipo);
			break;
		}
		case "revivir": {
			idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
			respuestaOperacion = eqServ.revivirEquipo(idEquipo);
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
package controller;

import java.io.IOException;
import java.util.List;
import entity.Consultorio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ConsultorioService;

@WebServlet("/consultorios")

public class ConsultorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected ConsultorioService consServ;

	public ConsultorioServlet() {
		this.consServ = new ConsultorioService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Consultorio> consultoriosActivos = consServ.getAllActivos();
		List<Consultorio> consultoriosInactivos = consServ.getAllInactivos();
		request.setAttribute("tablaConsultoriosActivos", consultoriosActivos);
		request.setAttribute("tablaConsultoriosInactivos", consultoriosInactivos);
		request.getRequestDispatcher("abmConsultorio.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer idConsultorio;
		String descConsultorio;
		String mensaje;
		String respuestaOperacion = null;
		String opcion = request.getParameter("operacion");

		switch (opcion) {
							case "alta": {
								descConsultorio = request.getParameter("descConsultorio");
								respuestaOperacion = consServ.insertarConsultorio(descConsultorio);
								break;
							}
							case "actualizar": {
								idConsultorio = Integer.parseInt(request.getParameter("idConsultorio"));
								descConsultorio = request.getParameter("descConsultorio");
								respuestaOperacion = consServ.actualizarConsultorio(idConsultorio, descConsultorio);
								break;
							}
							case "eliminar": {
								idConsultorio = Integer.parseInt(request.getParameter("idConsultorio"));
								respuestaOperacion = consServ.eliminarConsultorio(idConsultorio);
								break;
							}
							case "revivir": {
								idConsultorio = Integer.parseInt(request.getParameter("idConsultorio"));
								respuestaOperacion = consServ.revivirConsultorio(idConsultorio);
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
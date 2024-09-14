package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Paciente;
import entity.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PacienteService;
import service.TurnoService;

@WebServlet("/registroAsistencia")

public class AsistenciaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TurnoService turServ;
	private PacienteService pacServ;
	
	public AsistenciaServlet() {	
		this.turServ = new TurnoService();
		this.pacServ = new PacienteService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registrarAsistencia.jsp").forward(request, response);		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje = null;
		
		switch (operacion) {
							case "buscarPaciente": 
							{
								Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
								Paciente pac = pacServ.buscarPaciente(dni); //busco el paciente para mostrar los datos tambine junto con los turnos registrados
								if(pac!=null) 
								{
									request.setAttribute("paciente", pac);
									List<Turno> turnos = new ArrayList<Turno>();			
									turnos = turServ.buscarTurnosAsignadosPaciente(pac.getDni());
									request.setAttribute("turnos", turnos);
								}
								else {
									mensaje="No existe el Paciente.";
									request.setAttribute("mensaje", mensaje);
								}
								break;			
							}
					
							case "regitrarAsistencia": 
							{
								Integer idTurno = Integer.parseInt(request.getParameter("idTurno"));
								Integer dni = Integer.parseInt(request.getParameter("paciente"));
								Paciente pac = pacServ.buscarPaciente(dni);
								respuestaOperacion = turServ.registrarAsistencia(pac,idTurno);
								if (respuestaOperacion == "OK") 
								{
									mensaje = "Asistencia registrada exitosamente";
									request.setAttribute("mensaje", mensaje);		
								}
								else 
								{	
									if(respuestaOperacion == null)
									{
										mensaje = "El turno a registrar no es del dia de hoy.";
										request.setAttribute("mensaje", mensaje);
									}
									else
									{
										mensaje = respuestaOperacion;
										request.setAttribute("mensaje", mensaje);	
									}
								}
								request.setAttribute("paciente", null);
								request.setAttribute("turnos", null);
								break;
							}
						}	
	this.doGet(request, response);
	}
}

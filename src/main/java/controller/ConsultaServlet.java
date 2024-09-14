package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.PacienteService;
import service.ProfesionalService;
import service.TurnoService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entity.Paciente;
import entity.Profesional;
import entity.Turno;
import entity.Usuario;


@WebServlet("/consultaTurnos")

public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected TurnoService turServ;
	protected PacienteService pacServ;
	protected ProfesionalService profServ;
    
    public ConsultaServlet() {
    	this.turServ = new TurnoService();
    	this.pacServ = new PacienteService();
    	this.profServ = new ProfesionalService();
    }    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usLog = (Usuario)session.getAttribute("usuario");
		List<Turno> turnosPaciente = new ArrayList<Turno>();
		List<Turno> turnosPrescripcion = new ArrayList<Turno>();
		
		if(request.getParameter("idPrescripcion")!=null) {
			Integer idPrescripcion = Integer.parseInt(request.getParameter("idPrescripcion"));
			turnosPrescripcion = turServ.getTurnosPrescripcion(idPrescripcion);
			request.setAttribute("turnos", turnosPrescripcion);
		}
		if(usLog.getTipo_usuario()==3) {
			turnosPaciente = turServ.buscarTurnosAsignadosPaciente(usLog.getDni());
		}
		
		List<Profesional> profesionales = profServ.getAll();
		request.setAttribute("turnosPaciente", turnosPaciente);
		request.setAttribute("profesionales", profesionales);
		request.getRequestDispatcher("consultaTurnos.jsp").forward(request, response);		
	}	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String opcion=null;
		Integer tipoFiltro=null;
		String respuestaOperacion=null;
		String mensaje=null;
		HttpSession session = request.getSession();
		
		opcion = request.getParameter("opcion");
		switch(opcion)
		{
			case "filtroTurno":
			{//este case va a contener otros 3 case con los datos a filtrar. revisar absUsuario
				tipoFiltro = Integer.parseInt(request.getParameter("filtro"));					
				switch(tipoFiltro)
				{
					case 1:
					{
						Integer dni = Integer.parseInt(request.getParameter("dniPaciente"));
						Paciente pac = pacServ.buscarPaciente(dni);
						if(pac!=null) 
						{
							List<Turno> turnos = turServ.buscarTurnosAsignadosPaciente(pac.getDni());
							if(turnos.size()==0) 
							{
								respuestaOperacion="Sin turnos";
							}
							else 
							{
								request.setAttribute("turnos", turnos);								
							}
						}
						else 
						{
							respuestaOperacion="Paciente nulo";
						}
						
						break;
					}
					
					case 2:
					{
						Integer matricula = Integer.parseInt(request.getParameter("profesional"));
						List<Turno> turnos = turServ.buscarTurnosAsignadosProfesional(matricula);
						
						if(turnos.size()==0){
							respuestaOperacion="Sin turnos";
						}												
						request.setAttribute("turnos", turnos);
						break;
					}
					
					case 3:
					{
						LocalDate fecha_turno = LocalDate.parse(request.getParameter("fecha"));
						List<Turno> turnos = turServ.buscarTurnosDelDia(fecha_turno);
						
						if(turnos.size()==0){
							respuestaOperacion="Sin turnos";
						}												
						request.setAttribute("turnos", turnos);	
						break;						
					}
				}
				break;	
			}
			
			case "cancelaTurno":
			{
				Integer idTurno = Integer.parseInt(request.getParameter("idTurno"));
				respuestaOperacion = turServ.cancelaTurno(idTurno);		
			
				if (respuestaOperacion == "OK"){	
					mensaje = "El turno ha sido cancelado.";
					request.setAttribute("mensaje", mensaje);		
				} else{
					mensaje = "No se ha registrado la cancelacion del turno.";					
				}
				break;
			}
		}
		
		switch(respuestaOperacion)
		{
		case "OK":
			mensaje = "El turno ha sido cancelado.";
			break;
		case "Sin turnos":
			mensaje="No se han encontrado turnos registrados.";
			break;
		case "Fallo cancelacion":
			mensaje = "Los turnos pueden cancelarse hasta con un dia de anticipacion.";
			break;
		case "Paciente nulo":
			mensaje="No existe Paciente.";
			break;
		case "":
			break;
		}		
		request.setAttribute("mensaje", mensaje);
		doGet(request, response);
	}
}
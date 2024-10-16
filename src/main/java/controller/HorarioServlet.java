package controller;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import entity.Enumeradores.DiaSemana;
import entity.Horario;
import entity.Practica;
import entity.Profesional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.HorarioService;
import service.PracticaService;
import service.ProfesionalService;

@WebServlet("/horarios")

public class HorarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HorarioService horServ;
	protected ProfesionalService profServ;
	protected PracticaService prServ; // vinculo con las practicas para cargarlas en el alta del horario

	public HorarioServlet() {
		this.horServ = new HorarioService();
		this.profServ = new ProfesionalService();
		this.prServ = new PracticaService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
													List<Horario> horariosActivos = horServ.getAllActivos();
													List<Horario> horariosInactivos = horServ.getAllInactivos();
													List<Profesional> profesionales = profServ.getAll();
													DiaSemana[] diasSemana = DiaSemana.values();
													
													//TODO: TRAER EL CONTENIDO DE LA TABLA PRACTICAS-PROFESIONAL. ACA ESTOY TRAYENDO TODAS
													List<Practica> practicas = prServ.getAllActivas();
													request.setAttribute("tablaHorariosActivos", horariosActivos);
													request.setAttribute("tablaHorariosInactivos", horariosInactivos);
													request.setAttribute("profesionales", profesionales);
													request.setAttribute("practicas", practicas);
													request.setAttribute("diasSemana", diasSemana);
													request.getRequestDispatcher("abmHorario.jsp").forward(request, response);
												}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		String respuestaOperacion = null;
		String mensaje;
		Integer idHorario;
		
		
		switch (operacion) {

							case "buscarProfesional": {
														Integer matricula = Integer.parseInt(request.getParameter("matricula"));
														List<Horario> horarios = horServ.getHorariosActivosProfesional(matricula);
														List<Profesional> profesionales = profServ.getAll();
														request.setAttribute("profesionales", profesionales);
														request.setAttribute("horarios", horarios);
														request.getRequestDispatcher("abmHorario.jsp").forward(request, response);											
														break;
													  }					
							case "alta": {
													Horario hr = new Horario();		
													Practica pr = new Practica();
													
													pr.setId_practica(Integer.parseInt(request.getParameter("id_practica")));
													
													hr.setPractica(pr);
													hr.setHora_desde(LocalTime.parse(request.getParameter("hora_desde")));
													hr.setHora_hasta(LocalTime.parse(request.getParameter("hora_hasta")));
													hr.setDia_semana(Integer.parseInt(request.getParameter("dia_semana")));
													//TODO: ARREGLAR LOS DESPELOTES POR LOS CAMBIOS DE TIPO
																									
													if(horServ.calculaHorario(hr) && horServ.validaConsulorio(hr)) {
														
																				Profesional prof = new Profesional();
																				prof.setMatricula(Integer.parseInt(request.getParameter("matriculaProf")));
																				hr.setProfesional(prof);
																																																		
																				respuestaOperacion = horServ.insertarHorario(hr);													
																				}													
													else {respuestaOperacion = "No es posible agregar el horario ingresado";}
													break;
												}
							case "eliminar": {
												idHorario = Integer.parseInt(request.getParameter("idHorario"));
												//if(horServ.validarHorario(idHorario)) {
													respuestaOperacion = horServ.inactivarHorario(idHorario);
												//}
												//else {
												//	respuestaOperacion="Horario tiene turnos pendientes.";
												//}
												break;
											}
							case "revivir": {
												idHorario = Integer.parseInt(request.getParameter("idHorario"));
												respuestaOperacion = horServ.revivirHorario(idHorario);
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

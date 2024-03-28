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

@WebServlet("/practicas")

public class PracticaServlet extends HttpServlet {
													private static final long serialVersionUID = 1L;
													protected PracticaService prServ;
													protected EquipoService eqServ;

													public PracticaServlet() {
																		    	this.prServ= new PracticaService();
																		    	this.eqServ = new EquipoService();
																		     }

													protected void doGet(HttpServletRequest request, HttpServletResponse response) 
															throws ServletException, IOException {		
																								    List<Practica> practicasActivas = prServ.getAllActivas();
																								    List<Practica> practicasInactivas = prServ.getAllInactivas();		    
																								    List<Equipo> equipos = eqServ.getAll();
																							        request.setAttribute("tablaPracticasActivas", practicasActivas);
																							        request.setAttribute("tablaPracticasInactivas", practicasInactivas);
																							        request.setAttribute("equipos", equipos);
																							        request.getRequestDispatcher("abmPractica.jsp").forward(request,response);
																							       }
	
													protected void doPost(HttpServletRequest request, HttpServletResponse response) 
															throws ServletException, IOException {
																									Integer idPractica;
																									String descPractica;
																									Integer duracion;
																									Integer idEquipo;
																									Double monto;
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
																											monto = Double.parseDouble(request.getParameter("monto"));
																											respuestaOperacion = prServ.insertarPractica(idPractica,descPractica,duracion, idEquipo, monto);
																											break;
																										}
																										
																										case "actualizar":
																										{																							
																											idPractica = Integer.parseInt(request.getParameter("idPractica"));
																											descPractica = request.getParameter("descPractica");
																											duracion = Integer.parseInt(request.getParameter("duracion"));
																											idEquipo = Integer.parseInt(request.getParameter("idEquipo"));	
																											monto = Double.parseDouble(request.getParameter("monto"));
																											respuestaOperacion = prServ.actualizarPractica(idPractica, descPractica, duracion, idEquipo,monto);
																											break;
																										}
																										
																										case "eliminar":
																										{
																											idPractica = Integer.parseInt(request.getParameter("idPractica"));
																										    respuestaOperacion = prServ.eliminarPractica(idPractica);
																										    break;
																										}
																										case "revivir":
																										{
																											idPractica = Integer.parseInt(request.getParameter("idPractica"));
																										    respuestaOperacion = prServ.revivirPractica(idPractica);
																										    break;
																										}
																									}
																									
																									if (respuestaOperacion == "OK")
																									{																										
																										mensaje = "Operacion realizada correctamente.";
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
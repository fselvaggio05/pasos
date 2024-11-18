package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.EquipoService;
import service.MontosPracticaService;
import service.PracticaService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import entity.Equipo;
import entity.Practica;
import entity.Enumeradores.TipoPractica;

@WebServlet("/practicas")

public class PracticaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected PracticaService practServ;
	protected EquipoService eqServ;

	public PracticaServlet() {
		this.practServ = new PracticaService();
		this.eqServ = new EquipoService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Practica> practicasActivas = practServ.getAllActivas();
		List<Practica> practicasInactivas = practServ.getAllInactivas();
		List<Equipo> equipos = eqServ.getAllActivos();
		request.setAttribute("tablaPracticasActivas", practicasActivas);
		request.setAttribute("tablaPracticasInactivas", practicasInactivas);
		request.setAttribute("equipos", equipos);
		request.getRequestDispatcher("abmPractica.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
													Integer idPractica;
													String tipoPract;
													String descPractica;
													Integer duracion;
													Integer idEquipo;
													String mensaje;
													String respuestaOperacion=null;																									
													String opcion = request.getParameter("operacion");
													
													switch(opcion)
													{		
														case "alta":
														{
															idPractica = Integer.parseInt(request.getParameter("idPractica"));
															tipoPract = request.getParameter("tipoPractica");
															TipoPractica tipoPractica = TipoPractica.fromString(tipoPract);
															descPractica = request.getParameter("descPractica");
															duracion = Integer.parseInt(request.getParameter("duracion"));
															if(tipoPractica.getCodigo()==1)
															{
																idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
																if(idEquipo!=0) 
																{
																	respuestaOperacion = practServ.insertarAmbulatoria(idPractica,tipoPractica,descPractica,duracion,idEquipo);
																}
																else 
																{
																	respuestaOperacion = "Por favor, seleccione un equipo.";
																}
															}
															else 
															{
																if(request.getParameter("fechaDesde").isEmpty()||request.getParameter("fechaHasta").isEmpty()||request.getParameter("monto").isEmpty()) 
																	{
																		respuestaOperacion="Por favor, ingrese el monto.";
																	}
																	else 
																	{
																		respuestaOperacion = practServ.insertarDiscapacidad(idPractica,tipoPractica,descPractica,duracion);
																		if("OK".equals(respuestaOperacion)) 
																		{
																			LocalDate fecha_desde = LocalDate.parse(request.getParameter("fechaDesde"));
																			LocalDate fecha_hasta = LocalDate.parse(request.getParameter("fechaHasta"));
																			Double monto = Double.parseDouble(request.getParameter("monto"));
																			MontosPracticaService mps = new MontosPracticaService();
																			respuestaOperacion = mps.insertarMontoPractica(idPractica,fecha_desde,fecha_hasta,monto);
																		}
																	}
															}
															break;
														}
														
														case "actualizar":
														{																							
															idPractica = Integer.parseInt(request.getParameter("idPractica"));
															tipoPract = request.getParameter("tipoPractica");
															TipoPractica tipoPractica = TipoPractica.fromString(tipoPract);
															descPractica = request.getParameter("descPractica");
															duracion = Integer.parseInt(request.getParameter("duracion"));
															
															if(tipoPractica.getCodigo()==1) 
															{
																idEquipo = Integer.parseInt(request.getParameter("idEquipo"));
																if(idEquipo==0) 
																{
																	respuestaOperacion="Por favor, seleccione un Equipo.";
																}
																else 
																{
																	respuestaOperacion = practServ.actualizarAmbulatoria(idPractica, descPractica, duracion, idEquipo);
																}
															}
															else 
															{
																respuestaOperacion = practServ.actualizarDiscapacidad(idPractica,descPractica,duracion);
															}

															break;
														}
														
														case "eliminar":
														{
															idPractica = Integer.parseInt(request.getParameter("idPractica"));
														    respuestaOperacion = practServ.eliminarPractica(idPractica);
														    break;
														}
														case "revivir":
														{
															idPractica = Integer.parseInt(request.getParameter("idPractica"));
														    respuestaOperacion = practServ.revivirPractica(idPractica);
														    break;
														}
													}
													
													if ("OK".equals(respuestaOperacion))
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
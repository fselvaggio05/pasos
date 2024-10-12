<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Enumeradores.TipoUsuario" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="true" %> 
<!DOCTYPE html>
	<html lang="en">
		<head>
    		<meta charset="UTF-8">
    		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		</head>
		<body>
			<% TipoUsuario rolUsuario = (TipoUsuario) session.getAttribute("rol"); 
		    Integer codigoRolUsuario = rolUsuario != null ? rolUsuario.getCodigo() : null; 
		    pageContext.setAttribute("rol", codigoRolUsuario); 
			%>
			<div class="container-fluid">
				<div class="row mt-1">
					<div class="col-3 ">
						<jsp:include page="menu_final.jsp" />
					</div>
					<div class="col-9">
						<div class="container ">
							<h5 class="small fst-italic text-right mt-3">Bienvenido <c:out value="${usuario.apellido}, ${usuario.nombre}"></c:out></h5>
							<h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Turnos	registrados</h4>
							<div class="container mb-5">
								<form action="consultaTurnos" method="post">
									<input type="hidden" value="filtroTurno" name="opcion">
									<div class="row">
									    <div class="col-3">
									        <%-- Define una variable para determinar si el select debe estar deshabilitado --%>
											<c:set var="disableSelect" value="${not empty param.idPrescripcion}" />
									        <select class="form-select" id="filtro" name="filtro" <c:if test="${disableSelect}">disabled</c:if>>
											    <option value="-1" <c:if test="${filtroSeleccionado == '-1'}">selected</c:if>>Buscar turnos por...</option>
											    <c:if test="${rol=='1'}">
											        <option value="1" <c:if test="${filtroSeleccionado == '1'}">selected</c:if>>DNI Paciente</option>
											    </c:if>
											    <c:if test="${rol=='1' or rol=='2'}">
											        <option value="2" <c:if test="${filtroSeleccionado == '2'}">selected</c:if>>Profesional</option>
											    </c:if>
											    <c:if test="${rol=='1' or rol=='2'}">
											        <option value="3" <c:if test="${filtroSeleccionado == '3'}">selected</c:if>>Fecha</option>
											    </c:if>
											    <c:if test="${not empty param.idPrescripcion}">
											        <option value="4" selected>Prescripción</option>
											    </c:if>
											</select>
									    </div>
									    <div class="col-3">
									        <input type="number" class="form-control" name="dniPaciente" id="filtro-1" style="display: none;" value="${not empty param.dniPaciente ? param.dniPaciente : ''}"> 
									        <select class="form-select mb-2" name="profesional" id="filtro-2" style="display: none;">
											    <c:forEach var="prof" items="${profesionales}">
											        <option value="<c:out value='${prof.matricula}' />"
											                <c:if test="${not empty param.profesional and param.profesional == prof.matricula}">
											                    selected
											                </c:if>>
											            <c:out value="${prof.apellido}, ${prof.nombre}" />
											        </option>
											    </c:forEach>
											</select> 
									        <input type="date" class="form-control" name="fecha" id="filtro-3" style="display: none;" value="${not empty param.fecha ? param.fecha : ''}">
									    </div>
									    <div class="col-2">
									        <button type="submit" class="btn btn-success" id="btnBuscar" style="display: none;">Buscar</button>
									    </div>
									</div>
								</form>
							</div>
						</div>
						<div class="mt-4 container">
							<table class="table table-striped my-2" id="tablaTurnos">
								<thead>
									<tr>
										<th scope="col">Profesional</th>
										<th scope="col">Práctica</th>
										<th scope="col">Turno asignado</th>
										<th scope="col">Paciente</th>
										<th scope="col">Consultorio</th>
										<th scope="col">Estado</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${turnosPaciente!=null}">
										<c:forEach var="tur" items="${turnosPaciente}">
											<tr>
												<td>
													<c:out value="${tur.getHorario().getProfesional().getApellido()} , ${tur.getHorario().getProfesional().getNombre()}"></c:out>
												</td>
												<td>
													<c:out value="${tur.getHorario().getPractica().getDescripcion()}"></c:out>
												</td>
												<td>
													<c:out value="${tur.getFecha_t()} ${tur.getHora_t()}"></c:out>
												</td>
												<td>
													<c:out value="${tur.getPaciente().getApellido()} , ${tur.getPaciente().getNombre()}"></c:out>
												</td>
												<td>
													<c:out value="${tur.getConsultorio().getDescripcion()}"></c:out>
												</td>
												<td>
													<c:out value="${tur.getEstado_t()}"></c:out>
												</td>
												<td>
													<a href="#" class="btn btn-success btn-sm" idTurno="${tur.getId_turno()}" profesional="${tur.getHorario().getProfesional().getApellido()},${tur.getHorario().getProfesional().getNombre()}" descPractica="${tur.getHorario().getPractica().getDescripcion()}" datosTurno="${tur.getFecha_t()} ${tur.getHora_t()}" data-bs-toggle="modal" data-bs-target="#cancelaTurno">Cancelar</a>
													<!--REEMPLAZAR ESTE BOTON POR ICONO -->
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
								<c:if test="${turnos!=null}">
									<c:forEach var="tur" items="${turnos}">
										<tr>
											<td>
												<c:out value="${tur.getHorario().getProfesional().getApellido()} , ${tur.getHorario().getProfesional().getNombre()}"></c:out>
											</td>
											<td>
												<c:out value="${tur.getHorario().getPractica().getDescripcion()}"></c:out>
											</td>
											<td>
												<c:out value="${tur.getFecha_t()} ${tur.getHora_t()}"></c:out>
											</td>
											<td>
												<c:out value="${tur.getPaciente().getApellido()} , ${tur.getPaciente().getNombre()}"></c:out>
											</td>
											<td class="text-center">
												<c:out value="${tur.getConsultorio().getId_consultorio()}"></c:out>
											</td>
											<td>
												<c:out value="${tur.estado_t}"></c:out>
											</td>
											<c:if test="${tur.estado_t == 'Asignado'}">
												<td>
													<a href="#" class="btn btn-danger btn-sm" idTurno="${tur.getId_turno()}" profesional="${tur.getHorario().getProfesional().getApellido()},${tur.getHorario().getProfesional().getNombre()}" descPractica="${tur.getHorario().getPractica().getDescripcion()}" datosTurno="${tur.getFecha_t()} ${tur.getHora_t()}" data-bs-toggle="modal" data-bs-target="#cancelaTurno">Cancelar</a>
													<!--REEMPLAZAR ESTE BOTON POR ICONO -->
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--MENSAJE DE OPERACION  -->
	   	<c:if test="${mensaje !=null }">
			<div class="modal fade" id="mensajeOK" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
						    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p class="fs-5 fw-bold">${mensaje}<i class="fa-solid fa-circle-info fa-2xl" style="color: #FFD43B;"></i></p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
						</div>
					</div>
				</div>
			</div>
			<script>new bootstrap.Modal(document.getElementById('mensajeOK')).show();</script> 
		</c:if>
		<!--VENTANA MODAL "CANCELA TURNO" -->
		<div class="modal fade" id="cancelaTurno" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
            	<div class="modal-content">
                	<div class="modal-header">
                    	<h5 class="modal-title" id="exampleModalLabel">Datos del turno</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
					<form action="consultaTurnos" method="post"> 
						<div class="modal-body">                                   
	                    	<input type="hidden" value="cancelaTurno" name="opcion">
	                        <input type="hidden" id="idTurno" name="idTurno">   
	                        <div class="mb-3">
	                        	<label  class="fw-bold form-label col-3">Practica</label>
	                            <label class="form-label" id="descPractica"></label>
	                        </div>
							<div class="mb-3">
	                        	<label for="practica" class="form-label fw-bold col-3">Profesional</label>
	                            <label class="form-label" id="profesional"></label>
	                        </div>
	                        <div class="mb-3">
	                        	<label for="practica" class="form-label fw-bold col-3">Fecha y hora</label>
	                            <label class="form-label" id="datosTurno"></label>
                            </div>                                                                          
                        </div>
	                    <div class="modal-footer">
	                    	<button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Salir</button>
	                        <button type="submit" class="btn btn-danger btn-sm">Cancelar turno</button>
	                    </div>
               		</form>
            	</div>
			</div>
		</div>			
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<script src="js/filtroConsultaTurnos.js"></script>    
	</body>
</html>
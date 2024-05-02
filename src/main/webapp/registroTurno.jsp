<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
    </head>
    <body>
    	<div class="container-fluid">
        	<div class="row mt-1">
            	<div class="col-3 ">
                       <jsp:include page="menu_final.jsp" />
				</div>
				<div class="col-9">
					<div class="container ">
                    	<h5 class="small fst-italic text-right mt-3">Bienvenido <c:out value="${usuario.getApellido()} ${usuario.getNombre() }"></c:out></h5>
                    	<h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registro turno</h4>
                        <div class="row">
                        	<div class="col-6">
                            	<form action="registroTurno" method="post" id="practica">
								    <input type="hidden" value="buscarProfesional" name="operacion">
								    <select class="form-select mb-2" name="practicas" onchange="document.getElementById('practica').submit()">
								        <option value="0">Seleccione una Practica</option>
								        <c:forEach var="pr" items="${practicas}">
								            <c:choose>
								                <c:when test="${pr.id_practica eq practicaSeleccionada}">
								                    <option value="${pr.id_practica}" selected>${pr.descripcion}</option>
								                </c:when>
								                <c:otherwise>
								                    <option value="${pr.id_practica}">${pr.descripcion}</option>
								                </c:otherwise>
								            </c:choose>
								        </c:forEach>
								    </select>
								</form>
                            </div> 
                            <c:if test="${not empty practicaSeleccionada}">
                            	<div class="col-6">
	                            	<form action="registroTurno" method="post" id="profesional">
		                                <input type="hidden" value="buscarTurnos" name="operacion">
		                                <select class="form-select mb-2" name="profesional" onchange="document.getElementById('profesional').submit()">
		                                	<option value="1">Seleccione un Profesional</option>
	                                    	<c:forEach var="prof" items="${profesionales}">
		                                    	<c:choose>
		                                        	<c:when test="${prof.matricula eq profesionalSeleccionado}">
		                                            	<option value="<c:out value=" ${pr.id_practica}"></c:out>" selected>
		                                            				   <c:out value="${pr.descripcion}"></c:out>
		                                                </option> 
		                                                <option value="<c:out value="${prof.matricula}"></c:out>" selected>
		                                                			   <c:out value="${prof.apellido} , ${prof.nombre}"></c:out>
		                                                </option>
		                                            </c:when>
		                                            <c:otherwise>
		                                            	<option value="<c:out value="${prof.matricula}"></c:out>">
		                                            				   <c:out value="${prof.apellido} , ${prof.nombre}"></c:out>
		                                                </option>
		                                            </c:otherwise>
		                                        </c:choose>
		                                    </c:forEach>
		                                </select>
	                                </form>
	                            </div>
                            </c:if>  
                        </div>                           
                        <c:if test="${not empty profesionalSeleccionado}">
                        	<div class="row">
	                        	<div class="col-4 ">
	                                <form action="registroTurno" method="post">
		                                <input type="hidden" value="buscarPaciente" name="operacion">
		                                <c:choose>
		                                	<c:when test="${dniPacienteBuscado!=null}">
		                                    	<input type="number" class="form-control mb-2" name="dniPaciente" value="<c:out value=" ${dniPacienteBuscado}"></c:out>">
		                                    </c:when>
		                                    <c:otherwise>
		                                    	<input type="number" class="form-control mb-2" name="dniPaciente" placeHolder="DNI del Paciente">
		                                    </c:otherwise>
		                                </c:choose>
		                                <button type="submit" class="btn btn-success btn-sm">Seleccionar</button>
	                                </form>
	                            </div>
	                            <div class="col-6 p-5 fw-bold fs-5">
		                        	<c:if test="${paciente!=null}">
		                            	<c:out value="${paciente.apellido} ${paciente.nombre}"></c:out> 
		                            </c:if> 
	                            </div>
	                        </div>
                        </c:if>
                        <c:if test="${not empty turnos}">
                        	<div class="mt-4 container">
								<div class="row justify-content-center">
	                            	<div class="col-12">
	                                	<table class="table table-striped my-2 text-center">
	                                    	<thead>
	                                        	<tr>
	                                            	<th scope="col">Fecha turno</th>
	                                               	<th scope="col">Hora turno</th>    
	                                            </tr>    
	                                        </thead>
	                                        <tbody>
	                                        	<c:forEach var="tur" items="${turnos}">
	                                            	<tr>
	                                                	<td><c:out value="${tur.fecha_t}"></c:out></td>
	                                                    <td><c:out value="${tur.hora_t}"></c:out></td>
	                                                    <td>
	                                                   		<a href='#' class="btn btn-success btn-sm" data-bs-toggle='modal' data-bs-target='#registrarTurno' idTurno="${tur.id_turno}" fecha_turno="${tur.fecha_t}" hora_turno="${tur.hora_t}">Reservar</a>
	                                                   	</td>
	                                                </tr>
	                                            </c:forEach>
	                                        </tbody>
	                                    </table>
	                                </div>
	                            </div>
	                        </div>
                        </c:if>
                        <div class="row justify-content-end">
                        	<div class="col-1">
                            	<a href='menu_final.jsp' class="btn btn-success btn-sm">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--VENTANA MODAL "RESERVAR TURNO" -->
            <div class="modal fade" id="registrarTurno" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            	<div class="modal-dialog">
                	<div class="modal-content">
                    	<div class="modal-header">
                        	<h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar reserva de turno</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="registroTurno" method="post">
                        	<div class="modal-body">
                            	<input type="hidden" value="registroTurno" name="operacion">
                                <input type="hidden" name="idTurno" id="idTurno">
                                <div class="mb-3">
                                	<label class="fw-bold form-label col-6">Apellido y nombre paciente</label>
                                    <c:out value="${paciente.apellido} ${paciente.nombre}"></c:out>
                                </div>
                                <div class="mb-3">
                                	<label class="fw-bold form-label col-6">DNI</label>
                                    <c:out value="${paciente.dni}"></c:out>
                                </div>
								<div class="mb-3">
                                	<label class="fw-bold form-label col-6">Numero de afiliado</label>
                                    <input type="hidden" value="${paciente.nro_afiliado}" name="nroAfiliado">
                                    <c:out value="${paciente.nro_afiliado}"></c:out>
                                </div>
                                <div class="mb-3">
                                	<label class="fw-bold form-label col-6">Fecha turno</label>
                                    <label class="form-label " id="fechaTurno"></label>
                                </div>
                                <div class="mb-3">
                                	<label class="fw-bold form-label col-6">Hora turno</label>
                                    <label class="form-label " id="horaTurno"></label>
                                </div>
                                <div class="modal-footer">
                                	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!--MENSAJE DE OPERACION -->
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
                            	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
                <script>new bootstrap.Modal(document.getElementById('mensajeOK')).show();</script>
            </c:if>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
            <script src="js/funciones_abm.js"></script>
    	</div>
    </body>
</html>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body>
    	<div class="container-fluid">
        	<div class="row mt-1">
            	<div class="col-3 ">
					<jsp:include page="menu_final.jsp" />
            	</div>
            	<div class="col-9">
                	<h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registro de prá	cticas abonadas</h4>
                	<div class="container">
                    	<div class="row  justify-content-evenly">
                        	<form action="registroPagos" method="post">
								<input type="hidden" name="operacion" value="buscarTurnosFacturados">
								<select class="form-select mb-2" name="profesional" onchange="document.getElementById('profesional').submit()">
									<option value="1">Seleccione un Profesional</option>
									<c:forEach var="prof" items="${profesionales}">
										<c:choose>
											<c:when test="${prof.matricula eq profesionalSeleccionado}">
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
								<div class="row justify-content-evenly align-items-center">
									<div class="mb-3 fw-bold">Período facturado</div>
									<div class="col-1">
										<label for="">Fecha desde:</label> <label class="form-label" id="fecha_desde"></label>
									</div>
									<div class="col-3">
										<c:if test="${empty fecha_desde}">
											<input type="date" value="${fecha_desde}" name="fecha_desde" class="form-control col-2 ml-4" required>
										</c:if>
										<c:if test="${not empty fecha_desde}">
											<input type="date" value="${fecha_desde}" name="fecha_desde" class="form-control col-2 ml-4" required>
										</c:if>	
									</div>
									<div class="col-1">
										<label for="">Fecha hasta:</label> <label class="form-label" id="fecha_hasta"></label>
									</div>
									<div class="col-3">
										<c:if test="${empty fecha_hasta}">
											<input type="date" value="${fecha_hasta}" name="fecha_hasta" class="form-control col-2 ml-4" required>
										</c:if>
										<c:if test="${not empty fecha_hasta}">
											<input type="date" value="${fecha_hasta}" name="fecha_hasta" class="form-control col-2 ml-4" required>
										</c:if>	
									</div>
									<div class="col-1">
										<button type="submit" class="btn btn-success">Buscar</button>
									</div>
								</div>
							</form>	                        
                   		</div>
               		</div>            
                  	<hr class="mt-4 border border-success border-1 opacity-50">            
                  	<div class="container">
                   		<form action="registroPagos" method="post">
                   			<table class="table table-striped my-2">
	                    		<thead>
	                            	<tr>
	                                	<th scope="col">  </th>
	                                  	<th scope="col">Fecha turno</th>
	                                  	<th scope="col">Profesional</th>
	                                    <th scope="col">Obra social</th>
	                                    <th scope="col">Práctica</th>                                     
	                                    <th scope="col">Paciente</th> 
	                           		</tr>
	                       		</thead>
                            	<tbody>
                              		<input type="hidden" name="operacion" value="registrarPago">
                                  	<c:forEach var="tur" items="${turnosPorCobrar}">
	                                	<tr>
			                            	<td><input class="form-check-input" type="checkbox" name="seleccionados" value="${tur.getId_turno()}"></td>
	                                     	<td><c:out value="${tur.getFecha_t()}"></c:out></td>
	                                     	<td><c:out value="${tur.getHorario().getProfesional().getApellido()}, ${tur.getHorario().getProfesional().getNombre()}"></c:out></td>
	                                        <td><c:out value="${tur.getPaciente().getObra_social().getNombre_os()}"></c:out></td>
	                                        <td><c:out value="${tur.getHorario().getPractica().getDescripcion()}"></c:out></td>
	                                        <td><c:out value="${tur.getPaciente().getApellido()}, ${tur.getPaciente().getNombre()}"></c:out></td>
                                   		</tr>
                           			</c:forEach>
                       			</tbody>
                   			</table>            
                      		<div class="container">
                        		<div class="row justify-content-end">
                           			<div class="col-3">
                               			<button type="submit" class="btn btn-success">Registrar pago</button>                                
                        			</div>
                           			<div class="col-2">
                               			<button type="button" class="btn btn-success">Cancelar</button>
                           			</div>
                       			</div>
                   			</div>
               			</form>
           			</div>
       			</div>
        	</div>
    	</div>
    	<!-- 			MENSAJE OPERACION 		 -->
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
		                <div></div>
		            </div>
		        </div>
		    </div>
			    <script> 
			        new bootstrap.Modal(document.getElementById('mensajeOK')).show(); 
			    </script> 
		</c:if>
    	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
	</body>
</html>
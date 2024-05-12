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
								    <c:if test="${not empty idPrescripcion}">
								    	<input type="hidden" value="${idPrescripcion}" name="idPrescripcion">
								    </c:if>
								    <select class="form-select mb-2" name="practicas" onchange="document.getElementById('practica').submit()">
									    <option value="0">Seleccione una Practica</option>
									    <c:forEach var="pr" items="${practicas}">
									        <c:choose>
									            <c:when test="${pr.id_practica eq practicaSeleccionada}">
									                <option value="${pr.id_practica}" selected disabled>${pr.descripcion}</option>
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
		                                <c:if test="${not empty idPrescripcion}">
									    	<input type="hidden" value="${idPrescripcion}" name="idPrescripcion">
									    </c:if>
								    	<input type="hidden" name="practicaSeleccionada" value="<%= request.getAttribute("practicaSeleccionada") %>">
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
														    <c:if test="${not empty idPrescripcion}">
															    <a href='#' class="btn btn-success btn-sm btn-reservar" data-bs-toggle='modal' data-bs-target='#registrarTurno' id="botonRegistrarTurno-${tur.id_turno}" idTurno="${tur.id_turno}" idPrescripcion="${idPrescripcion}" fecha_t="${tur.fecha_t}" hora_t="${tur.hora_t}">Reservar</a>
															</c:if>
														    <c:if test="${empty idPrescripcion}">
														        <a href='#' class="btn btn-success btn-sm" data-bs-toggle='modal' data-bs-target='#buscarPaciente' id="botonBuscarPaciente-${tur.id_turno}" idTurno="${tur.id_turno}">Reservar</a>
														    </c:if>
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
                            	<a href='menu_final.jsp' class="btn btn-primary btn-sm">Volver</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--VENTANA MODAL "BUSCAR PACIENTE" -->
            <div class="modal fade" id="buscarPaciente" tabindex="-1" aria-labelledby="modalBuscarPacienteLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="modalBuscarPacienteLabel">Buscar Paciente</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <form id="formBuscarPaciente" action="registroTurno" method="post">
			        	<input type="hidden" value="buscarPaciente" name="operacion">
			        	<c:if test="${not empty idPrescripcion}">
					    	<input type="hidden" value="${idPrescripcion}" name="idPrescripcion">
					    </c:if>
						<input type="hidden" name="idTurnoHidden" id="idTurnoHidden">
   			            <input type="number" name="dniPaciente" placeholder="Buscar Paciente" class="form-control" required>
			            <button type="submit" class="btn btn-primary mt-2">Buscar</button>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
            <script>
			    document.addEventListener("DOMContentLoaded", function() {
			        // Capturamos el botón que abre el modal de buscar paciente
			        var botonReservar = document.querySelector("[data-bs-target='#buscarPaciente']");	
			        
			        // Añadimos un evento de clic al botón de reservar turno
			        botonReservar.addEventListener("click", function() {
			            // Obtenemos el valor de idTurno del botón
			            var idTurno = this.getAttribute("idTurno");	
			            
			            // Asignamos el valor al campo oculto
			            document.getElementById('idTurnoHidden').value = idTurno;
			        });
			
			        // Capturamos el botón que abre el modal de registrar turno
			        var botonRegistrar = document.querySelector("[data-bs-target='#registrarTurno']");
			        
			        // Añadimos un evento de clic al botón de registrar turno
			        if (botonRegistrar) { // Comprobamos si el botón existe
			            botonRegistrar.addEventListener("click", function() {
			                // Obtenemos el valor de idTurno del botón
			                var idTurno = this.getAttribute("idTurno");
			
			                // Asignamos el valor al campo oculto
			                document.getElementById('idTurnoHidden').value = idTurno;
			            });
			        }
			    });
			</script>
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
	                           	<c:if test="${not empty idPrescripcion}">
							    	<input type="hidden" value="${idPrescripcion}" name="idPrescripcion">
							    </c:if>
							    <input type="hidden" name="idTurno" id="idTurno" value="${idTurno}">
	                            <input type="hidden" name="turno" id="turno" value="${turno}">
	                            <input type="hidden" name="idTurnoHidden" id="idTurnoHidden">
	                            <input type="hidden" name="dniPaciente" id="dniPaciente" value="${paciente.dni}">
	                            <div class="mb-3">
								    <label class="fw-bold form-label col-6">Apellido y nombre paciente</label>
								    <c:choose>
								        <c:when test="${not empty paciente}">
								            <c:out value="${paciente.apellido} ${paciente.nombre}"></c:out>
								        </c:when>
								        <c:when test="${not empty prescripcion}">
								            <c:out value="${prescripcion.paciente.apellido} ${prescripcion.paciente.nombre}"></c:out>
								        </c:when>
								    </c:choose>
								</div>
								<div class="mb-3">
								    <label class="fw-bold form-label col-6">DNI</label>
								    <c:choose>
								        <c:when test="${not empty paciente}">
								            <c:out value="${paciente.dni}"></c:out>
								        </c:when>
								        <c:when test="${not empty prescripcion}">
								            <c:out value="${prescripcion.paciente.dni}"></c:out>
								        </c:when>
								    </c:choose>
								</div>
								<div class="mb-3">
								    <label class="fw-bold form-label col-6">Numero de afiliado</label>
								    <c:choose>
								        <c:when test="${not empty paciente}">
								            <input type="hidden" value="${paciente.nro_afiliado}" name="nroAfiliado">
								            <c:out value="${paciente.nro_afiliado}"></c:out>
								        </c:when>
								        <c:when test="${not empty prescripcion}">
								            <input type="hidden" value="${prescripcion.paciente.nro_afiliado}" name="nroAfiliado">
								            <c:out value="${prescripcion.paciente.nro_afiliado}"></c:out>
								        </c:when>
								    </c:choose>
								</div>
	                            <div class="mb-3">
				    <label class="fw-bold form-label col-6">Fecha turno</label>
				    <label class="form-label " id="fechaTurno">
				        <c:choose>
				            <c:when test="${not empty turno}">
				                <c:out value="${turno.fecha_t}"></c:out>
				            </c:when>
				            <c:when test="${not empty requestScope.fechaTurno}">
				                <c:out value="${requestScope.fechaTurno}"></c:out>
				            </c:when>
				        </c:choose>
				    </label>
				</div>
				<div class="mb-3">
				    <label class="fw-bold form-label col-6">Hora turno</label>
				    <label class="form-label " id="horaTurno">
				        <c:choose>
				            <c:when test="${not empty turno}">
				                <c:out value="${turno.hora_t}"></c:out>
				            </c:when>
				            <c:when test="${not empty requestScope.horaTurno}">
				                <c:out value="${requestScope.horaTurno}"></c:out>
				            </c:when>
				        </c:choose>
				    </label>
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
	      <c:if test="${paciente != null}">
		  	<!-- Incluir script para mostrar el modal automáticamente -->
		    <script>new bootstrap.Modal(document.getElementById('registrarTurno')).show();</script>
		  </c:if>
		  <script>
			  document.addEventListener("DOMContentLoaded", function() {
				    // Manejar el botón de reservar turno cuando no hay prescripción
				    var botonBuscarPaciente = document.getElementById("botonBuscarPaciente-${tur.id_turno}");
				    
				    if (botonBuscarPaciente !== null) {
				        botonBuscarPaciente.addEventListener("click", function() {
				            var idTurno = this.getAttribute("idTurno");
				            var idPrescripcion = this.getAttribute("idPrescripcion");
				            var fechaTurno = this.getAttribute("fecha_t");
				            var horaTurno = this.getAttribute("hora_t");
				            
				            // Aquí puedes realizar acciones adicionales si es necesario
				            console.log("Botón Buscar Paciente clickeado. ID de turno:", idTurno, "ID de prescripción:", idPrescripcion, "Fecha de turno:", fechaTurno, "Hora de turno:", horaTurno);
				        });
				    }
	
				    // Manejar el botón de reservar turno cuando hay prescripción
				    var botonRegistrarTurno = document.getElementById("botonRegistrarTurno-${tur.id_turno}");
				    
				    if (botonRegistrarTurno !== null) {
				        botonRegistrarTurno.addEventListener("click", function() {
				            var idTurno = this.getAttribute("idTurno");
				            var idPrescripcion = this.getAttribute("idPrescripcion");
				            var fechaTurno = this.getAttribute("fecha_t");
				            var horaTurno = this.getAttribute("hora_t");
				            
				            // Aquí puedes realizar acciones adicionales si es necesario
				            console.log("Botón Registrar Turno clickeado. ID de turno:", idTurno, "ID de prescripción:", idPrescripcion, "Fecha de turno:", fechaTurno, "Hora de turno:", horaTurno);
				        });
				    }
				});
			</script>
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
<!--             <script src="js/funciones_abm.js"></script> -->
          <script>
	         document.addEventListener("DOMContentLoaded", function() 
        		{
			         var practicaSeleccionada = "${prescripcion}";
			         var selectElement = document.querySelector("select[name='practicas']");
			         if (practicaSeleccionada) 
			         {
			            selectElement.disabled = true;
			         } 
			         else 
			         {
			          	selectElement.disabled = false;
			          }
	            });
			</script>
    	</div>
    </body>
</html>

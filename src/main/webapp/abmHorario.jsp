<%@page import="entity.Horario"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html lang="es">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	    <!-- Bootstrap CSS -->
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
	    
	    <!-- FullCalendar CSS desde CDN -->
	    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css" rel="stylesheet">
	
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	    <link rel="stylesheet" href="css/styles.css">
	    <title>Gestion de Horarios</title>
	
	    <!-- Estilos personalizados para el calendario -->
	    <style>
	        #calendario {
	            width: 100%;
	            max-width: 1100px;
	            margin: 0 auto;
	            height: 100%;
	            min-height: 400px;
	        }
	    </style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row mt-1">
				<div class="col-3">
					<jsp:include page="menu_final.jsp"/>					
				</div>
				<div class="col-9">
					<div class="container">
						<h5 class="small fst-italic text-right mt-3">Bienvenido <c:out value="${usuario.getApellido()} ${usuario.getNombre() }"></c:out></h5>
						<h4 class="text-center my-5 text-decoration-underline fw-bold ">Gestión de horarios</h4>
						<!-- Calendario Semanal -->
                    	<div id="calendario" class="mb-5"></div>
						<div>
							<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado	de Horarios</h4>
							<div class="row justify-content-center mt-3">
								<div class="card text-center">
									<div class="toggle-switch">
                                		<input type="checkbox" id="toggle" class="toggle-switch-checkbox" checked> 
                                		<label for="toggle" class="toggle-switch-label"> 
                                    		<span class="toggle-switch-inner"></span>
                                    		<span class="toggle-switch-text toggle-switch-active">Activos</span>
                                    		<span class="toggle-switch-text toggle-switch-inactive">Inactivos</span>
                                		</label>
                            		</div>
                            		<div class="card-body">
                            			<!-- Tabla de Horarios Activos -->
		                                <table id="tablaActivos" class="table table-striped my-2">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Matrícula</th>
		                                            <th scope="col">Apellido</th>
		                                            <th scope="col">Práctica</th>
		                                            <th scope="col">Día de la Semana</th>
		                                            <th scope="col">Hora Desde</th>
		                                            <th scope="col">Hora Hasta</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unHorario" items="${tablaHorariosActivos}">
		                                            <tr>
		                                                <td><c:out value="${unHorario.getProfesional().getMatricula()}"></c:out></td>
		                                                <td><c:out value="${unHorario.getProfesional().getApellido()}"></c:out></td>
		                                                <td><c:out value="${unHorario.getPractica().getDescripcion()}"></c:out></td>
		                                                <td><c:out value="${unHorario.dia_semana.getNombre()}"></c:out></td>
		                                                <td><c:out value="${unHorario.hora_desde}"></c:out></td>
		                                                <td><c:out value="${unHorario.hora_hasta}"></c:out></td>
		                                            	<td>
		                                                	<a href='#' data-bs-toggle='modal' data-bs-target='#eliminarHorario' idHorario="${unHorario.id_horario}">
    															<i class='bi bi-trash-fill m-1'></i>
															</a> 
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <!-- Tabla de Horarios inactivos -->
		                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Matrícula</th>
		                                            <th scope="col">Apellido</th>
		                                            <th scope="col">Práctica</th>
		                                            <th scope="col">Día de la Semana</th>
		                                            <th scope="col">Hora Desde</th>
		                                            <th scope="col">Hora Hasta</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unHorario" items="${tablaHorariosInactivos}">
		                                            <tr>
		                                                <td><c:out value="${unHorario.getProfesional().getMatricula()}"></c:out></td>
		                                                <td><c:out value="${unHorario.getProfesional().getApellido()}"></c:out></td>
		                                                <td><c:out value="${unHorario.getPractica().getDescripcion()}"></c:out></td>
		                                                <td><c:out value="${unHorario.dia_semana.getNombre()}"></c:out></td>
		                                                <td><c:out value="${unHorario.hora_desde}"></c:out></td>
		                                                <td><c:out value="${unHorario.hora_hasta}"></c:out></td>
		                                            	<td>
		                                                	<a href='#' data-bs-toggle='modal'data-bs-target='#revivirHorario' idHorario="${unHorario.id_horario}">
		                                                		<i class='bi bi-heart-fill m-1'></i>
		                                                	</a> 
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <div class="row justify-content-end">
		                                	<button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaHorario" data-bs-whatever="@mdo">Agregar Horario</button>
		                                	<button type="button" class="btn btn-success col-2 m-1">Cancelar</button>
		                                </div>
                            		</div>
								</div>
							</div>							
						</div>
					</div>
				</div>
			</div>
		</div>
 
<!-- 		VENTANA MODAL "AGREGAR HORARIO" -->
		<div class="modal fade" id="altaHorario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Horario</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="horarios" method="post">
						<div class="modal-body">
							<input type="hidden" value="alta" name="operacion">
							<div class="mb-3">
								<select class="form-select m-1" name="matriculaProf">
									<option value="1">Seleccione un Profesional</option>
									<c:forEach var="unProfesional" items="${profesionales}">
										<option value="<c:out value="${unProfesional.matricula}"></c:out>"><c:out value="${unProfesional.apellido}, ${unProfesional.nombre}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label class="col-6">Práctica</label>
								<select class="col-3 form-select" name="id_practica" id="id_practica">
									<option value="1">Seleccione una Práctica</option>
									<c:forEach var="unaPractica" items="${practicas}">
										<option value="${unaPractica.id_practica}"><c:out value="${unaPractica.descripcion}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label class="col-6">Día de la Semana</label>
								<select class="col-3 form-select" name="dia_semana">
								    <c:forEach var="dia" items="${diasSemana}">
								        <option value="${dia.getDia()}">${dia.name().substring(0, 1).toUpperCase()}${dia.name().substring(1).toLowerCase()}</option>
								    </c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label class="col-6">Hora Desde</label>
								<input type="time" class="form-control col-6" name="hora_desde" id="hora_desde">
							</div>
							<div class="mb-3">
								<label class="col-6">Hora Hasta</label>
								<input type="time" class="form-control col-6" name="hora_hasta" id="hora_hasta">
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
		<!--                     VENTANA MODAL "ELIMINAR HORARIO" -->
		<div class="modal fade" id="eliminarHorario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="horarios" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="eliminar" name="operacion">
					        <input type="hidden" id="idHorario" name="idHorario">
					        <div class="mb-3">
					            <label class="col-6">¿Desea anular el horario?</label>
					            <input type="hidden"  id="idHorario" name="idHorario">
					            <div class="modal-footer">
					                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					                <button type="submit" class="btn btn-primary">Guardar</button>
					            </div>
					        </div>
					    </div>
					</form>
				</div>
			</div>
		</div>
		<!--                     VENTANA MODAL "REVIVIR HORARIO" -->
		<div class="modal fade" id="revivirHorario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="horarios" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="revivir" name="operacion">
					        <input type="hidden" id="idHorario" name="idHorario">
					        <div class="mb-3">
					            <label class="col-6">¿Desea restablecer el horario?</label>
					            <input type="hidden"  id="idHorario" name="idHorario">
					            <div class="modal-footer">
					                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					                <button type="submit" class="btn btn-primary">Guardar</button>
					            </div>
					        </div>
					    </div>
					</form>
				</div>
			</div>
		</div>
		<!-- Bootstrap JS y Popper.js -->
	    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>
	
	    <!-- FullCalendar JS -->
	    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.js"></script>
	
	    <!-- Script para renderizar el calendario -->
	    <script>
	        document.addEventListener('DOMContentLoaded', function() {
	            var calendarEl = document.getElementById('calendario');
	            
	            // Función para asignar un color diferente a cada profesional
	            function getRandomColor() {
	                const letters = '0123456789ABCDEF';
	                let color = '#';
	                for (let i = 0; i < 6; i++) {
	                    color += letters[Math.floor(Math.random() * 16)];
	                }
	                return color;
	            }
	
	            // Obtenemos los horarios desde la lista tablaHorariosActivos
	            var horarios = [
	                <% for (Horario unHorario : (List<Horario>) request.getAttribute("tablaHorariosActivos")) { %>
	                    {
	                        title: '<%= unHorario.getProfesional().getApellido() %> - <%= unHorario.getPractica().getDescripcion() %>',
	                        daysOfWeek:  [<%= unHorario.getDia_semana().getDia() %>],
	                        startTime: '<%= unHorario.getHora_desde() %>',
	                        endTime: '<%= unHorario.getHora_hasta() %>',
	                        backgroundColor: getRandomColor(), // Color asignado aleatoriamente
	                        textColor: 'white'
	                    },
	                <% } %>
	            ];
	
	            var calendar = new FullCalendar.Calendar(calendarEl, {
	                initialView: 'timeGridWeek', // Cambiar a vista semanal
	                locale: 'es', // Idioma en español
	                headerToolbar: {
	                    left: '',
	                    center: '',
	                    right: '' // Quitar los botones de 'prev', 'next', y 'today'
	                },
	                buttonText: {
	                    today: ''
	                },
	                firstDay: 1, // Comenzar con lunes
	                hiddenDays: [0, 6], // Ocultar domingos (0) y sábados (6)
	                slotMinTime: '08:00:00', // Mostrar horarios desde las 8:00 AM
	                slotMaxTime: '20:00:00', // Mostrar horarios hasta las 8:00 PM
	                events: horarios, // Cargar los horarios dinámicamente desde la lista
	                allDaySlot: false, // Deshabilitar el slot de "todo el día"
	                slotDuration: '00:30:00', // Intervalos de media hora
	                dayHeaderFormat: { weekday: 'long' }, // Mostrar nombres completos de los días de la semana
	            });
	
	            calendar.render();
	        });
	    </script>
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
			<!-- CONFIRMACION ELIMINAR TURNOS DE HORARIO -->
			<c:if test="${TurnosPendientes !=null }">
			    <div class="modal fade" id="eliminarTurnos" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			        <div class="modal-dialog">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
			                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			                </div>
			
			                <div class="modal-body">
			                    <p class="fs-5 fw-bold">
			                        El horario tiene turnos pendientes asignados, ¿desea cancelarlos?
			                        <i class="fa-solid fa-circle-info fa-2xl" style="color: #FFD43B;"></i>
			                    </p>
			                </div>			
			                <div class="modal-footer">
			                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
			                    <!-- Formulario para cancelar turnos -->
			                    <form action="horarios" method="post">
			                        <input type="hidden" name="operacion" value="cancelarTurnos">
			                        <input type="hidden" name="idHorario" value="${TurnosPendientes}">
			                        <button type="submit" class="btn btn-danger">Cancelar turnos</button>
			                    </form>
			                </div>
			            </div>
			        </div>
			    </div>			
			    <script> 
			        new bootstrap.Modal(document.getElementById('eliminarTurnos')).show(); 
			    </script> 
			</c:if> 
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<script src="js/funciones_abm.js"></script>  
		<script src="js/toggle.js"></script>
	</body>
</html>
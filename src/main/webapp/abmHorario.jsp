<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body>
		<div class="container-fluid">
			<div class="row mt-1">
				<div class="col-3">
					<jsp:include page="menu_final.jsp"/>					
				</div>
				<div class="col-9">
					<div class="container">
						<div>
							<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado	de Horarios</h4>
							<div class="row justify-content-center">
								<div class="input-group mb-3 col-1">
									<form action="horarios" method="post">
										<div class="row mt-1">
											<div class="col-9">
												<input type="hidden" name="operacion" value="buscarProfesional"> 
													<select class="form-select m-1" name="matricula">
													<option value="1">Seleccione un profesional</option>
													<c:forEach var="prof" items="${profesionales}">
														<option value="<c:out value="${prof.matricula}"></c:out>"><c:out
																value="${prof.apellido}, ${prof.nombre}"></c:out></option>
													</c:forEach>
												</select>
											</div>
											<div class="col-2">
												<button class="btn btn-outline-success" type="submit">Buscar</button>
											</div>
										</div>
									</form>
								</div>
							</div>
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
                            			<!-- Tabla de Horarios activos -->
		                                <table id="tablaActivos" class="table table-striped my-2">
		                                    <thead>
		                                        <tr>
													<th scope="col">Matricula</th>
													<th scope="col">Apellido</th>
													<th scope="col">Practica</th>
													<th scope="col">Dia de la semana</th>
													<th scope="col">Hora desde</th>
													<th scope="col">Hora hasta</th>
													<th scope="col">Operaciones</th>
												</tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="hor" items="${tablaHorariosActivos}">
       												<tr>
														<td><c:out value="${hor.matricula}"></c:out></td>
														<td><c:out value="${hor.apellido_profesional}"></c:out></td>
														<td><c:out value="${hor.desc_practica}"></c:out></td>
														<td><c:out value="${hor.dia_semana}"></c:out></td>
														<td><c:out value="${hor.hora_desde}"></c:out></td>
														<td><c:out value="${hor.hora_hasta}"></c:out></td>
														<td><a href='#' data-bs-toggle='modal' data-bs-target='#eliminarHorario' idHorario="${hor.id_horario}">
																<i class='bi bi-trash-fill m-1'></i>
															</a> 
														</td>
													</tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <!-- Tabla de horarios inactivos -->
		                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
		                                    <thead>
		                                        <tr>
													<th scope="col">Matricula</th>
													<th scope="col">Apellido</th>
													<th scope="col">Practica</th>
													<th scope="col">Dia de la semana</th>
													<th scope="col">Hora desde</th>
													<th scope="col">Hora hasta</th>
													<th scope="col">Operaciones</th>
												</tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="hor" items="${tablaHorariosInactivos}">
       												<tr>
														<td><c:out value="${hor.matricula}"></c:out></td>
														<td><c:out value="${hor.apellido_profesional}"></c:out></td>
														<td><c:out value="${hor.desc_practica}"></c:out></td>
														<td><c:out value="${hor.dia_semana}"></c:out></td>
														<td><c:out value="${hor.hora_desde}"></c:out></td>
														<td><c:out value="${hor.hora_hasta}"></c:out></td>
														<td><a href='#' data-bs-toggle='modal' data-bs-target='#revivirHorario' idHorario="${hor.id_horario}">
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
		<!--MENSAJE DE OPERACION-->
		<div class="bg-info fs-4 text-center">
			<c:out value="${mensaje}"></c:out>
        </div>
		<div class="modal fade" id="altaHorario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Horario</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="horarios"	method="post">
						<input type="hidden" name="operacion" value="altaHorario">
						<div class="modal-body text-start">
							<select class="form-select m-1" name="matriculaProf">
								<option value="1">Seleccione un profesional</option>
								<c:forEach var="prof" items="${profesionales}">
									<option value="<c:out value="${prof.matricula}"></c:out>"><c:out value="${prof.apellido}, ${prof.nombre}"></c:out></option>
								</c:forEach>
							</select>
							<div class="mb-3">
								<label class="col-6">Practica</label>
								<select class="col-3 form-select" name="id_practica" id="id_practica">
									<option value="1">Seleccione una practica</option>
									<c:forEach var="pract" items="${practicas}">															
										<option value="${pract.id_practica}"><c:out value="${pract.descripcion}"></c:out></option>
									</c:forEach>
								</select>
							</div>
							<div class="mb-3">
								<label class="col-6">Dia de la semana</label>
								 <select class="col-3 form-select" id="dia_semana" name="dia_semana">
									<option value="lunes">Lunes</option>
									<option value="martes">Martes</option>
									<option value="miércoles">Miercoles</option>
									<option value="jueves">Jueves</option>
									<option value="viernes">Viernes</option>
									<option value="sábado">Sabado</option>
								</select>
							</div>
							<div class="mb-3">
								<label class="col-6">Hora desde</label> <input type="time"
									class="form-control col-6" name="hora_desde" id="hora_desde">
							</div>
							<div class="mb-3">
								<label class="col-6">Hora hasta</label> <input type="time"
									class="form-control col-6" name="hora_hasta" id="hora_hasta">
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
		<!--VENTANA MODAL "ELIMINAR HORARIO" -->
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
							<div class="mb-3">
                            	<label class="col-6">¿Desea inactivar el horario?</label>
                               	<input type="hidden"  id="idHorario" name="idHorario">                           
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
							<div class="mb-3">
                               <label class="col-6">Desea habilitar el horario seleccionado?</label>                                    
								<!--en este campo voy a guardar el valor del idHorario para enviarlo al servlet -->
					            <input type="hidden"  id="idHorario" name="idHorario">                                    
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
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<script src="js/editarHorario.js"></script>      
		<script src="js/eliminarEquipo.js"></script>
		<script src="js/toggle.js"></script>
	</body>
	
</html>
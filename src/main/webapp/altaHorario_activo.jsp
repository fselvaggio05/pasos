<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" href="/Vistas Proyecto ING/sidebars.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row "></div>

		<div class="row mt-1">
			<div class="col-3 ">

				<jsp:include page="menu_final.jsp" />

			</div>

			<div class="col-9">

				<div class="container ">



					<h4 class="text-center my-4 text-decoration-underline fw-bold ">Listado
						de horarios de los profesionales</h4>

					<div class="row justify-content-center">

						<div class="input-group mb-3 col-1">

							<form action="horarios" method="post">

								<div class="row mt-1">

									<div class="col-9">

										<input type="hidden" name="operacion" value="buscarProfesional"> 
											<select
											class="form-select m-1" name="matricula">
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


						<div class="row mt-2">


						<div class="card text-center">
							<div class="card-header">

								<ul class="nav nav-pills card-header-pills">
									<li class="nav-item"><a href="horarios"
										class="nav-link active">Activos</a></li>
									<li class="nav-item "><a href="horariosIn"
										class="nav-link ">Inactivos</a></li>
								</ul>

							</div>

							<div class="card-body">
								<h4 class="card-title"></h4>
								<!-- aqui va el listado de practicas  -->
								<table class="table table-striped my-2">
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

										<tr>
											<c:forEach var="hor" items="${horarios}">
												<td><c:out value="${hor.matricula}"></c:out></td>
												<td><c:out value="${hor.apellido_profesional}"></c:out></td>
												<td><c:out value="${hor.desc_practica}"></c:out></td>
												<td><c:out value="${hor.dia_semana}"></c:out></td>
												<td><c:out value="${hor.hora_desde}"></c:out></td>
												<td><c:out value="${hor.hora_hasta}"></c:out></td>
												<td><a href='#' data-bs-toggle='modal'
													data-bs-target='#activar' id="${hor.id_horario}"><i
														class='bi bi-trash-fill m-1'></i></a> 
													<a href='#'
													data-bs-toggle='modal' data-bs-target='#editar'
													id="${hor.id_horario}" practica="${hor.desc_practica}" dia_semana="${hor.dia_semana}" hora_desde="${hor.hora_desde}" hora_hasta="${hor.hora_hasta}"><i class="bi bi-pencil-fill"></i></a></td>
													
										</tr>
										</c:forEach>



									</tbody>
								</table>
							</div>

							<div class="container text-end mb-3">
								<button type="button"
									class="btn btn-success col-2 justify-content-end"
									data-bs-toggle="modal" data-bs-target="#altaHorario"
									data-bs-whatever="@mdo">Agregar horario</button>
								<button type="button"
									class="btn btn-success col-2 justify-content-end">Cancelar</button>
							</div>



<!-- 						VENTANA MODAL "AGREGAR" -->

							<div class="modal fade" id="altaHorario" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo
												horario</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>

										<form action="horarios"	method="post">
											<input type="hidden" name="operacion" value="altaHorario">
											<div class="modal-body text-start">


												<select class="form-select m-1" name="matriculaProf">
													<option value="1">Seleccione un profesional</option>
													<c:forEach var="prof" items="${profesionales}">
														<option value="<c:out value="${prof.matricula}"></c:out>"><c:out
																value="${prof.apellido}, ${prof.nombre}"></c:out></option>
													</c:forEach>
												</select>
												<!-- 												<div class="mb-3"> -->
<!-- 													<label class="col-3">Matricula</label> <input type="text" -->
<!-- 														class="form-control col-6" name="matricula" id="matricula"> -->

<!-- 												</div> -->

<!-- 												<div class="mb-3"> -->
<!-- 													<label class="col-3">Apellido</label> <input type="text" -->
<!-- 														class="form-control col-6" name="apellido" id="apellido"> -->
<!-- 												</div> -->

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
														<option value="Lu">Lunes</option>
														<option value="Ma">Martes</option>
														<option value="Mi">Miercoles</option>
														<option value="Ju">Jueves</option>
														<option value="Vi">Viernes</option>
														<option value="Sa">Sabado</option>

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
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Cancelar</button>
													<button type="submit" class="btn btn-primary">Guardar</button>
												</div>
												</div>
										</form>


									</div>

								</div>
							</div>
							
							
<!--                     VENTANA MODAL "EDITAR" -->
                    
                    
			<div class="modal fade" id="editar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Editar horario</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="horarios" method="post">
                            <div class="modal-body text-start">

                                <input type="hidden" value="editar" name="operacion">                                
                                
                           																
									<div class="mb-3">
									
										<input type="hidden" name="id_horario" id="id_horario" readOnly>
										
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
											<option value="Lu">Lunes</option>
											<option value="Ma">Martes</option>
											<option value="Mi">Miercoles</option>
											<option value="Ju">Jueves</option>
											<option value="Vi">Viernes</option>
											<option value="Sa">Sabado</option>
	
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
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Cancelar</button>
										<button type="submit" class="btn btn-primary">Guardar</button>
									</div>
									 

                            </div>
                        </form>
                    </div>
                 </div>
              </div>
              
							
							
							  
              <!--                     VENTANA MODAL "ELIMINAR" -->
                    
                    
			<div class="modal fade" id="activar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="horarios" method="post">
                            <div class="modal-body">

								<input type="hidden" value="activar" name="operacion">
                               

								<div class="mb-3">
                                    <label class="col-6">Desea inactivar el horario?</label>
                                    <input type="hidden"  id="idEnviado" name="idEnviado">
                                                                        
                           
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
            



						</div>





						<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
	
	<script src="js/activar_desactivar.js"></script>
	<script src="js/editarHorario.js"></script>
	
</body>

</html>
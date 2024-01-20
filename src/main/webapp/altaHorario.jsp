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



					<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado
						de horarios de los profesionales</h4>

					<div class="row justify-content-center">

						<div class="input-group mb-5 col-1">

							<!-- AGREGAR LIST CON LOS PROFESIONALES  -->
							<select class="form-select col-2 m-1">
								<option value="1">Seleccione un profesional</option>
								<c:forEach var="prof" items="${profesionales}" >
										<option value="<c:out value="${prof.matricula}"></c:out>"><c:out value="${prof.apellido}, ${prof.nombre}"></c:out></option>
								</c:forEach>
							</select>
							
							<div class="input-group-append">
								<button class="btn btn-outline-success" type="button">Buscar</button>
							</div>
						</div>

					</div>


						<div class="row justify-content-center mt-2">


							<div class="card text-center">
								<div class="card-header">

									<ul class="nav nav-pills card-header-pills">
										<li class="nav-item"><a href="horarios"
											class="nav-link active">Activos</a></li>
										<li class="nav-item "><a href=""
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
<!-- 											 este campo esta en la tabla profesional_practicas -->
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
												<td></td>		
												<td><c:out value="${hor.dia_semana}"></c:out></td>
												<td><c:out value="${hor.hora_desde}"></c:out></td>
												<td><c:out value="${hor.hora_hasta}"></c:out></td>
												<td><a href='#' data-bs-toggle='modal'
													data-bs-target='#delHorario' idHorario='"++"'><i
														class='bi bi-trash-fill m-1'></i></a> <a href='#'
													data-bs-toggle='modal' data-bs-target='#updPractica'
													idHOrario='"++"'><i class="bi bi-pencil-fill"></i></a></td>
										    </tr>
											</c:forEach>
											

											
										</tbody>
									</table>
								</div>


								<div class="container text-end">
									<button type="button"
										class="btn btn-success col-2 justify-content-end"
										data-bs-toggle="modal" data-bs-target="#altaPractica"
										data-bs-whatever="@mdo">Agregar horario</button>
									<button type="button"
										class="btn btn-success col-2 justify-content-end">Cancelar</button>
								</div>





								<div class="modal fade" id="altaPractica" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo
													horario</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>

											<form
												action="formulario que realiza la toma y el guardado de los datos"
												method="post">
												<div class="modal-body">

													<div class="mb-3">
														<label class="col-3">Matricula</label> <input type="text"
															class="form-control col-6" name="matricula"
															id="matricula">

													</div>

													<div class="mb-3">
														<label class="col-3">Apellido</label> <input type="text"
															class="form-control col-6" name="matricula"
															id="matricula">
													</div>

													<div class="mb-3">
														<label class="col-6">Practica</label> <select
															class="col-3 form-select" id="practica" onchange="">
															<option value="1">Magnetoterapia</option>
															<option value="1">Ondas</option>

														</select>
													</div>

													<div class="mb-3">
														<label class="col-6">Dia de la semana</label> <select
															class="col-3 form-select" id="genero" onchange="">
															<option value="1">Lunes</option>
															<option value="1">Martes</option>
															<option value="1">Miercoles</option>
															<option value="1">Jueves</option>
															<option value="1">Viernes</option>
															<option value="1">Sabado</option>

														</select>
													</div>

													<div class="mb-3">
														<label class="col-6">Hora desde</label> <input type="time"
															class="form-control col-6" name="hora_desde"
															id="hora_desde">
													</div>

													<div class="mb-3">
														<label class="col-6">Hora hasta</label> <input type="time"
															class="form-control col-6" name="hora_desde"
															id="hora_desde">
													</div>

													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Cancelar</button>
														<button type="submit" class="btn btn-primary">Guardar</button>
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
</body>

</html>





</div>





</div>



<div class="row "></div>
</div>




<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
</body>

</html>
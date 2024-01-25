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

										<input type="hidden" name="operacion"
											value="buscarProfesional"> 
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


						<div class="row justify-content-center mt-2">


							<div class="card text-center">
								<div class="card-header">

									<ul class="nav nav-pills card-header-pills">
										<li class="nav-item"><a href="horarios"
											class="nav-link">Activos</a></li>
										<li class="nav-item "><a href="horariosIn"
											class="nav-link active">Inactivos</a></li>
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
											<th scope="col">Fecha baja</th>											
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
												<td><c:out value="${hor.fecha_baja}"></c:out></td>
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

</body>

</html>

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
	
	<script src="js/buscarProfesionalPorMatricula.js">

</script>
</body>

</html>
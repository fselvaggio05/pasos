<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page session="true"%>

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
            <jsp:include page="menu_final.jsp" />
        </div>
        <div class="col-9">
            <div class="container">
                <h5 class="small fst-italic text-right mt-3">Bienvenido @USUARIO</h5>
                <h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registrar prescripcion</h4>
                <div class="mt-4 container">
                    <!-- Radio buttons para seleccionar entre "Ambulatorias" y "Discapacidad" -->
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="tipoPrescripcion" id="radioAmbulatorias" value="ambulatorias" checked>
                        <label class="form-check-label" for="radioAmbulatorias">Ambulatorias</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="tipoPrescripcion" id="radioDiscapacidad" value="discapacidad">
                        <label class="form-check-label" for="radioDiscapacidad">Discapacidad</label>
                    </div>
                </div>
                <form method="post" action="prescripcion" class="my-4">
				    <div class="input-group">
				        <input type="text" class="form-control" placeholder="Ingrese el DNI del paciente" name="dniPaciente" aria-describedby="button-addon2" value="${not empty param.dniPaciente ? param.dniPaciente : ''}">
				        <button class="btn btn-primary ms-2" type="submit" name="operacion" value="buscarPaciente" id="button-addon2">Buscar</button>
				    </div>
				</form>
                <!-- Tabla de prescripciones ambulatorias -->
                <div id="tablaAmbulatorias" class="mt-4 container">
                    <table class="table table-striped my-2">
                        <thead>
                            <tr>
                                <th scope="col">Fecha Prescripcion</th>
                                <th scope="col">Paciente</th>
                                <th scope="col">Nro. Afiliado</th>
                                <th scope="col">Practica</th>
                                <th scope="col">Total Sesiones</th>
                                <th scope="col">Sesiones Pendientes</th>
                                <th scope="col">Fecha Fin Prescripcion</th>
                                <th scope="col"> Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prescripcion" items="${prescripcionesAmbulatorias}">
                                <tr>
								    <td><c:out value="${prescripcion.fecha_prescripcion}"/></td>
								    <td><c:out value="${prescripcion.paciente.nombre} ${prescripcion.paciente.apellido}"/></td>
								    <td><c:out value="${prescripcion.paciente.nro_afiliado}"/></td>
								    <td><c:out value="${prescripcion.practica.descripcion}"/></td>
								    <td><c:out value="${prescripcion.cant_sesiones}"/></td>
								    <td><c:out value="${prescripcion.cant_sesiones - prescripcion.sesiones_asistidas}"/></td>
								    <td><c:out value="${prescripcion.fecha_baja_prescipcion}"/></td>
								    <td>
									    <div class="d-flex justify-content-center gap-4">
									        <a href="consultaTurnos?idPrescripcion=${prescripcion.id_prescripcion}">
									            <i class="bi bi-calendar-check m-1"></i>
									        </a>
									        <a href="registroTurno?idPrescripcion=${prescripcion.id_prescripcion}">
									            <i class="bi bi-calendar-plus-fill m-1"></i>
									        </a>
									        <a href="#" data-bs-toggle="modal" data-bs-target="#anularPrescripcion" idPrescripcion="${prescripcion.id_prescripcion}">
									            <i class="bi bi-trash-fill m-1"></i>
									        </a> 
									    </div>
									</td>
								</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- Tabla de prescripciones de discapacidad -->
                <div id="tablaDiscapacidad" class="mt-4 container" style="display: none;">
                    <table class="table table-striped my-2">
                        <thead>
                            <tr>
                                <th scope="col">Fecha Prescripcion</th>
                                <th scope="col">Paciente</th>
                                <th scope="col">Nro. Afiliado</th>
                                <th scope="col">Practica</th>
                                <th scope="col">Total Sesiones</th>
                                <th scope="col">Sesiones Pendientes</th>
                                <th scope="col">Fecha Fin Prescripcion</th>
                                <th scope="col"> Operaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="prescripcion" items="${prescripcionesDiscapacidad}">
                                <tr>
								    <td><c:out value="${prescripcion.fecha_prescripcion}"/></td>
								    <td><c:out value="${prescripcion.paciente.nombre} ${prescripcion.paciente.apellido}"/></td>
								    <td><c:out value="${prescripcion.paciente.nro_afiliado}"/></td>
								    <td><c:out value="${prescripcion.practica.descripcion}"/></td>
								    <td><c:out value="${prescripcion.cant_sesiones}"/></td>
								    <td><c:out value="${prescripcion.cant_sesiones - prescripcion.sesiones_asistidas}"/></td>
								    <td><c:out value="${prescripcion.fecha_baja_prescipcion}"/></td>
								    <td>
									    <div class="d-flex justify-content-center gap-4">
									        <a href='#' data-bs-toggle='modal' data-bs-target='#anularPrescripcion' idPrescripcion="${prescripcion.id_prescripcion}">
									            <i class='bi bi-trash-fill m-1'></i>
									        </a> 
									        <a href="registroTurno?idPrescripcion=${prescripcion.id_prescripcion}">
									            <i class="bi bi-calendar-plus-fill m-1"></i>
									        </a>
									    </div>
									</td>
								</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <button type="button" class="btn btn-success btn-sm me-2" data-bs-toggle="modal" data-bs-target="#registroPrescripcion"  data-bs-whatever="@mdo">Registrar prescripción</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="js/funciones_abm.js"></script>
<script src="js/radio_button_abm_prescripcion.js"></script>
</body>
</html>

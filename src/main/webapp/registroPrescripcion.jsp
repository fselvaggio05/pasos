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
                <h5 class="small fst-italic text-right mt-3">Bienvenido <c:out value="${usuario.getApellido()} ${usuario.getNombre()}"></c:out></h5>
                <h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registrar prescripcion</h4>
                <!-- Formulario para buscar paciente -->
                <form method="post" action="prescripcion" class="my-4">
				    <div class="input-group">
				        <input type="hidden" value="buscarPaciente" name="operacion">
				        <input type="text" class="form-control" placeholder="Ingrese el DNI del paciente" name="dniPaciente" aria-describedby="button-addon2" value="${not empty param.dniPaciente ? param.dniPaciente : ''}">
				        <button class="btn btn-primary ms-2" type="submit" name="buscarPaciente" value="Buscar" id="button-addon2">Buscar</button>
				        <button class="btn btn-success btn-sm ms-2" type="submit" name="agregarPrescripcion" value="AgregarPrescripcion" onclick="return validarDNI()">Agregar Prescripción</button>
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
                            <!-- Aquí se mostrarán las prescripciones -->
                            <c:forEach var="prescripcion" items="${prescripciones}">
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
                                            <c:if test="${empty prescripcion.fecha_baja_prescipcion}">
                                            	<a href="registroTurno?idPrescripcion=${prescripcion.id_prescripcion}">
	                                                <i class="bi bi-calendar-plus-fill m-1"></i>
	                                            </a>
                                            </c:if>
                                            <c:if test="${empty prescripcion.fecha_baja_prescipcion}">
					                            <a href="#" data-bs-toggle="modal" data-bs-target="#anularPrescripcion" idPrescripcion="${prescripcion.id_prescripcion}" paciente="${prescripcion.paciente.nombre} ${prescripcion.paciente.apellido}" practica="${prescripcion.practica.descripcion}" sesiones="${prescripcion.cant_sesiones}">
					                                <i class="bi bi-trash-fill m-1"></i>
					                            </a>
					                        </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- MODAL ALTA PRESCRIPCION -->
<c:if test="${accion !=null }">
    <div class="modal fade" id="altaPrescripcion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Registro de Prescripción</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="prescripcion" method="post">
                    <div class="modal-body">
                        <input type="hidden" value="alta" name="operacion">
                        <div class="mb-3">
                            <label for="dniPaciente" class="form-label">DNI del Paciente</label>
                            <input type="text" class="form-control" id="dniPaciente" name="dniPaciente" value="${paciente.dni}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="nombrePaciente" class="form-label">Nombre del Paciente</label>
                            <input type="text" class="form-control" id="nombrePaciente" name="nombrePaciente" value="${paciente.nombre} ${paciente.apellido}" readonly>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="mb-3">
                                    <label for="obraSocial" class="form-label">Obra Social</label>
                                    <input type="text" class="form-control" id="obraSocial" name="obraSocial" value="${paciente.obra_social.nombre_os}" readonly>
                                </div>
                            </div>
                            <div class="col">
                                <div class="mb-3">
                                    <label for="nroAfiliado" class="form-label">Número Afiliado</label>
                                    <input type="text" class="form-control" id="nroAfiliado" name="nroAfiliado" value="${paciente.nro_afiliado}" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="fechaPrescripcion" class="form-label">Fecha de Prescripción</label>
                            <input type="date" class="form-control" id="fechaPrescripcion" name="fechaPrescripcion" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Práctica</label>
                            <select class="form-select" name="id_practica" id="id_practica">
                                <option value="1">Seleccione una Práctica</option>
                                <c:forEach var="unaPractica" items="${practicas}">
                                    <option value="${unaPractica.id_practica}"><c:out value="${unaPractica.descripcion}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="cantSesiones" class="form-label">Cantidad de Sesiones</label>
                            <input type="number" class="form-control" id="cantSesiones" name="cantSesiones" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        new bootstrap.Modal(document.getElementById('altaPrescripcion')).show();
    </script>
</c:if>


<!-- VENTANA MODAL "ANULAR PRESCRIPCION" -->
<div class="modal fade" id="anularPrescripcion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="prescripcion" method="post">
                <div class="modal-body">
                    <input type="hidden" id="idPrescripcion" name="idPrescripcion">
                    <input type="hidden" value="anular" name="operacion">
                    <p class="fs-5">¿Está seguro que desea anular la prescripción de <span id="sesionesPrescripcion" class="fw-bold"></span> sesiones de <span id="practicaPrescripcion" class="fw-bold"></span> del Paciente <span id="pacientePrescripcion" class="fw-bold"></span>?</p>
                    <div class="fs-4 text-danger" id="paciente"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </div>
            </form>
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
<script src="js/funciones_abm.js"></script>
</body>
</html>
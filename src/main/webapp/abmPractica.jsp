<%@page import="entity.Practica"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
							<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado	de Prácticas Profesionales</h4>
							<div class="row justify-content-center mt-3">
								<div class="card text-center">
									<div class="toggle-switch">
                                		<input type="checkbox" id="toggle" class="toggle-switch-checkbox" checked> 
                                		<label for="toggle" class="toggle-switch-label"> 
                                    		<span class="toggle-switch-inner"></span>
                                    		<span class="toggle-switch-text toggle-switch-active">Activas</span>
                                    		<span class="toggle-switch-text toggle-switch-inactive">Inactivas</span>
                                		</label>
                            		</div>
                            		<div class="card-body">
                            			<!-- Tabla de Practicas activos -->
		                                <table id="tablaActivos" class="table table-striped my-2">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Código de Práctica</th>
		                                            <th scope="col">Tipo de Práctica</th>
		                                            <th scope="col">Descripción</th>
		                                            <th scope="col">Equipo</th>
		                                            <th scope="col">Duración</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unaPractica" items="${tablaPracticasActivas}">
		                                            <tr>
		                                                <td><c:out value="${unaPractica.id_practica}"/></td>
		                                                <td><c:out value="${unaPractica.tipo_practica}"/></td>
		                                                <td><c:out value="${unaPractica.descripcion}"/></td>
		                                                <td><c:out value="${unaPractica.getEquipo().getDescripcion()}"/></td>
		                                                <td><c:out value="${unaPractica.duracion}"/></td>
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal' data-bs-target='#eliminarPractica' idPractica="${unaPractica.id_practica}" descPractica="${unaPractica.descripcion}">
																<i class='bi bi-trash-fill m-1'></i>
															</a> 
															<a href="#" data-bs-toggle="modal" data-bs-target="#actualizarPractica" idPractica="${unaPractica.id_practica}" descPractica="${unaPractica.descripcion}" duracion="${unaPractica.duracion}" equipo="${unaPractica.getEquipo().getId_equipo()}" tipoPractica="${unaPractica.tipo_practica}">
															    <i class="bi bi-pencil-fill"></i>
															</a>
															<!-- Botón para consultar montos solo para prácticas del tipo Discapacidad -->
														    <c:if test="${unaPractica.tipo_practica eq 'DISCAPACIDAD'}">
														        <a href="/Pasos/MontosPractica?idPractica=${unaPractica.id_practica}">
														            <i class="bi bi-cash m-1"></i>
														        </a>
														    </c:if>
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <!-- Tabla de Practicas inactivos -->
		                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Código de Práctica</th>
		                                            <th scope="col">Tipo de Práctica</th>
		                                            <th scope="col">Descripción</th>
		                                            <th scope="col">Equipo</th>
		                                            <th scope="col">Duración</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unaPractica" items="${tablaPracticasInactivas}">
		                                            <tr>
		                                                <td><c:out value="${unaPractica.id_practica}"/></td>
		                                                <td><c:out value="${unaPractica.tipo_practica}"/></td>
		                                                <td><c:out value="${unaPractica.descripcion}"/></td>
		                                                <td><c:out value="${unaPractica.getEquipo().getDescripcion()}"/></td>
		                                                <td><c:out value="${unaPractica.duracion}"/></td>
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal'data-bs-target='#revivirPractica' idPractica="${unaPractica.id_practica}" descPractica="${unaPractica.descripcion}">
		                                                		<i class='bi bi-heart-fill m-1'></i>
		                                                	</a> 
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <div class="row justify-content-end">
		                                	<button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaPractica" data-bs-whatever="@mdo">Agregar Practica</button>
		                                </div>
                            		</div>
								</div>
							</div>							
						</div>
					</div>
				</div>
			</div>
		</div> 
		<!--VENTANA MODAL "AGREGAR PRACTICA" -->
		<div class="modal fade" id="altaPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h1 class="modal-title fs-5" id="exampleModalLabel">Nueva Práctica</h1>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <form action="practicas" method="post" onsubmit="mensaje()">
		                <div class="modal-body">
		                    <input type="hidden" value="alta" name="operacion">
		                    <div class="mb-3">
		                        <label>Tipo de Práctica:</label>
		                        <div class="form-check form-check-inline">
		                            <input class="form-check-input" type="radio" name="tipoPractica" id="ambulatoria" value="Ambulatoria" checked>
		                            <label class="form-check-label" for="ambulatoria">Ambulatoria</label>
		                        </div>
		                        <div class="form-check form-check-inline">
		                            <input class="form-check-input" type="radio" name="tipoPractica" id="discapacidad" value="Discapacidad">
		                            <label class="form-check-label" for="discapacidad">Discapacidad</label>
		                        </div>
		                    </div>
		                    <div class="mb-3">
		                        <label>Código de Práctica:</label>
		                        <input type="text" class="form-control" name="idPractica">
		                    </div>
		                    <div class="mb-3">
		                        <label>Descripción práctica:</label>
		                        <input type="text" class="form-control" name="descPractica">
		                    </div>
		                    <div class="mb-3">
		                        <label>Duración:</label>
		                        <input type="number" class="form-control" name="duracion">
		                    </div>
		                    <div class="mb-3" id="equipoField">
		                        <label>Equipo:</label>
		                        <select class="form-select" name="idEquipo"> 
		                            <option value="1">Seleccione un Equipo</option>
		                            <c:forEach var="equip" items="${equipos}">
		                                <option value="<c:out value="${equip.id_equipo}"></c:out>"><c:out value="${equip.descripcion}"></c:out></option>
		                            </c:forEach>
		                        </select>                               
		                    </div>
		                    <div class="mb-3" id="fechaDesdeField" style="display: none;">
		                        <label>Fecha Desde:</label>
		                        <input type="date" class="form-control" name="fechaDesde">
		                    </div>
		                    <div class="mb-3" id="fechaHastaField" style="display: none;">
		                        <label>Fecha Hasta:</label>
		                        <input type="date" class="form-control" name="fechaHasta">
		                    </div>
		                    <div class="mb-3" id="montoField" style="display: none;">
		                        <label>Monto:</label>
		                        <input type="number" class="form-control" name="monto">
		                    </div>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		                    <button type="submit" class="btn btn-primary">Guardar</button>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
		<!--VENTANA MODAL "EDITAR PRACTICA" -->                    
		<div class="modal fade" id="actualizarPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Práctica</h1>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <form action="practicas" method="post">
		                <div class="modal-body">
		                    <input type="hidden" value="actualizar" name="operacion">
		                    <div class="mb-3">
		                        <label>Tipo de Práctica:</label>
		                        <div class="form-check form-check-inline">
		                            <input class="form-check-input" type="radio" name="tipoPractica" id="ambulatoria" value="Ambulatoria">
		                            <label class="form-check-label" for="ambulatoria">Ambulatoria</label>
		                        </div>
		                        <div class="form-check form-check-inline">
		                            <input class="form-check-input" type="radio" name="tipoPractica" id="discapacidad" value="Discapacidad">
		                            <label class="form-check-label" for="discapacidad">Discapacidad</label>
		                        </div>
		                    </div>
		                    <div class="mb-3">
		                        <label>Codigo Práctica:</label>
		                        <input type="text" class="form-control" id="idPractica" name="idPractica" readOnly>
		                    </div>
		                    <div class="mb-3">
		                        <label>Descripción práctica:</label>
		                        <input type="text" class="form-control" id="descPractica" name="descPractica">
		                    </div>
		                    <div class="mb-3">
		                        <label>Duración:</label>
		                        <input type="number" class="form-control" id="duracion" name="duracion">
		                    </div>
		                    <div class="mb-3" id="equipoField" style="display: none;">
		                        <label>Equipo:</label>
		                        <select class="form-select" id="equipo" name="idEquipo">
		                            <option value="">Seleccione un Equipo</option>
		                            <c:forEach var="equip" items="${equipos}">
		                                <option value="<c:out value="${equip.id_equipo}"></c:out>"><c:out value="${equip.descripcion}"></c:out></option>
		                            </c:forEach>
		                        </select>                                                 
		                    </div>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
		                    <button type="submit" class="btn btn-primary">Guardar</button>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
		<!--VENTANA MODAL "ELIMINAR PRACTICA" -->                    
		<div class="modal fade" id="eliminarPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <form action="practicas" method="post">
	                    <div class="modal-body">
							<input type="hidden" value="eliminar" name="operacion">
							<div class="mb-3">
	                            <label class="col-6">¿Desea eliminar la Práctica?</label>
	                            <input type="hidden"  id="idPractica" name="idPractica">
	                            <div  class="fs-4 text-danger" id="descPractica" name="descPractica" ></div>
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
		<!--VENTANA MODAL "REVIVIR PRACTICA" -->
		<div class="modal fade" id="revivirPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="practicas" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="revivir" name="operacion">
					        <input type="hidden" id="idPractica" name="idPractica">
					        <div class="mb-3">
					            <label class="col-6">¿Desea reactivar la Práctica?</label>
					            <input type="hidden"  id="idPractica" name="idPractica">
								<div  class="fs-4 text-danger" id="descPractica" name="descPractica"></div>
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
		<script src="js/toggle.js"></script>
		<script>
		    document.addEventListener('DOMContentLoaded', function () {
		        var ambulatoriaRadio = document.getElementById('ambulatoria');
		        var discapacidadRadio = document.getElementById('discapacidad');
		        var equipoField = document.getElementById('equipoField');
		        var fechaDesdeField = document.getElementById('fechaDesdeField');
		        var fechaHastaField = document.getElementById('fechaHastaField');
		        var montoField = document.getElementById('montoField');
		
		        ambulatoriaRadio.addEventListener('change', function () {
		            equipoField.style.display = 'block';
		            fechaDesdeField.style.display = 'none';
		            fechaHastaField.style.display = 'none';
		            montoField.style.display = 'none';
		        });
		
		        discapacidadRadio.addEventListener('change', function () {
		            equipoField.style.display = 'none';
		            fechaDesdeField.style.display = 'block';
		            fechaHastaField.style.display = 'block';
		            montoField.style.display = 'block';
		        });
		    });
		</script>
	</body>
</html>
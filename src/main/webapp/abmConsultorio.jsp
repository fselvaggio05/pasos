<%@page import="entity.Consultorio"%>
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
							<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado	de Consultorios</h4>
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
                            			<!-- Tabla de Consultorios activos -->
		                                <table id="tablaActivos" class="table table-striped my-2">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">C�digo de Consultorio</th>
		                                            <th scope="col">Descripci�n</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unConsultorio" items="${tablaConsultoriosActivos}">
		                                            <tr>
		                                                <td><c:out value="${unConsultorio.id_consultorio}"/></td>
		                                                <td><c:out value="${unConsultorio.descripcion}"/></td>
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal' data-bs-target='#eliminarConsultorio' idConsultorio="${unConsultorio.id_consultorio}" descConsultorio="${unConsultorio.descripcion}">
    															<i class='bi bi-trash-fill m-1'></i>
															</a> 
															<a href='#' data-bs-toggle='modal' data-bs-target='#actualizarConsultorio' idConsultorio="${unConsultorio.id_consultorio}" descConsultorio="${unConsultorio.descripcion}">
																<i class="bi bi-pencil-fill"></i>
															</a>
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <!-- Tabla de consultorios inactivos -->
		                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">C�digo de Consultorio</th>
		                                            <th scope="col">Descripci�n</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unConsultorio" items="${tablaConsultoriosInactivos}">
		                                            <tr>
		                                                <td><c:out value="${unConsultorio.id_consultorio}"/></td>
		                                                <td><c:out value="${unConsultorio.descripcion}"/></td>
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal'data-bs-target='#revivirConsultorio' idConsultorio="${unConsultorio.id_consultorio}" descConsultorio="${unConsultorio.descripcion}">
		                                                		<i class='bi bi-heart-fill m-1'></i>
		                                                	</a> 
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <div class="row justify-content-end">
		                                	<button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaConsultorio" data-bs-whatever="@mdo">Agregar Consultorio</button>
		                                </div>
                            		</div>
								</div>
							</div>							
						</div>
					</div>
				</div>
			</div>
		</div>
 
<!-- 		VENTANA MODAL "AGREGAR CONSULTORIO" -->
		<div class="modal fade" id="altaConsultorio" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Consultorio</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="consultorios" method="post">
						<div class="modal-body">
							<input type="hidden" value="alta" name="operacion">
							<div class="mb-3">
								<label class="col-6">Descripci�n consultorio:</label>
	                            <input type="text" class="form-control col-6" name="descConsultorio" required>
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
		<!--                     VENTANA MODAL "EDITAR CONSULTORIO" -->
		<div class="modal fade" id="actualizarConsultorio" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Editar Consultorio</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="consultorios" method="post">
						<div class="modal-body">
							<input type="hidden" value="actualizar" name="operacion">
							<div class="mb-3">
								<label class="col-6">C�digo Consultorio:</label>
                                <input type="text" class="form-control col-6" id="idConsultorio" name="idConsultorio" readOnly>
                                <c:out value="${unConsultorio.id_consultorio}"></c:out>								
							</div>
							<div class="mb-3">
								<label class="col-6">Descripci�n Consultorio:</label>
                                <input type="text" class="form-control col-6" id="descConsultorio" name="descConsultorio" required>
                                <c:out value="${unConsultorio.descripcion}"></c:out>
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
		<!--                     VENTANA MODAL "ELIMINAR CONSULTORIO" -->
		<div class="modal fade" id="eliminarConsultorio" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="consultorios" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="eliminar" name="operacion">
					        <input type="hidden" id="idConsultorio" name="idConsultorio">
					        <div class="mb-3">
					            <label class="col-6">�Desea anular el consultorio?</label>
					            <input type="hidden"  id="idConsultorio" name="idConsultorio">
								<div  class="fs-4 text-danger" id="descConsultorio" name="descConsultorio"></div>
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
		<!--                     VENTANA MODAL "REVIVIR CONSULTORIO" -->
		<div class="modal fade" id="revivirConsultorio" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="consultorios" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="revivir" name="operacion">
					        <input type="hidden" id="idConsultorio" name="idConsultorio">
					        <div class="mb-3">
					            <label class="col-6">�Desea restablecer el consultorio?</label>
					            <input type="hidden"  id="idConsultorio" name="idConsultorio">
								<div  class="fs-4 text-danger" id="descConsultorio" name="descConsultorio"></div>
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
	</body>
	
</html>
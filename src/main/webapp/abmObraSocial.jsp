<%@page import="entity.ObraSocial"%>
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
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
							<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado	de Obras sociales</h4>
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
                            			<!-- Tabla de obras sociales activas -->
		                                <table id="tablaActivos" class="table table-striped my-2">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Código de Obra social</th>
		                                            <th scope="col">Nombre</th>
		                                            
		                                            
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unaObraSocial" items="${tablaActivas}">
		                                            <tr>
		                                                <td><c:out value="${unaObraSocial.id_obra_social}"/></td>
		                                                <td><c:out value="${unaObraSocial.nombre_os}"/></td>
		                                                
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal' data-bs-target='#eliminarObraSocial' idObraSocial="${unaObraSocial.id_obra_social}" nomObraSocial="${unaObraSocial.nombre_os}">
    															<i class='bi bi-trash-fill m-1'></i>
															</a> 
															<a href='#' data-bs-toggle='modal' data-bs-target='#actualizarObraSocial' idObraSocial="${unaObraSocial.id_obra_social}" nomObraSocial="${unaObraSocial.nombre_os}">
																<i class="bi bi-pencil-fill"></i>
															</a>
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <!-- Tabla de obra sociales inactivas -->
		                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
		                                    <thead>
		                                        <tr>
		                                            <th scope="col">Código de Obra Social</th>
		                      
		                                            <th scope="col">Nombre</th>
		                                            <th scope="col">Operaciones</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <c:forEach var="unaObraSocial" items="${tablaInactivas}">
		                                            <tr>
		                                                <td><c:out value="${unaObraSocial.id_obra_social}"/></td>
		                                                <td><c:out value="${unaObraSocial.nombre_os}"/></td>
		                                                
		                                                <td>
		                                                	<a href='#' data-bs-toggle='modal'data-bs-target='#revivirObraSocial' idObraSocial="${unaObraSocial.id_obra_social}" nomObraSocial="${unaObraSocial.nombre_os}">
		                                                		<i class='bi bi-heart-fill m-1'></i>
		                                                	</a> 
		                                                </td>
		                                            </tr>
		                                        </c:forEach>
		                                    </tbody>
		                                </table>
		                                <div class="row justify-content-end">
		                                	<button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaObraSocial" data-bs-whatever="@mdo">Agregar Obra Social</button>
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
			                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
			                </div>
			                <div></div>
			            </div>
			        </div>
			    </div>
 			    <script> 
 			        new bootstrap.Modal(document.getElementById('mensajeOK')).show(); 
 			    </script> 
			</c:if>   
<!-- 		VENTANA MODAL "AGREGAR OBRA SOCIAL" -->
		<div class="modal fade" id="altaObraSocial" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">
							Nueva Obra Social
						</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="obrassociales" method="post">
						<div class="modal-body">
							<input type="hidden" value="alta" name="operacion">
							<div class="mb-3">
								<label class="col-6">Codigo obra social:</label>
	                            <input type="text" class="form-control col-6" name="idObraSocial">
							</div>
							<div class="mb-3">
								<label class="col-6">Nombre obra social:</label>
	                            <input type="text" class="form-control col-6" name="nomObraSocial">
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
		<!--                     VENTANA MODAL "EDITAR OBRA SOCIAL" -->
		<div class="modal fade" id="actualizarObraSocial" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Editar Obra Social</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="obrassociales" method="post">
						<div class="modal-body">
							<input type="hidden" value="actualizar" name="operacion">
							<div class="mb-3">
								<label class="col-6">Codigo Obra Social:</label>
                                <input type="text" class="form-control col-6" id="idObraSocial" name="idObraSocial" readOnly>
                                <c:out value="${unaObraSocial.id_obra_social}"></c:out>								
							</div>
							
							<div class="mb-3">
								<label class="col-6">Nombre Obra Social:</label>
                                <input type="text" class="form-control col-6" id="nomObraSocial" name="nomObraSocial">
                                <c:out value="${unaObraSocial.nombre_os}"></c:out>
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
		<!--                     VENTANA MODAL "ELIMINAR OBRA SOCIAL" -->
		<div class="modal fade" id="eliminarObraSocial" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="obrassociales" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="eliminar" name="operacion">
					        <input type="hidden" id="idObraSocial" name="idObraSocial">
					        <div class="mb-3">
					            <label class="col-6">¿Desea eliminar la Obra Social?</label>
					            <input type="hidden"  id="idObraSocial" name="idObraSocial">
								<div  class="fs-4 text-danger" id="nomObraSocial" name="nomObraSocial"></div>
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
		<!--                     VENTANA MODAL "REVIVIR OBRA SOCIAL" -->
		<div class="modal fade" id="revivirObraSocial" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="obrassociales" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="revivir" name="operacion">
					        <input type="hidden" id="idObraSocial" name="idObraSocial">
					        <div class="mb-3">
					            <label class="col-6">¿Desea reactivar la obra social?</label>
					            <input type="hidden"  id="idObraSocial" name="idObraSocial">
								<div  class="fs-4 text-danger" id="nomObraSocial" name="nomObraSocial"></div>
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
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<script src="js/funciones_abm.js"></script>
		<script src="js/toggle.js"></script>
	</body>
	
</html>
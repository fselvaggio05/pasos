<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	</head>
	<body>
    	<div class="container-fluid">
        	<div class="row mt-1">
            	<div class="col-3 ">
                	<jsp:include page="menu_final.jsp" />
            	</div>
            	<div class="col-9">
                	<div class="container ">
                        <h4 class="text-center my-4 text-decoration-underline fw-bold ">Generar agenda</h4>
                        <h5 class="text-center mb-5 fw-bold ">Horarios profesionales</h5>                		
                        <form action="generarAgendas" method="post">
                        	<input type="hidden" value="generar" name="operacion">
                          	<table class="table table-striped my-2">
                             <thead>
                                 <tr>
                                 	<th scope="col">Matrícula </th>
                                     <th scope="col">Apellido</th>
                                     <th scope="col">Práctica</th>
                                     <th scope="col">Día de la semana</th>
                                     <th scope="col">Hora desde</th>
                                     <th scope="col">Hora hasta</th>                   
                                 </tr>
                             </thead>
                             <tbody>
                             	<c:forEach var="hor" items="${horarios}">
                                		<tr>
                                    		<td> 
                                          <input class="form-check-input m-1" type="checkbox" id="cb1" name="seleccionados" value="${hor.id_horario}" >
                                          <c:out value="${hor.getProfesional().getMatricula()}"></c:out>
                                      </td>                           		  
								<td><c:out value="${hor.getProfesional().getApellido()}"></c:out></td>
								<td><c:out value="${hor.getPractica().getDescripcion()}"></c:out></td>
								<td><c:out value="${hor.dia_semana}"></c:out></td>
								<td><c:out value="${hor.hora_desde}"></c:out></td>
								<td><c:out value="${hor.hora_hasta}"></c:out></td>                                     
                                		</tr>
                              		</c:forEach>                                   
                            	</tbody>
                        	</table>
                        	<div class="container text-end mb-3">		
								<button type="button" class="btn btn-success col-2 justify-content-end" onclick="selectAll()">Seleccionar todos</button>
								<button type="button" class="btn btn-success col-3 justify-content-end me-4" onclick="unSelectAll()">Deseleccionar todos</button>
								<button type="submit" class="btn btn-success col-2 justify-content-end" >Generar agenda</button>
								<button type="button" class="btn btn-success col-2 justify-content-end">Cancelar</button>
							</div>
						</form>
                    </div> 
                    <!--MENSAJE DE OPERACION -->
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
		 			    <script>new bootstrap.Modal(document.getElementById('mensajeOK')).show();</script> 
					</c:if>
					<!--VENTANA MODAL - ELIMINAR SELECCION -->
					<div class="modal fade" id="eliminarSeleccion" tabindex="-1" aria-labelledby="eliminarSeleccion" aria-hidden="true">
		            	<div class="modal-dialog">
		                    <div class="modal-content">
		                        <div class="modal-header">
		                            <h1 class="modal-title fs-5" id="eliminarSeleccion">Advertencia</h1>
		                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                        </div>		
		                        <form action="generarAgendas" method="post">
		                            <div class="modal-body">
										<input type="hidden" value="eliminar" name="operacion">
		                                <div class="mb-3">
		                                    <label class="fw-bold fs-5">¿Desea eliminar los horarios seleccionados?</label>
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
		            <!--VENTANA GENERAR AGENDA -->
					<div class="modal fade" id="generarAgenda" tabindex="-1" aria-labelledby="eliminarSeleccion" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="eliminarSeleccion">Advertencia</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
								<form action="generarAgendas" method="post">
									<div class="modal-body">
										<input type="hidden" value="generar" name="operacion">
										<div class="mb-3">
											<label class="fw-bold fs-5">Confirmar generación agenda de los profesionales: ...</label>										
											<input type="text" name="seleccionados" id="seleccionados">
											<input type="hidden" id="idEnviado" name="idEnviado">
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
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
            <script src="js/funciones_abm.js"></script>
    	</div>       
    </body>
</html>
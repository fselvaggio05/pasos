<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   
</head>

<body>
    <div class="container-fluid">
        <div class="row ">

        </div>

        <div class="row mt-1">
            <div class="col-3 ">

                <jsp:include page="menu_final.jsp" />

            </div>

            <div class="col-9">

                <div class="container ">
                   
                        <h4 class="text-center my-4 text-decoration-underline fw-bold ">Generar agenda</h4>
                        <h5 class="text-center mb-5 fw-bold ">Horarios profesionales</h4>
                
                       
                		
                            <table class="table table-striped my-2">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th scope="col">Matricula </th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Practica</th>
                                        <th scope="col">Dia de la semana</th>
                                        <th scope="col">Hora desde</th>
                                        <th scope="col">Hora hasta</th>
                                                           
                                        </tr>
                
                                </thead>
                                <tbody>
                                   
                                   
                                 <c:forEach var="hor" items="${horarios}">
                                    <tr>
                                        <td> 
                                            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                        </td>
                                        
                                       		    <td><c:out value="${hor.matricula}"></c:out></td>
												<td><c:out value="${hor.apellido_profesional}"></c:out></td>
												<td><c:out value="${hor.desc_practica}"></c:out></td>
												<td><c:out value="${hor.dia_semana}"></c:out></td>
												<td><c:out value="${hor.hora_desde}"></c:out></td>
												<td><c:out value="${hor.hora_hasta}"></c:out></td>
                                        
                                     
                                    </tr>
                                  </c:forEach>               
                                    
                                   
                                </tbody>
                            </table>
                    </div>
                  
                    <div class="container text-end mb-3">
								<button type="button"
									class="btn btn-success col-2 justify-content-end"
									data-bs-toggle="modal" data-bs-target="#eliminarSeleccion"
									>Eliminar seleccionados
								</button>
								
								<button type="button"
									class="btn btn-success col-2 justify-content-end"
									data-bs-toggle="modal" data-bs-target="#generarAgenda"
									>Generar agenda
								</button>
								
								<button type="button"
									class="btn btn-success col-2 justify-content-end">Cancelar
								</button>
					</div>
					
					
					
					
					
<!-- 					VENTANA MODAL - ELIMINAR SELECCION -->

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
                                    <label class="fw-bold fs-5">Desea eliminar los horarios seleccionados?</label>
                                    
<!--                                     	TODO: mostrar los horarios que se van a eliminar? -->

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
              
              
              
<!--               VENTANA GENERAR AGENDA -->

				<div class="modal fade" id="generarAgenda" tabindex="-1"
					aria-labelledby="eliminarSeleccion" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="eliminarSeleccion">Advertencia</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>

							<form action="generarAgendas" method="post">
								<div class="modal-body">
									<input type="hidden" value="generar" name="operacion">


									<div class="mb-3">
										<label class="fw-bold fs-5">Confirmar generacion agenda de los profesionales: ...</label>

										<!--                                     	TODO: mostrar los horarios que se van a eliminar? -->

										<input type="hidden" id="idEnviado" name="idEnviado">

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






			</div>            
            </div>
            


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
    integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
    crossorigin="anonymous"></script>
</body>

</html>
<%@page import="entity.Equipo"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Practica" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">

<head>

   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   

</head>

<body>

<div class="container-fluid">
    <div class="row bg-primary">

    </div>

    <div class="row mt-1">
        <div class="col-3">

            <jsp:include page="menu_final.jsp"/>

        </div>



			<div class="col-9">

				<div class="container">
					<h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado
						de practicas profesionales</h4>

		

					<div class="row justify-content-center mt-3">

						
							<div class="card text-center">
								<div class="card-header">

									<ul class="nav nav-pills card-header-pills">
										<li class="nav-item"><a href="practicas" class="nav-link active">Activas</a>
										</li>
										<li class="nav-item "><a href="practicasIn" class="nav-link ">Inactivas</a>
										</li>
									</ul>

								</div>

								<div class="card-body">
									<h4 class="card-title"></h4>
									<!-- aqui va el listado de practicas  -->
									<table class="table table-striped my-2">
										<thead>
											<tr>
												<th scope="col">ID Practica</th>
												<th scope="col">Descripcion</th>
												<th scope="col">Duracion</th>
												<th scope="col">Equipo</th>
												<th scope="col">Operaciones</th>

											</tr>

										</thead>
										<tbody>
											<tr>
												
												<c:forEach var="pract" items="${practicasA}">

													<tr>
														<td><c:out value="${pract.id_practica}"></c:out></td>
														<td><c:out value="${pract.descripcion}"></c:out></td>
														<td><c:out value="${pract.duracion}"></c:out></td>
														<td><c:out value="${pract.desc_equipo}"></c:out></td>
														



														<td><a href='#' data-bs-toggle='modal'data-bs-target='#activar'
															id="${pract.id_practica}"
															descripcion="${pract.descripcion}"><i
																class='bi bi-trash-fill m-1'></i></a> 
															
															<a href='#'
															data-bs-toggle='modal'
															data-bs-target='#actualizarPractica'
															idPractica="${pract.id_practica}"
															descPractica="${pract.descripcion}"
															equipo="${pract.id_equipo}"
															duracion = "${pract.duracion}"><i
																class="bi bi-pencil-fill" onclick=""></i></a></td>
													<tr>
												</c:forEach>
											</tr>
										</tbody>
									</table>
									
															
									<div class="row justify-content-end">
						                <button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal"
						                        data-bs-target="#altaPractica" data-bs-whatever="@mdo" onclick="mensaje()">Agregar practica</button>
						                <button type="button" class="btn btn-success col-2 m-1">Cancelar</button>
						            </div>
            
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


          	
<!--             MENSAJE DE OPERACION -->

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


<!--                     VENTANA MODAL "AGREGAR PRACTICA" -->



            <div class="modal fade" id="altaPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Nueva practica</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="practicas" method="post" onsubmit="mensaje()">
                            <div class="modal-body">

                                <input type="hidden" value="alta" name="operacion">

								<div class="mb-3">
                                    <label class="col-6">ID Practica:</label>
                                    <input type="text"  class="form-control col-6" name="idPractica" >
                                </div>
                                
                                <div class="mb-3">
                                    <label class="col-6">Descripcion practica:</label>
                                    <input type="text" class="form-control col-6" name="descPractica" >
                                </div>

								<div class="mb-3">
                                    <label class="col-6">Duracion:</label>
                                    <input type="text" class="form-control col-6" name="duracion" >
                                </div>
								

                                <div class="mb-3">
                                    <label class="col-6">Equipo:</label>
                                    <select class="form-select col-6" name="idEquipo"> 
                                    	<option value="1">Seleccione un equipo</option>
                                    	
                                    		<c:forEach var="equip" items="${equipos}" >
                                    			<option value="<c:out value="${equip.id_equipo}"></c:out>"><c:out value="${equip.descripcion}"></c:out></option>
                                    		</c:forEach>
                                    </select>                               
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
                    
                    
<!--                     VENTANA MODAL "EDITAR PRACTICA" -->
                    
                    
			<div class="modal fade" id="actualizarPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Editar practica</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="practicas" method="post">
                            <div class="modal-body">

                                <input type="hidden" value="actualizar" name="operacion">
                                

								<div class="mb-3">
                                    <label class="col-6">ID Practica:</label>
                                    <input type="text" class="form-control col-6" id="idPractica" name="idPractica" readOnly>
                                </div>
                                
                                <div class="mb-3">
                                    <label class="col-6">Descripcion practica:</label>
                                    <input type="text" class="form-control col-6" id="descPractica" name="descPractica" >
                                </div>

								<div class="mb-3">
                                    <label class="col-6">Duracion:</label>
                                    <input type="text" class="form-control col-6" id="duracion" name="duracion" >
                                </div>

                                <div class="mb-3">
                                    <label class="col-6">Equipo:</label>
                                   <select class="form-select col-6" id="equipo" name="idEquipo">
                                   	<option value="">Seleccione un equipo</option>
                                    		<c:forEach var="equip" items="${equipos}" >
                                    			<option value="<c:out value="${equip.id_equipo}"></c:out>"><c:out value="${equip.descripcion}"></c:out></option>
                                    		</c:forEach>
                                    		
		                           </select>  
		                           
		                           
                                  
                                                                                             
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
              
              
              
              <!--                     VENTANA MODAL "ELIMINAR PRACTICA" -->
                    
                    
			<div class="modal fade" id="activar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                    <label class="col-6">Desea eliminar la practica?</label>
                                    <input type="hidden"  id="idEnviado" name="idEnviado">
                                     <input type="hidden" name="estado" value="0">
                                    
                                    <div  class="fs-4 text-danger" id="idMostrar" name="descPractica" >
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
            
                    




 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
        crossorigin="anonymous"></script>
<script src="js/editarPractica.js"></script>      
<script src="js/activar_desactivar.js"></script>
           
       
</body>

</html>
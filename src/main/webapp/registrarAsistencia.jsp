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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
               integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
               crossorigin="anonymous" referrerpolicy="no-referrer" />    
    

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



				<form action="registroAsistencia" method="post">
					<input type="hidden" value="buscarPaciente" name="operacion">
                    <div class="row mt-5 justify-content-center">

                        <div class="col-2">
                            <label class="fs-6 fw-bold mb-3 " for="">DNI paciente</label>
            
                        </div>
            
                        <div class="col-6">
                            <input type="number" class="col-6 form-control" name="dniPaciente">
                        </div>
                        
                        <div class="col-2 mb-4">
                            <button type="submit" class="col-6  btn btn-success">Buscar</button>
                        </div>
                    </div>
                 </form>
            
            
        		  <div class="col-6 fw-semibold fst-italic fs-5">                      
                           	
                           	<c:if test="${paciente!=null}">
                           		 <p class="text-decoration-underline ">Datos paciente:</p>
                           		 <c:out value="${paciente.apellido} ${paciente.nombre}    ${paciente.dni}     ${paciente.getObra_social().getNombre_os()}"></c:out> 
                           	</c:if>                              	                                                                 

                   </div>
            		
            		
            
            
                    <hr class="border border-success border-1 opacity-50">
            
                    <h4 class="text-center mt-2 mb-5 text-decoration-underline fw-bold ">Turnos registrados</h4>
                    
                    <div class="mt-4 container">
                    
                        <table class="table table-striped my-2">
                            <thead>
                                <tr>
                                  
                                  	
                                    <th scope="col">Apellido</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Práctica</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Hora</th>
            
                                </tr>
            
                            </thead>
            
                            <tbody>
                            
                            
                          
                            
                            <c:forEach var="tur" items="${turnos}">
											<tr>
											
												<td><c:out value="${tur.getHorario().getProfesional().getApellido()}"></c:out></td>
												<td><c:out value="${tur.getHorario().getProfesional().getNombre()}"></c:out></td>
												<td><c:out value="${tur.getHorario().getPractica().getDescripcion()}"></c:out></td>
												<td><c:out value="${tur.fecha_t}"></c:out></td>
												<td><c:out value="${tur.hora_t}"></c:out></td>												
												<td><a href="#" class="btn btn-success btn-sm" idTurno="${tur.id_turno}" 
												apellidoProfesional="${tur.getHorario().getProfesional().getApellido()}" 
												descPractica="${tur.getHorario().getPractica().getDescripcion()}" 
												fecha_turno="${tur.fecha_t}" hora_turno="${tur.hora_t}" data-bs-toggle="modal"
                                    			data-bs-target="#registroAsistencia">Registrar</a></td>
											</tr>
										
							</c:forEach>   
						    

                            </tbody>
                        </table>
                    </div>           
                   
                </div>            
            </div>
        </div>
    </div>
    
    
    
    <!--             VENTANA MODAL "REGISTRAR ASISTENCIA" -->
            
            
             <div class="modal fade" id="registroAsistencia" tabindex="-1" aria-labelledby="exampleModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Registrar asistencia</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="registroAsistencia" method="post">
                                                	<input type="hidden" value="regitrarAsistencia" name="operacion">
                                                	
                                                	<input type="hidden" name="idTurno" id="idTurno">
                                                	
                                                    <div>
                                                        <label for="practica" class="col-form-label fw-bold">Practica</label>
                                                    </div>
                                                    
                                                    <div class="mb-3">
                                                        <label class="col-form-label" id="descPractica"></label>
                                                    </div>
            
                                                    <div >
                                                        <label for="practica" class="col-form-label fw-bold">Profesional</label>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="col-form-label" id="apellidoProfesional"></label>
                                                    </div>
            
                                                    <div>
                                                        <label class="col-form-label fw-bold">Fecha turno</label>
                                                    </div>
                                                    <div>
                                                        <label class="col-form-label" id="fechaTurno"></label>
                                                    </div>
                                                    
                                                    <div>
                                                        <label for="practica" class="col-form-label fw-bold">Hora turno</label>
                                                    </div>
                                                    <div>
                                                        <label class="col-form-label" id="horaTurno"></label>
                                                    </div>
                                                    
                                                    <div class="modal-footer">
                                               			 <button type="button" class="btn btn-secondary btn-sm"
                                                    	 data-bs-dismiss="modal">Cancelar</button>
                                               			 <button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
                                               		</div> 
                                               		
                                               </form> 	
                                            </div>
                              </div> 
                       </div>
                 </div>   
                      
            
            
               <!--MENSAJE DE OPERACION -->
             <c:if test="${mensaje !=null }">
                   <div class="modal fade" id="mensajeOK" tabindex="-1" aria-labelledby="exampleModalLabel"
                       aria-hidden="true">
                       <div class="modal-dialog">
                           <div class="modal-content">
                               <div class="modal-header">
                                   <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
                                   <button type="button" class="btn-close" data-bs-dismiss="modal"
                                       aria-label="Close"></button>
                               </div>
                               <div class="modal-body">
                                   <p class="fs-5 fw-bold">${mensaje}<i class="fa-solid fa-circle-info fa-2xl"
                                           style="color: #FFD43B;"></i></p>
                               </div>
                               <div class="modal-footer">
                                   <button type="button" class="btn btn-secondary"
                                       data-bs-dismiss="modal">Close</button>
                               </div>
                           </div>
                       </div>
                   </div>
                   <script>new bootstrap.Modal(document.getElementById('mensajeOK')).show();</script>
               </c:if>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
    integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
    crossorigin="anonymous"></script>
    
<script src="js/funciones_abm.js"></script>
</body>

</html>
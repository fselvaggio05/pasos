<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="true" %>
    
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
        <div class="row ">

        </div>

        <div class="row mt-1">
            <div class="col-3 ">

                <jsp:include page="menu_final.jsp" />

            </div>

            <div class="col-9">

                <div class="container ">


                    <h5 class="small fst-italic text-right mt-3">Bienvenido @USUARIO</h5>

                    <h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registrar prescripcion</h4>

                    <div>


				    <form action="prescripcion" method="post">
					   <input type="hidden" name="operacion" value="buscarPaciente">
					   
                        <div class="row container justify-content-evenly">

                            <div class="col-2">
                                <label class="fs-6 fw-bold mb-3 ">DNI paciente</label>

                            </div>
                            <div class="col-7">
                                <input class="form-control" type="text" name="dniPaciente">
                            </div>
                            <div class="col-2">
                                <button class="btn btn-success">Buscar</button>
                            </div>
                        </div>                    
                     </form>
                     
                    </div>




                    <div class="mt-4 container">                      
                     
                      	
                        <table class="table table-striped my-2">
                            <thead>
                                <tr>
                                    <th scope="col">Profesional</th>
                                    <th scope="col">Práctica</th>
                                    <th scope="col">Turno asignado</th>

                                </tr>

                            </thead>

                            <tbody>
                            
                            <c:forEach var="tur" items="${turnos}">
											<tr>
											
												<td><input class="form-check-input m-1" type="radio" name="seleccion" id="seleccion" value="${tur.getHorario().getPractica().getId_practica()}" onclick="seleccionRadio()">												
                                            		<c:out value="${tur.getHorario().getProfesional().getApellido()} , ${tur.getHorario().getProfesional().getNombre()}"></c:out></td>
												<td><c:out value="${tur.getHorario().getPractica().getDescripcion()}"></c:out></td>
												<td><c:out value="${tur.fecha_t}    ${tur.hora_t}"></c:out></td>
												
											</tr>
										
							</c:forEach>

                    
                            </tbody>
                        </table>
                        
                        <button type="button" class="btn btn-success btn-sm me-2" data-bs-toggle="modal"
                         data-bs-target="#registroPrescripcion" data-bs-whatever="@mdo">Registrar prescripción</button>
                        
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
                    
                    
<!--                  VENTANA MODAL REGISTRAR PRESCRIPCION    -->
                <div class="modal fade" id="registroPrescripcion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Datos prescripcion</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="prescripcion" method="post">
                            <div class="modal-body">
                                <input type="hidden" value="alta" name="operacion">
                                	<input type="hidden" id="idPractica" name="idPractica">
                                	                                	
                                	
                                
                                	
								<div class="mb-3">
                                    <label  class="fw-bold form-label col-6">Apellido y nombre paciente</label>
     		                        <c:out value="${paciente.apellido} ${paciente.nombre}"></c:out>
                                </div>
                                <div class="mb-3">
                                	<label  class="fw-bold form-label col-6">DNI</label> 
									<c:out value="${paciente.dni}"></c:out>
							    </div>
								<div class="mb-3">
                                    <label  class="fw-bold form-label col-6">Numero de afiliado</label> 
   									<input type="hidden" value="${paciente.nro_afiliado}" name="nroAfiliado" > 
  									<c:out value="${paciente.nro_afiliado}"></c:out>
                                </div>
                                <div class="mb-3">
                                     <label class="col-form-label col-6">Fecha prescripción</label>
    								 <input type="date" class="form-control col-6" name="fechaPresc">                               
                                </div>
                                <div class="mb-3">
                                     <label class="col-form-label">Cantidad de sesiones</label>
    								<input type="number" class="form-control" name="cantSesiones">                               
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
    </div>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
        crossorigin="anonymous"></script>
        
     <script src="js/funciones_abm.js"></script>
</body>

</html>
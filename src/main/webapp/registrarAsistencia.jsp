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
												<td><input class="form-check-input" type="radio" name="radio" value="<c:out value="${tur.apellido_profesional}"></c:out>"></td>
												<td><c:out value="${tur.nombre_profesional}"></c:out></td>
												<td><c:out value="${tur.desc_practica}"></c:out></td>
												<td><c:out value="${tur.fecha_t}"></c:out></td>
												<td><c:out value="${tur.hora_t}"></c:out></td>
												
<!-- 												agregar el radiobutton												 -->
											</tr>
										
							</c:forEach>
            
<!--                                 <tr> -->
<!--                                     <td> -->
<!--                                         <div class="form-check"> -->
<!--                                             <input class="form-check-input" type="radio" name="flexRadioDefault" -->
<!--                                                 id="flexRadioDefault1"> -->
<!--                                             <label class="form-check-label" for="flexRadioDefault1"> -->
<!--                                                 GODOY. Santiago -->
<!--                                             </label> -->
<!--                                         </div> -->
            
<!--                                     </td> -->
            
<!--                                     <td>Magnetoterapia</td> -->
<!--                                     <td>15/11/2023 14:00 hs</td> -->
<!--                                     <td></td> -->
<!--                                 </tr> -->
            
<!--                                 <tr> -->
<!--                                     <td> -->
<!--                                         <div class="form-check"> -->
<!--                                             <input class="form-check-input" type="radio" name="flexRadioDefault" -->
<!--                                                 id="flexRadioDefault2" checked> -->
<!--                                             <label class="form-check-label" for="flexRadioDefault2"> -->
<!--                                                 SILVA, Alejandra -->
<!--                                             </label> -->
<!--                                         </div> -->
<!--                                     </td> -->
            
<!--                                     <td>Ondas rusas</td> -->
<!--                                     <td>15/11/2023 15:00 hs</td> -->
<!--                                     <td></td> -->
            
<!--                                 </tr> -->
            
<!--                                 <tr> -->
<!--                                     <td> -->
<!--                                         <div class="form-check"> -->
<!--                                             <input class="form-check-input" type="radio" name="flexRadioDefault" -->
<!--                                                 id="flexRadioDefault1"> -->
<!--                                             <label class="form-check-label" for="flexRadioDefault1"> -->
<!--                                                 GARBIN, Andrea -->
<!--                                             </label> -->
<!--                                         </div> -->
<!--                                     </td> -->
<!--                                     <td>Fisioterapia</td> -->
<!--                                     <td>15/11/2023 16:00 hs</td> -->
<!--                                     <td></td> -->
<!--                                 </tr> -->
                            </tbody>
                        </table>
                    </div>
            
                    <div class="row justify-content-end">
            
                        <div class="row">
                             <div class="d-flex justify-content-end col-8">
                                <button type="button" class="btn btn-success btn-sm" data-bs-toggle="modal"
                                    data-bs-target="#exampleModal" data-bs-whatever="@mdo">Registrar asistencia</button>
            
                                 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Registrar asistencia</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <form>
                                                    <div>
                                                        <label for="practica" class="col-form-label fw-bold">Practica</label>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="descPractica" class="col-form-label"> Ondas rusas</label>
                                                    </div>
            
                                                    <div >
                                                        <label for="practica" class="col-form-label fw-bold">Profesional</label>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="descPractica" class="col-form-label">SILVA, Alejandra</label>
                                                    </div>
            
                                                    <div>
                                                        <label for="practica" class="col-form-label fw-bold">Fecha y hora</label>
                                                    </div>
                                                    <div>
                                                        <label for="descPractica" class="col-form-label"> 15/11/2023 16:00 hs</label>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary btn-sm"
                                                    data-bs-dismiss="modal">Cancelar</button>
                                                <button type="button" class="btn btn-primary btn-sm">Confirmar</button>
                                            </div>
                                        </div>
            
                                        
                                    </div>
            
            
                                </div>
                            </div>
                            <div class="col-1">
                                <button type="submit" class="btn btn-success btn-sm">Volver</button>
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
</body>

</html>
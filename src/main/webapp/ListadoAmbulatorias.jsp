<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="true" %>
    
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


                <h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Listado de practicas ambulatorias
                    realizadas</h4>

                <div class="container">
                    <div class="row  justify-content-evenly">
                        <div class="col-2">
                            <label for="">Fecha desde:</label>
                            <label class="form-label" id="fecha_desde"></label>
                        </div>


                        <div class="col-2">
                            <input type="date" class="form-control col-2 ml-4">
                        </div>

                        <div class="col-2">
                            <label for="">Fecha hasta:</label>
                            <label class="form-label" id="fecha_hasta"></label>
                        </div>

                        <div class="col-2">
                            <input type="date" class="form-control col-2">
                        </div>

                        <div class="col-3">
                            <button type="submit" class="btn btn-success">Buscar</button>
                        </div>
                    </div>

                </div>


                <hr class="mt-4 border border-success border-1 opacity-50">


                <div class="container">
                
                   
<table class="table table-striped my-2" id="tablaPrescripciones">
						<thead>
							<tr>
								<th scope="col">Obra Social</th>
								<th scope="col">Practica</th>
								<th scope="col">Fecha</th>
								<th scope="col">Paciente</th>
								<th scope="col">Cantidad de sesiones</th>

							</tr>

						</thead>

						<tbody>

							<c:if test="${prescripciones!=null}">

								<c:forEach var="pre" items="${prescripciones}">

									<tr>
										<td><c:out
												value="${pre.getPaciente().getObra_social().getNombre()}"></c:out>
										</td>

										<td><c:out
												value="${pre.getPractica().getCodPractica()}"></c:out>
										</td>

										<td><c:out value="${pre.getFecha_prescripcion()}"></c:out>
										</td>
										<td><c:out
												value="${pre.getPaciente().getApellido()} , ${pre.getPaciente().getNombre()}"></c:out>
										</td>
                                                                                     <td><c:out
												value="${pre.getCant_Sesiones()}"></c:out>
										</td>



										<td><a href="#" class="btn btn-success btn-sm"
											nomObra_Social="${pre.getObra_social().getNombre()}"
                                            codPractica="${pre.getPractica().getCodPractica()}"
											paciente="${pre.getPaciente().getApellido()} , ${pre.getPaciente().getNombre()}"
											sesiones="${pre.getCant_Sesiones()}"
											data-bs-toggle="modal" data-bs-target="#excluir">Excluir</a>
											</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
						
						
						
					</table>



                    <div class="container">
                        <div class="row justify-content-end">

                         <div class="col-2">
									<button type="submit" class="btn btn-success" id="listado">Buscar</button>
								</div>
                              <div class="col-2">
									<button type="submit" class="btn btn-success" id="exportar">Exportar</button>
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
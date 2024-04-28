<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                        <h4 class="text-center my-5 text-decoration-underline fw-bold ">Historico de Montos por Practica</h4>
                        <input type="hidden" value="<%= request.getParameter("idPractica") %>" id="idPractica" name="idPractica">
                        <div class="row justify-content-center mt-3">
                            <div class="card text-center">
                                <div class="card-body">
                                    <!-- Tabla de Montos por Practica -->
                                    <table id="tablaActivos" class="table table-striped my-2">
									    <thead>
									        <tr>
									            <th scope="col">Fecha Desde</th>
									            <th scope="col">Fecha Hasta</th>
									            <th scope="col">Monto</th>
									            <th scope="col">Operaciones</th>
									        </tr>
									    </thead>
									    <tbody>
									        <c:forEach var="unMonto" items="${practica.getMontos()}">
									            <tr>
									                <td><c:out value="${unMonto.fecha_desde}"/></td>
									                <td><c:out value="${unMonto.fecha_hasta}"/></td>
									                <td>
													    <script>
													        function formatearNumero(numero) {
													            return new Intl.NumberFormat('es-ES', { minimumFractionDigits: 2 }).format(numero);
													        }
													        document.write(formatearNumero(${unMonto.monto}));
													    </script>
													</td>
									                <td>
									                    <a href='#' data-bs-toggle='modal' data-bs-target='#actualizarMonto' id_monto="${unMonto.id_monto}" fechaDesde="${unMonto.fecha_desde}" fechaHasta="${unMonto.fecha_hasta}" monto="${unMonto.monto}" idPractica="${practica.id_practica}">
									                        <i class="bi bi-pencil-fill"></i>
									                    </a>
									                </td>
									            </tr>
									        </c:forEach>
									    </tbody>
									</table>
                                    <div class="row justify-content-end">
										<button id="btnAltaMontos" type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaMonto" data-bs-whatever="@mdo" data-practica="<%= request.getParameter("idPractica") %>">Agregar Monto</button>
    									<a href="practicas" class="btn btn-primary col-2 m-1">Volver</a>
                                    </div>
                                </div>
                            </div>
                        </div>                          
                    </div>
                </div>
            </div>
        </div>
    </div> 
	<!--VENTANA MODAL "AGREGAR MONTO" -->
		<div class="modal fade" id="altaMonto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Monto</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="MontosPractica" method="post">
						<div class="modal-body">
							<input type="hidden" value="alta" name="operacion">
							<div class="mb-3">
						        <label class="col-6">Codigo de Practica:</label>
						        <input type="text" class="form-control col-6" id="idPracticaAlta" name="idPracticaAlta" readonly>
						    </div>							
						    <div class="mb-3">
								<label class="col-6">Fecha Desde:</label>
	                            <input type="date" class="form-control col-6" name="fechaDesde">
							</div>
							<div class="mb-3">
								<label class="col-6">Fecha Hasta:</label>
	                            <input type="date" class="form-control col-6" name="fechaHasta">
							</div>
							<div class="mb-3">
								<label class="col-6">Monto:</label>
	                            <input type="number" class="form-control col-6" name="monto">
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
		<!-- Ventana Modal "Actualizar Monto" -->
		<div class="modal fade" id="actualizarMonto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Monto</h1>
		                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		            </div>
		            <form action="MontosPractica" method="post">
		                <div class="modal-body">
		                	<input type="hidden" value="actualizar" name="operacion">
		                    <input type="hidden" id="idPractica" name="idPractica">
		                    <input type="hidden" id="idMonto" name="idMonto">
		                    <div class="mb-3">
		                        <label class="col-6">Fecha Desde:</label>
		                        <input type="date" class="form-control col-6" id="fechaDesde" name="fechaDesde">
		                    </div>
		                    <div class="mb-3">
		                        <label class="col-6">Fecha Hasta:</label>
		                        <input type="date" class="form-control col-6" id="fechaHasta" name="fechaHasta">
		                    </div>
		                    <div class="mb-3">
		                        <label class="col-6">Monto:</label>
		                        <input type="number" class="form-control col-6" id="monto" name="monto">
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
	</body>	
</html>
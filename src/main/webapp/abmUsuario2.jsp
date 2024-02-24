<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/Vistas Proyecto ING/sidebars.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <jsp:include page="menu_final.jsp"/>
        </div>
        <div class="col-9">
            <form action="usuarios" class="container" method="post">
                <div class="row mt-5">
                    <div class="col-3 mb-3">
                        <label class="col-form-label">Tipo de Usuario</label>
                    </div>
                    <div class="col-9">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="administrador" value="1" checked>
                            <label class="form-check-label" for="administrador">Administrador</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="profesional" value="2">
                            <label class="form-check-label" for="profesional">Profesional</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="tipoUsuario" id="paciente" value="3">
                            <label class="form-check-label" for="paciente">Paciente</label>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label>Nombre</label>
                        <input type="text" class="form-control" name="nombre">
                    </div>
                    <div class="col-6">
                        <label>Apellido</label>
                        <input type="text" class="form-control" name="apellido">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label>DNI</label>
                        <input type="text" class="form-control" name="dni">
                    </div>
                    <div class="col-6">
                        <label>Fecha de Nacimiento</label>
                        <input type="date" class="form-control" name="fechaNacimiento">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label>Teléfono</label>
                        <input type="text" class="form-control" name="telefono">
                    </div>
                    <div class="col-6">
                        <label>Género</label>
                        <select class="form-select" name="genero">
                            <option value="1">Femenino</option>
                            <option value="2">Masculino</option>
                            <option value="3">No binario</option>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-6">
                        <label>Email</label>
                        <input type="text" class="form-control" name="email">
                    </div>
                    <div class="col-6">
                        <label>Contraseña</label>
                        <input type="password" class="form-control" name="contraseña">
                    </div>
                </div>
                <!-- Aquí comienza la parte del Paciente -->
                <div class="row mt-3" id="partePaciente" style="display: none;">
                    <div class="col-12">
                        <select class="form-select col-6" name="id_obra_social"> 
                        	<option value="1">Seleccione una Obra Social</option>
                            <c:forEach var="unaOS" items="${obrasSociales}" >
                        		<option value="<c:out value="${unaOS.id_obra_social}"></c:out>"><c:out value="${unaOS.nombre}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-12 mt-3">
                        <label>Número de Afiliado</label>
                        <input type="text" class="form-control" name="nroAfiliado">
                    </div>
                </div>
                <!-- Aquí comienza la parte del Profesional -->
                <div class="row mt-3" id="parteProfesional" style="display: none;">
                    <div class="col-12 mt-3">
                        <label>Número de Matricula</label>
                        <input type="text" class="form-control" name="matricula">
                    </div>
                </div>
                <!-- Este es el botón de Enviar -->
                <div class="row mt-3">
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Enviar</button>
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
<script src="js/toggle.js"></script>

</body>
</html>

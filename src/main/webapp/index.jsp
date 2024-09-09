<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link rel="stylesheet" href="css/login.css">
    	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
   	</head>
	<body>
		<div class="login-reg-panel">
    		<div class="white-panel">
        		<div class="login-show">
        			<form action="login" method="post">
            			<input type="hidden" value="login" name="operacion">
            			<h2 class="mb-5">INICIO DE SESION</h2>
            			<input type="text" placeholder="Email" name="mail">
            			<input type="password" placeholder="Password" name="pass">
            			<div class="text-right mt-2">
                			<a href="olvideContraseña" class="text-primary">Olvidé contraseña</a> 
                			<span class="mx-2">|</span> 
                			<a class="text-primary" data-bs-toggle="modal" data-bs-target="#registro" href='#'>Registrarse</a>                			
            			</div>						
            			<input type="submit" value="Login" class="mt-3">
         			</form>
        		</div>
        	</div>
		</div>
		<!-- 		VENTANA MODAL "AGREGAR USUARIO" -->
		<div class="modal fade" id="registro" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Usuario</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="login" method="post">
						<div class="modal-body">
							<input type="hidden" id="tipoUsuarioHidden" name="tipoUsuario" value="3"> 
							<input type="hidden" value="registro" name="operacion">
							<input type="hidden" name="idModal" value="altaUsuario">							
							<div id="camposAlta">
								<div id="camposAdministrador">
									<div class="row mt-3">
										<div class="col-6">
											<label>Nombre</label> <input type="text" class="form-control" name="nombre">
										</div>
										<div class="col-6">
											<label>Apellido</label> <input type="text" class="form-control" name="apellido">
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>DNI</label> <input type="text" class="form-control" name="dni">
										</div>
										<div class="col-6">
											<label>Fecha de Nacimiento</label> <input type="date" class="form-control" name="fechaNacimiento">
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>Teléfono</label> <input type="number" class="form-control" name="telefono">
										</div>
										<div class="col-6">
											<label>Género</label> <select class="form-select" name="genero">
												<option value="1">Femenino</option>
												<option value="2">Masculino</option>
												<option value="3">No binario</option>
											</select>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>Email</label> <input type="text" class="form-control" name="email">
										</div>
										<div class="col-6">
											<label>Contraseña</label> <input type="password" class="form-control" name="contraseña">
										</div>
									</div>
								</div>
								<div id="camposPaciente">
									<div class="row mt-3">
										<div class="col-12">
											<label>Obra Social</label> <select class="form-select" name="id_obra_social">
												<option value="1">Seleccione una Obra Social</option>
												<c:forEach var="unaOS" items="${obrasSociales}">
													<option value="${unaOS.id_obra_social}">${unaOS.nombre_os}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-12 mt-3">
											<label>Número de Afiliado</label> <input type="text" class="form-control" name="nroAfiliado">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
							<button type="submit" class="btn btn-primary">Guardar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="js/login.js"></script>
	</body>
</html>

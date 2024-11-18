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
            			<h2 class="mb-5">INICIO DE SESIÓN</h2>
            			<input type="text" placeholder="Email" name="mail">
            			<input type="password" placeholder="Contraseña" name="pass">
            			<div class="text-right mt-2">
            			    <a href='#' class="text-primary" data-bs-toggle='modal' data-bs-target='#olvidoClave'>Olvidé mi contraseña</a>
                			<span class="mx-2">|</span> 
                			<a class="text-primary" data-bs-toggle="modal" data-bs-target="#registro" href='#'>Registrarse</a>                			
            			</div>						
            			<input type="submit" value="Ingresar" class="mt-3">
         			</form>
        		</div>
        	</div>
		</div>		
		<!--VENTANA MODAL "OLVIDE MI CONTRASEÑA" -->		
		<div class="modal fade" id="olvidoClave" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Recuperar contraseña</h1>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="login" method="post">
					    <div class="modal-body">
					         <input type="hidden" value="recuperar" name="operacion">
					   	       <div class="">            
					           		<div class="">
										<label class="col-6">Ingrese su correo electrónico:</label>
									</div>							
									<div class="mt-4 mx-auto">
					            		<input class="col-11" type="text"  name="mailRecupera" required>
					            	</div>
					            	<div class="modal-footer">
					                	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					                	<button type="submit" class="btn btn-primary">Enviar</button>
					            	</div>
					        	</div>
					    </div>
					</form>
				</div>
			</div>
		</div>
		<!--VENTANA MODAL "AGREGAR USUARIO" -->
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
											<label>Nombre</label> <input type="text" class="form-control" name="nombre" required>
										</div>
										<div class="col-6">
											<label>Apellido</label> <input type="text" class="form-control" name="apellido" required>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>DNI</label> <input type="text" class="form-control" name="dni" required>
										</div>
										<div class="col-6">
											<label>Fecha de Nacimiento</label> <input type="date" class="form-control" name="fechaNacimiento" required>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>Teléfono</label> <input type="number" class="form-control" name="telefono" required>
										</div>
										<div class="col-6">
											<label>Género</label> <select class="form-select" name="genero" required>
												<option value="1">Femenino</option>
												<option value="2">Masculino</option>
												<option value="3">No binario</option>
											</select>
										</div>
									</div>
									<div class="row mt-3">
										<div class="col-6">
											<label>Email</label> <input type="text" class="form-control" name="email" required>
										</div>
										<div class="col-6">
											<label>Contraseña</label> <input type="password" class="form-control" name="contraseña" required>
										</div>
									</div>
								</div>
								<div id="camposPaciente">
									<div class="row mt-3">
										<div class="col-12">
											<label>Obra Social</label> <select class="form-select" name="id_obra_social">
												<option value="0">Seleccione una Obra Social</option>
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
		<!--MENSAJE OPERACION-->
		<c:choose>
			<c:when test="${mensaje eq 'Cambio'}">
				<div class="modal fade" id="clave" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="exampleModalLabel">Cambio contraseña</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<form action="login" method="post">
								<div class="modal-body">
									<input type="hidden" value="clave" name="operacion">														
									<div id="camposAlta">
										<div id="camposAdministrador">										
											<div class="fst-italic">
												<label>La contraseña debe contener un maximo de 15 caracteres. </label>
											</div>											
											<div class="row mt-3">
												<div class="col-8">
													<label>Nueva clave:</label>
													<input type="text" class="form-control" name="nueva_clave1">
												</div>
											</div>
											<div class="row mt-3">
												<div class="col-8">
													<label>Reingrese clave:</label> 
													<input type="text" class="form-control" name="nueva_clave2">
												</div>												
											</div>
									 	</div>
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
				<script>
				    document.addEventListener('DOMContentLoaded', function () {
				        if (document.getElementById('clave')) {
				            new bootstrap.Modal(document.getElementById('clave')).show();
				        }
				    });
				</script>
			</c:when>
			<c:when test="${mensaje !=null }">
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
		            	</div>
		        	</div>
		    	</div>
				<script>
					document.addEventListener('DOMContentLoaded', function () {
					if (document.getElementById('mensajeOK')) {
				    	new bootstrap.Modal(document.getElementById('mensajeOK')).show();
				        }
				    });
				</script>			
			</c:when>		
		</c:choose>		  
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>		
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="js/login.js"></script>
	</body>
</html>
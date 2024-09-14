<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   
    
<div class="login-reg-panel">
    <div class="login-info-box">
        
    </div>
                        
    <div class="register-info-box">
       
    </div>
                        
    <div class="white-panel">
        <div class="login-show ">
        <form action="login" method="post">
        <input type="hidden" value="ingresar" name="operacion">
            <h2 class="mb-5">INICIO DE SESION</h2>
            <input type="text" placeholder="Email" name="mail">
            <input type="password" placeholder="Password" name="pass">
            <input type="submit" value="Login">
            <a href='#' class="" data-bs-toggle='modal' data-bs-target='#olvidoClave'>Olvide mi contraseña</a>
<!--             <input class="mr-4 form-check-input" type="checkbox" value="" id="recordarUsuario">Recordar usuario -->
         </form>
            
        </div>
       
    </div>
</div>


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
				    <!-- <input type="hidden" id="idHorario" name="idHorario">
 -->				        <div class="mb-3">
				            <label class="col-6">Ingrese correo:</label>
				            <input type="text"  name="mailRecupera">
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

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<script src="js/login.js"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    
</head>
<body>
   
    
<div class="login-reg-panel">
    <div class="login-info-box">
        
    </div>
                        
    <div class="register-info-box">
       
    </div>
                        
    <div class="white-panel">
        <div class="login-show ">
            <h2 class="mb-5">INICIO DE SESION</h2>
            <input type="text" placeholder="Email">
            <input type="password" placeholder="Password">
            <input type="button" value="Logins">
             <a href='#' data-bs-toggle='modal'data-bs-target='#olvidoClave'></a>
            
            
        </div>
        
    </div>
</div>


		<div class="modal fade" id="olvidoClave" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Advertencia</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<form action="login" method="post">
					    <div class="modal-body">
					        <input type="hidden" value="revivir" name="operacion">
					        <input type="hidden" id="idHorario" name="idHorario">
					        <div class="mb-3">
					            <label class="col-6">Ingrese su correo</label>
					            <input type="text"  id="mailRecupera" name="mailRecupera">
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

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/login.js"></script>
</body>
</html>
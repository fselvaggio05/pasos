<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<link rel="stylesheet" href="css/login.css">
    	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
   	</head>
	<body>
		<div class="login-reg-panel">
    		<div class="white-panel">
        		<div class="login-show">
        			<form action="login" method="post">
            			<h2 class="mb-5">INICIO DE SESION</h2>
            			<input type="text" placeholder="Email" name="mail">
            			<input type="password" placeholder="Password" name="pass">
            			<div class="text-right mt-2">
                			<a href="olvideContraseña" class="text-primary">Olvidé contraseña</a> 
                			<span class="mx-2">|</span> 
                			<a href="registro" class="text-primary">Registrarse</a>
            			</div>						
            			<input type="submit" value="Login" class="mt-3">
         			</form>
        		</div>
        	</div>
		</div>

		<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="js/login.js"></script>
	</body>
</html>

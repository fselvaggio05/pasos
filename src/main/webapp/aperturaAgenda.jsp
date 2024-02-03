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
                   
                        <h4 class="text-center my-4 text-decoration-underline fw-bold ">Generar agenda</h4>
                        <h5 class="text-center mb-5 fw-bold ">Horarios profesionales</h4>
                
                       
                
                            <table class="table table-striped my-2">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th scope="col">Matricula </th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">Practica</th>
                                        <th scope="col">Dia de la semana</th>
                                        <th scope="col">Hora desde</th>
                                        <th scope="col">Hora hasta</th>
                                                           
                                        </tr>
                
                                </thead>
                                <tbody>
                                                  
                                    <tr>
                                        <td> 
                                            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                     
                                    </tr>
                                    
                                   
                                </tbody>
                            </table>
                    </div>
                    <div class="container text-end">
                        <button type="reset" class="btn btn-success col-3 justify-content-end" >Eliminar seleccionados</button>
                        <button type="button" class="btn btn-success col-2 justify-content-end">Generar</button>
                            <!-- este boton va a mostrar un mensaje de confirmacion con los horarios a generar -->
                            <button type="button" class="btn btn-success col-2 justify-content-end">Cancelar</button>
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
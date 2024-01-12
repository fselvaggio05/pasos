<%@ page import="java.util.List" %>
<%@ page import="entity.Practica" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">

<head>

   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
   

</head>

<body>

<div class="container-fluid">
    <div class="row bg-primary">

    </div>

    <div class="row mt-1">
        <div class="col-3">

            <jsp:include page="menu_final.jsp"/>

        </div>



        <div class="col-9">
            <div class="container ">
                <h4 class="text-center my-5 text-decoration-underline fw-bold ">Listado de practicas profesionales</h4>
                <table class="table table-striped my-2">
                    <thead>
                    <tr>
                        <th scope="col">ID Practica</th>
                        <th scope="col">Descripcion</th>
                        <th scope="col">ID Equipo</th>
                        <th scope="col">Operaciones</th>

                    </tr>

                    </thead>
                    <tbody>
                    <tr>
                        <jsp:useBean id="practicas"  type="java.util.List<entity.Practica>" scope="request"/>
                        <c:forEach var="pract" items="${practicas}">
                          
                          <tr>
                            <td><c:out value="${pract.id_practica}"></c:out></td>
                            <td><c:out value="${pract.descripcion}"></c:out></td>
                            <td><c:out value="${pract.id_equipo}"></c:out></td>
                             
	                        <td><a href='#' data-bs-toggle='modal' data-bs-target='#delPractica'  ><i class='bi bi-trash-fill m-1'></i></a>
	                            <a href='#' data-bs-toggle='modal' data-bs-target='#updPractica' ><i class="bi bi-pencil-fill"></i></a>
	                        </td>
                          <tr>
                        </c:forEach>

                    </tr>                   

                    </tbody>
                </table>
            </div>

            <div class="row ">
                <button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal"
                        data-bs-target="#altaPractica" data-bs-whatever="@mdo">Agregar practica</button>
                <button type="button" class="btn btn-success col-2 m-1">Cancelar</button>
            </div>

            <div class="modal fade" id="altaPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Nueva practica</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="practicas" method="post">
                            <div class="modal-body">

                                <input type="hidden" name="altaPractica">
                                
                                <div class="mb-3">
                                    <label class="col-6">Descripcion practica:</label>
                                    <input type="text" class="form-control col-6" name="descPractica" >
                                </div>


<!-- 								10/1/24 Este campo, debe traerlo de la db y mostrarlo en un list desplegable -->

                                <div class="mb-3">
                                    <label class="col-6">ID Equipo:</label>
                                    <input type="text" class="form-control col-6" name="idEquipo" >
                                   
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    
                    
<!--                     VENTANA MODAL "AGREGAR PRACTICA" -->
                    
                    
               <div class="modal fade" id="delPractica" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Nueva practica</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <form action="formulario que realiza la toma y el guardado de los datos" method="post">
                            <div class="modal-body">

                                <div class="mb-3">
                                    <label class="col-6">ID Practica:</label>
                                    <!-- este campo va a estar deshabilitado y solo sumara uno al ultimo de la bd -->
                                    <input type="text" class="form-control col-6" name="idPractica" >
                                </div>

                                <div class="mb-3">
                                    <label class="col-6">Descripcion practica:</label>
                                    <input type="text" class="form-control col-6" name="descPractica" >
                                </div>

                                <div class="mb-3">
                                    <label class="col-6">Estado:</label>
                                    <select class="col-3 form-select" id="genero" onchange="">
                                        <option value="1">Activa</option>
                                        <option value="2">CRI CRI CRI</option>
                                    </select>
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
            </div>

            <div>
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
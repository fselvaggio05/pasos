<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/Vistas Proyecto ING/sidebars.css">

</head>

<body>
<div class="container-fluid">
    <div class="row bg-primary">

    </div>

    <div class="row">
        <div class="col-3 bg-success">

            <jsp:include page="menu_final.jsp"/>

        </div>

        <div class="col-9">

            <form class="container" action="AltaUsuarioServlet">

                <div class="row mt-5">
                    <div class="col-3 mb-3">
                        <label class="col-form-label">Tipo de documento</label>
                    </div>
                    <div class="col-2">
                        <select class="col-3 form-select" name="tipoDoc" onchange="">
                            <option value="1">DNI</option>
                            <option value="2">LC</option>
                            <option value="3">PAS</option>
                        </select>
                    </div>

                    <div class="col-3 mb-3">
                        <label class="col-form-label">DNI</label>
                    </div>
                    <div class="col-3">
                        <input type="number" name="dni" class="form-control">
                    </div>
                </div>

                <div class="row">
                    <div class="col-1 mb-3">
                        <label class="col-form-label">Nombre</label>
                    </div>
                    <div class="col-4">
                        <input type="text" name="nombre" class="form-control">
                    </div>

                    <div class="col-2 mb-3">
                        <label class="col-form-label">Apellido</label>
                    </div>
                    <div class="col-4">
                        <input type="text" name="apellido" class="form-control">
                    </div>
                </div>



                <div class="row">
                    <div class="col-1 mb-3">
                        <label class="col-form-label">Email</label>
                        <!-- realizar validacion de email mediante javascript -->
                    </div>
                    <div class="col-4">
                        <input type="text" name="email" class="form-control">
                    </div>

                    <div class="col-3">
                        <label class="col-form-label ">Fecha de nacimiento</label>
                    </div>
                    <div class="col-3">

                        <input type="date" class="form-control" name="fechaNac" id="fechaNac">

                    </div>
                </div>


                <div class="row">
                    <div class="col-1">Telefono</div>

                    <div class="col-4">
                        <input type="text" name="telefono" class="form-control">
                    </div>

                    <div class="col-3">Contraseña</div>
                    <div class="col-3 mb-3">

                        <input type="text" name="telefono" class="form-control">

                    </div>

                </div>


                <div class="row">
                    <div class="col-1">Género</div>

                    <div class="col-4">
                        <select class="col-3 form-select" name="genero" onchange="">
                            <option value="1">Femenino</option>
                            <option value="2">Masculino</option>
                            <option value="3">No binario</option>
                        </select>
                    </div>

                    <div class="col-3">Tipo de usuario</div>
                    <div class="col-3 mb-3">
                        <select class="col-3 form-select" name="tipoUsuario" onchange="">
                            <option value="1">Administrador</option>
                            <option value="2">Profesional</option>
                            <option value="3">Paciente</option>
                        </select>
                    </div>

                </div>



                <div class="row">
                    <div class="d-flex justify-content-end col-7">
                        <button type="button" class="btn btn-outline-success btn-sm m-2" data-bs-toggle="modal" data-bs-target="#exampleModalPac" data-bs-whatever="@mdo">Datos Paciente</button>



                        <div class="modal fade" id="exampleModalPac" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Obra social</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <div class="mb-3">
                                                <label for="obraSocial" class="col-form-label" >Obra social</label>

                                                <select class="col-3 form-select" name="obraSocial" onchange="">
                                                    <option value="1">IAPOS</option>
                                                    <option value="2">Federada Salud</option>
                                                    <option value="3">Swiss Medical</option>
                                                    <option value="4">Esencial</option>
                                                    <option value="5">Previnca</option>
                                                </select>


                                            </div>
                                            <div class="mb-3">
                                                <label for="nroAfiliado" class="col-form-label">Numero de afiliado:</label>
                                                <input type="number" class="form-control" name="nroAfiliado"></textarea>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary btn-sm"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="button" class="btn btn-primary btn-sm">Guardar</button>
                                    </div>
                                </div>
                            </div>
                        </div>




                        <button type="button" class="btn btn-outline-success btn-sm m-2" data-bs-toggle="modal" data-bs-target="#exampleModalProf" data-bs-whatever="@mdo">Datos Profesional </button>

                        <div class="modal fade" id="exampleModalProf" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Datos profesional</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <div class="mb-3">
                                                <label for="matricula" class="col-form-label">Numero de matricula:</label>
                                                <input type="number" class="form-control" name="matricula">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary btn-sm"
                                                data-bs-dismiss="modal">Cerrar</button>
                                        <button type="button" class="btn btn-primary btn-sm">Guardar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="col-2">
                        <button type="submit" class="btn btn-outline-success btn-sm m-2">Guardar</button>
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-outline-success btn-sm m-2">Cancelar</button>
                    </div>

                </div>
            </form>
        </div>

    </div>



    <div class="row bg-danger">
        PIE
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page session="true" %>
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


                <h4 class="text-center my-4 mb-5 text-decoration-underline fw-bold ">Registro de practicas abonadas</h4>

                <div class="container">
                    <div class="row  justify-content-evenly">
                        <div class="mb-3 fw-bold">
                            Periodo facturado
                        </div>
                        <div class="col-2">
                            <label for="">Fecha desde:</label>
                        </div>

                        <div class="col-2">
                            <input type="date" class="form-control col-2 ml-4">
                        </div>

                        <div class="col-2">
                            <label for="">Fecha hasta:</label>
                        </div>

                        <div class="col-2">
                            <input type="date" class="form-control col-2">
                        </div>

                        <div class="col-3">
                            <button type="submit" class="btn btn-success">Buscar</button>
                        </div>
                    </div>

                </div>


                <hr class="mt-4 border border-success border-1 opacity-50">


                <div class="container">
                    <label for="" class="fw-bold mb-3">Practicas adeudadas de periodos anteriores</label>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Paciente</th>
                                <th scope="col">Practica</th>
                                <th scope="col">???</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row"> <input class="form-check-input" type="radio" name="radio1"
                                        id="flexRadioDefault2" checked> </th>

                                <td>1</td>
                                <td>Magnetoterapia</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th scope="row"> <input class="form-check-input" type="radio" name="radio2"
                                        id="flexRadioDefault2"></th>
                                <td>1</td>
                                <td>Ondas Rusas</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th scope="row"><input class="form-check-input" type="radio" name="radio3"
                                        id="flexRadioDefault2" checked></th>
                                <td>85</td>
                                <td>Ondas Rusas</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th scope="row"><input class="form-check-input" type="radio" name="radio4"
                                        id="flexRadioDefault2" checked></th>
                                <td>54</td>
                                <td>Ondas Rusas</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th scope="row"><input class="form-check-input" type="radio" name="radio5"
                                        id="flexRadioDefault2"></th>
                                <td>54</td>
                                <td>Ondas Rusas</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <th scope="row"><input class="form-check-input" type="radio" name="radio6"
                                        id="flexRadioDefault2" checked></th>
                                <td>21</td>
                                <td>Ondas Rusas</td>
                                <td>-</td>
                            </tr>


                        </tbody>
                    </table>


                    <div class="container mt-5">
                        <label for="" class="fw-bold mb-3">Practicas adeudadas de periodos anteriores</label>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col">Paciente</th>
                                    <th scope="col">Practica</th>
                                    <th scope="col">???</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio7"
                                            id="flexRadioDefault2" checked></th>
                                    <td>1</td>
                                    <td>Magnetoterapia</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio8"
                                            id="flexRadioDefault2"></th>
                                    <td>1</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio9"
                                            id="flexRadioDefault2" checked></th>
                                    <td>85</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio10"
                                            id="flexRadioDefault2" checked></th>
                                    <td>54</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio11"
                                            id="flexRadioDefault2" checked></th>
                                    <td>54</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio12"
                                            id="flexRadioDefault2"></th>
                                    <td>21</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio13"
                                            id="flexRadioDefault2"></th>
                                    <td>74</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio14"
                                            id="flexRadioDefault2"></th>
                                    <td>74</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio15"
                                            id="flexRadioDefault2"></th>
                                    <td>74</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <th scope="row"><input class="form-check-input" type="radio" name="radio16"
                                            id="flexRadioDefault2" checked></th>
                                    <td>74</td>
                                    <td>Ondas Rusas</td>
                                    <td>-</td>
                                </tr>


                            </tbody>
                        </table>



                        <div class="container">
                            <div class="row justify-content-end">

                                <div class="col-3">
                                    <button type="button" class="btn btn-success">Registrar pago</button>

                                    <!-- agregar el resumen de las practicas a abonar -->

                                </div>
                                <div class="col-2">
                                    <button type="button" class="btn btn-success">Cancelar</button>
                                </div>

                            </div>


                        </div>


                    </div>




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
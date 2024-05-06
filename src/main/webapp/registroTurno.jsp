<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>

            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                crossorigin="anonymous">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
                integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
            <link rel="stylesheet" href="css/styles.css">
            <meta charset="UTF-8">
    
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/styles.css">


        </head>

        <body>

            <% Integer rolUsuario=(Integer)session.getAttribute("rol"); pageContext.setAttribute( "rol" , rolUsuario);
                %>

                <div class="container-fluid">
                    <div class="row ">

                    </div>

                    <div class="row mt-1">
                        <div class="col-3 ">

                            <jsp:include page="menu_final.jsp" />

                        </div>

                        <div class="col-9">
                            <div class="container ">
                                <div class="col-8">
                                    <h5 class="small fst-italic text-right mt-3">Bienvenido <c:out
                                            value="${usuario.getApellido()} ${usuario.getNombre() }"></c:out>
                                    </h5>
                                </div>

                                <h4 class="text-center my-2 text-decoration-underline fw-bold">Registro de turno</h4>
                                <div class="row justify-content-center mt-3">
                                    <div class="card text-center">

                                        <div class="card-body mt-2">
                                            <div class="row mb-5">
                                                <c:if test="${rol==3}">
                                                    <div class="col-4">
                                                        <label class="fs-6  fst-italic fw-bold mb-3">Registra
                                                            prescripcion
                                                            vigente?</label>
                                                    </div>

                                                    <div class="col-4">
                                                        <div class="toggle-switch">
                                                            <input type="checkbox" id="toggle"
                                                                class="toggle-switch-checkbox" checked>
                                                            <label for="toggle" class="toggle-switch-label">
                                                                <span class="toggle-switch-inner"></span>
                                                                <span
                                                                    class="toggle-switch-text toggle-switch-active" >NO</span>
                                                                <span
                                                                    class="toggle-switch-text toggle-switch-inactive">SI</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>




                                            <div id="tablaActivos">
                                                <div class="row ms-4">
                                                    <div class="col-4">
                                                        <label class="fs-6  fst-italic fw-bold mb-3 ">Seleccione una
                                                            práctica</label>
                                                        <form action="registroTurno" method="post">
                                                            <input type="hidden" value="buscarProfesional"
                                                                name="operacion">

                                                            <select class="form-select mb-2" id="tablaActivos">
                                                                <c:forEach var="pr" items="${practicas}">
                                                                    <c:choose>
                                                                        <c:when
                                                                            test="${pr.id_practica eq practicaSeleccionada}">
                                                                            <option value="<c:out value="
                                                                                ${pr.id_practica}">
                                                                                </c:out>" selected><c:out
                                                                                    value="${pr.descripcion}">
                                                                                </c:out>
                                                                            </option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="<c:out value="
                                                                                ${pr.id_practica}">
                                                                                </c:out>"><c:out
                                                                                    value="${pr.descripcion}">
                                                                                </c:out>
                                                                            </option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </select>

                                                            <button type="submit"
                                                                class="btn btn-success btn-sm mb-5 ">Seleccionar</button>
                                                        </form>
                                                    </div>


                                                    <div class="col-4">
                                                        <label class="fs-6 fst-italic fw-bold mb-3">Seleccione un
                                                            profesional</label>

                                                        <form action="registroTurno" method="post">
                                                            <input type="hidden" value="buscarTurnos" name="operacion">

                                                            <select class="form-select mb-2" name="profesional">
                                                                <c:forEach var="prof" items="${profesionales}">
                                                                    <c:choose>
                                                                        <c:when
                                                                            test="${prof.matricula eq profesionalSeleccionado}">
                                                                            <option value="<c:out value="
                                                                                ${pr.id_practica}">
                                                                                </c:out>" selected><c:out
                                                                                    value="${pr.descripcion}">
                                                                                </c:out>
                                                                            </option>
                                                                            <option value="<c:out value="
                                                                                ${prof.matricula}">
                                                                                </c:out>" selected><c:out
                                                                                    value="${prof.apellido} , ${prof.nombre}">
                                                                                </c:out>
                                                                            </option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="<c:out value="
                                                                                ${prof.matricula}">
                                                                                </c:out>"><c:out
                                                                                    value="${prof.apellido} , ${prof.nombre}">
                                                                                </c:out>
                                                                            </option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </select>
                                                            <button type="submit"
                                                                class="btn btn-success btn-sm">Seleccionar</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>



                                            <c:if test="${rol=='1' or rol=='2'}">
                                                <div class="row">
                                                    <div class="col-4 ms-4">
                                                        <label class="fs-6 fst-italic fw-bold mb-3" for="">DNI
                                                            paciente</label>

                                                        <form action="registroTurno" method="post">
                                                            <input type="hidden" value="buscarPaciente"
                                                                name="operacion">
                                                            <c:choose>
                                                                <c:when test="${dniPacienteBuscado!=null}">
                                                                    <input type="number" class="form-control mb-2"
                                                                        name="dniPaciente" value="<c:out value="
                                                                        ${dniPacienteBuscado}"></c:out>">
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="number" class="form-control mb-2"
                                                                        name="dniPaciente">
                                                                </c:otherwise>
                                                            </c:choose>

                                                            <button type="submit"
                                                                class="btn btn-success btn-sm">Seleccionar</button>
                                                        </form>
                                                    </div>

                                                    <div class="col-6 p-5 fw-bold fs-5">
                                                        <c:if test="${paciente!=null}">
                                                            <c:out value="${paciente.apellido} ${paciente.nombre}">
                                                            </c:out>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </c:if>



                                            <div id="tablaInactivos" style="display: none;">
                                               
                                                <table class="table table-striped my-2">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Fecha Prescripcion</th>
                                                            <th scope="col">Paciente</th>
                                                            <th scope="col">Nro. Afiliado</th>
                                                            <th scope="col">Practica</th>
                                                            <th scope="col">Total Sesiones</th>
                                                            <th scope="col">Sesiones Pendientes</th>
                                                            <th scope="col">Fecha Fin Prescripcion</th>
                                                            <th scope="col"> Operaciones</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <!-- Aquí se mostrarán las prescripciones -->
                                                        <c:forEach var="prescripcion" items="${prescripciones}">
                                                            <tr>
                                                                <td>
                                                                    <c:out value="${prescripcion.fecha_prescripcion}" />
                                                                </td>
                                                                <td>
                                                                    <c:out
                                                                        value="${prescripcion.paciente.nombre} ${prescripcion.paciente.apellido}" />
                                                                </td>
                                                                <td>
                                                                    <c:out
                                                                        value="${prescripcion.paciente.nro_afiliado}" />
                                                                </td>
                                                                <td>
                                                                    <c:out
                                                                        value="${prescripcion.practica.descripcion}" />
                                                                </td>
                                                                <td>
                                                                    <c:out value="${prescripcion.cant_sesiones}" />
                                                                </td>
                                                                <td>
                                                                    <c:out
                                                                        value="${prescripcion.cant_sesiones - prescripcion.sesiones_asistidas}" />
                                                                </td>
                                                                <td>
                                                                    <c:out
                                                                        value="${prescripcion.fecha_baja_prescipcion}" />
                                                                </td>
                                                                <td>
                                                                    <div class="d-flex justify-content-center gap-4">
                                                                        <a
                                                                            href="consultaTurnos?idPrescripcion=${prescripcion.id_prescripcion}">
                                                                            <i class="bi bi-calendar-check m-1"></i>
                                                                        </a>
                                                                        <c:if
                                                                            test="${empty prescripcion.fecha_baja_prescipcion}">
                                                                            <%-- Agregué este if para solo ver las
                                                                                opciones de registrar turno y anular si
                                                                                la prescripcion --%>
                                                                                <a
                                                                                    href="registroTurno?idPrescripcion=${prescripcion.id_prescripcion}">
                                                                                    <i
                                                                                        class="bi bi-calendar-plus-fill m-1"></i>
                                                                                </a>
                                                                                <a href="#" data-bs-toggle="modal"
                                                                                    data-bs-target="#anularPrescripcion"
                                                                                    idPrescripcion="${prescripcion.id_prescripcion}"
                                                                                    paciente="${prescripcion.paciente.nombre} ${prescripcion.paciente.apellido}"
                                                                                    practica="${prescripcion.practica.descripcion}"
                                                                                    sesiones="${prescripcion.cant_sesiones}">
                                                                                    <i class="bi bi-trash-fill m-1"></i>
                                                                                </a>
                                                                        </c:if>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <c:if test="${turnos != null}">
                                    <div class="mt-4 container">
                                        <div class="row justify-content-center">
                                            <div class="col-12">
                                                <table class="table table-striped my-2 text-center">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Fecha turno</th>
                                                            <th scope="col">Hora turno</th>
                                                        </tr>
                                                    </thead>

                                                    <tbody>
                                                        <c:forEach var="tur" items="${turnos}">
                                                            <tr>
                                                                <td>

                                                                    <c:out value="${tur.fecha_t}">
                                                                    </c:out>
                                                                </td>
                                                                <td>

                                                                    <c:out value="${tur.hora_t}">
                                                                    </c:out>
                                                                </td>
                                                                <td>
                                                                    <a href='#' class="btn btn-success btn-sm"
                                                                        data-bs-toggle='modal'
                                                                        data-bs-target='#registrarTurno'
                                                                        idTurno="${tur.id_turno}"
                                                                        fecha_turno="${tur.fecha_t}"
                                                                        hora_turno="${tur.hora_t}">Reservar</a>
                                                                </td>

                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>
                                    </div>


                                    <div class="row justify-content-end">
                                        <div class="col-1">
                                            <a href='menu_final.jsp' class="btn btn-success btn-sm">Cancelar</a>
                                        </div>
                                    </div>
                                </c:if>
                            </div>


                            <!--VENTANA MODAL "RESERVAR TURNO" -->
                            <div class="modal fade" id="registrarTurno" tabindex="-1"
                                aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar
                                                reserva de turno</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                        </div>
                                        <form action="registroTurno" method="post">
                                            <div class="modal-body">
                                                <input type="hidden" value="registroTurno" name="operacion">
                                                <input type="hidden" name="idTurno" id="idTurno">

                                                <div class="mb-3">
                                                    <label class="fw-bold form-label col-6">Apellido y nombre
                                                        paciente</label>
                                                    <c:out value="${paciente.apellido} ${paciente.nombre}">
                                                    </c:out>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="fw-bold form-label col-6">DNI</label>
                                                    <c:out value="${paciente.dni}"></c:out>
                                                </div>
                                                <!--                                    <div class="mb-3"> -->
                                                <!--                                        <label class="fw-bold form-label col-6">Obra social</label>                                        -->
                                                <%-- <c:out value="${paciente.getObra_Social().getNombre_os()}">
                                                    </c:out> --%>
                                                    <!--                                    </div> -->

                                                    <div class="mb-3">
                                                        <label class="fw-bold form-label col-6">Numero de
                                                            afiliado</label>
                                                        <input type="hidden" value="${paciente.nro_afiliado}"
                                                            name="nroAfiliado">
                                                        <c:out value="${paciente.nro_afiliado}"></c:out>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label class="fw-bold form-label col-6">Fecha
                                                            turno</label>
                                                        <label class="form-label " id="fechaTurno"></label>
                                                    </div>


                                                    <div class="mb-3">
                                                        <label class="fw-bold form-label col-6">Hora
                                                            turno</label>
                                                        <label class="form-label " id="horaTurno"></label>
                                                    </div>





                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">Cancelar</button>
                                                        <button type="submit" class="btn btn-primary">Guardar</button>
                                                    </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <!--MENSAJE DE OPERACION -->
                            <c:if test="${mensaje !=null }">
                                <div class="modal fade" id="mensajeOK" tabindex="-1" aria-labelledby="exampleModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <p class="fs-5 fw-bold">${mensaje}<i
                                                        class="fa-solid fa-circle-info fa-2xl"
                                                        style="color: #FFD43B;"></i></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <script>new bootstrap.Modal(document.getElementById('mensajeOK')).show();</script>
                            </c:if>



                            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                                crossorigin="anonymous"></script>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
                                integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
                                crossorigin="anonymous"></script>
                            <script src="js/funciones_abm.js"></script>
                            <script src="js/toggle.js"></script>
        </body>

        </html>
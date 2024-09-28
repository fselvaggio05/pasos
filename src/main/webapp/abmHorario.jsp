<%@page import="entity.Horario"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- FullCalendar CSS desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/styles.css">
    <title>Gestion de Horarios</title>

    <!-- Estilos personalizados para el calendario -->
    <style>
        #calendario {
            width: 80%;
            max-width: 900px;
            margin: 0 auto;
            height: auto;
            min-height: 400px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row mt-1">
            <div class="col-3">
                <jsp:include page="menu_final.jsp"/>
            </div>
            <div class="col-9">
                <div class="container">
                    <!-- Calendario Mensual (Posicionado arriba del switch de horarios) -->
                    <div id="calendario" class="mb-5"></div>

                    <div class="row justify-content-center mt-3">
                        <div class="card text-center">
                            <!-- Switch para cambiar entre Horarios Activos e Inactivos -->
                            <div class="toggle-switch">
                                <input type="checkbox" id="toggle" class="toggle-switch-checkbox" checked>
                                <label for="toggle" class="toggle-switch-label">
                                    <span class="toggle-switch-inner"></span>
                                    <span class="toggle-switch-text toggle-switch-active">Activos</span>
                                    <span class="toggle-switch-text toggle-switch-inactive">Inactivos</span>
                                </label>
                            </div>
                            <div class="card-body">
                                <!-- Tabla de Horarios Activos -->
                                <table id="tablaActivos" class="table table-striped my-2">
                                    <thead>
                                        <tr>
                                            <th scope="col">Matricula</th>
                                            <th scope="col">Apellido</th>
                                            <th scope="col">Práctica</th>
                                            <th scope="col">Dia de la Semana</th>
                                            <th scope="col">Hora Desde</th>
                                            <th scope="col">Hora Hasta</th>
                                            <th scope="col">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="unHorario" items="${tablaHorariosActivos}">
                                            <tr>
                                                <td><c:out value="${unHorario.getProfesional().getMatricula()}"></c:out></td>
                                                <td><c:out value="${unHorario.getProfesional().getApellido()}"></c:out></td>
                                                <td><c:out value="${unHorario.getPractica().getDescripcion()}"></c:out></td>
                                                <td><c:out value="${unHorario.dia_semana}"></c:out></td>
                                                <td><c:out value="${unHorario.hora_desde}"></c:out></td>
                                                <td><c:out value="${unHorario.hora_hasta}"></c:out></td>
                                                <td>
                                                    <a href='#' data-bs-toggle='modal' data-bs-target='#eliminarHorario' idHorario="${unHorario.id_horario}">
                                                        <i class='bi bi-trash-fill m-1'></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <!-- Tabla de Horarios Inactivos -->
                                <table id="tablaInactivos" class="table table-striped my-2" style="display: none;">
                                    <thead>
                                        <tr>
                                            <th scope="col">Matricula</th>
                                            <th scope="col">Apellido</th>
                                            <th scope="col">Practica</th>
                                            <th scope="col">Dia de la Semana</th>
                                            <th scope="col">Hora Desde</th>
                                            <th scope="col">Hora Hasta</th>
                                            <th scope="col">Operaciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="unHorario" items="${tablaHorariosInactivos}">
                                            <tr>
                                                <td><c:out value="${unHorario.getProfesional().getMatricula()}"></c:out></td>
                                                <td><c:out value="${unHorario.getProfesional().getApellido()}"></c:out></td>
                                                <td><c:out value="${unHorario.getPractica().getDescripcion()}"></c:out></td>
                                                <td><c:out value="${unHorario.dia_semana}"></c:out></td>
                                                <td><c:out value="${unHorario.hora_desde}"></c:out></td>
                                                <td><c:out value="${unHorario.hora_hasta}"></c:out></td>
                                                <td>
                                                    <a href='#' data-bs-toggle='modal' data-bs-target='#revivirHorario' idHorario="${unHorario.id_horario}">
                                                        <i class='bi bi-heart-fill m-1'></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                                <div class="row justify-content-end">
                                    <button type="button" class="btn btn-success col-2 m-1" data-bs-toggle="modal" data-bs-target="#altaHorario">Agregar Horario</button>
                                    <button type="button" class="btn btn-success col-2 m-1">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal "Agregar Horario" -->
        <div class="modal fade" id="altaHorario" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Horario</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="horarios" method="post">
                        <div class="modal-body">
                            <input type="hidden" value="alta" name="operacion">
                            <div class="mb-3">
                                <select class="form-select m-1" name="matriculaProf">
                                    <option value="1">Seleccione un Profesional</option>
                                    <c:forEach var="unProfesional" items="${profesionales}">
                                        <option value="<c:out value="${unProfesional.matricula}"></c:out>">
                                            <c:out value="${unProfesional.apellido}, ${unProfesional.nombre}"></c:out>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="col-6">Práctica</label>
                                <select class="col-3 form-select" name="id_practica">
                                    <option value="1">Seleccione una Practica</option>
                                    <c:forEach var="unaPractica" items="${practicas}">
                                        <option value="${unaPractica.id_practica}"><c:out value="${unaPractica.descripcion}"></c:out></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="col-6">Daa de la Semana</label>
                                <select class="col-3 form-select" name="dia_semana">
                                    <option value="lunes">Lunes</option>
                                    <option value="martes">Martes</option>
                                    <option value="miércoles">Miércoles</option>
                                    <option value="jueves">Jueves</option>
                                    <option value="viernes">Viernes</option>
                                    <option value="sábado">Sábado</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="col-6">Hora Desde</label>
                                <input type="time" class="form-control col-6" name="hora_desde">
                            </div>
                            <div class="mb-3">
                                <label class="col-6">Hora Hasta</label>
                                <input type="time" class="form-control col-6" name="hora_hasta">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-success">Agregar Horario</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Script de FullCalendar -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                var calendarEl = document.getElementById('calendario');
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    initialView: 'dayGridMonth',
                    locale: 'es', // Configuración del idioma a español
                    headerToolbar: {
                       
                        
                        
                    },
                    buttonText: {
                        today: 'Hoy'  
                    },
                    events: [
                        {
                            title: 'Kasimatis - Magnetoterapia',
                            start: '2024-09-23T08:00:00',
                            end: '2024-09-23T10:00:00',
                            backgroundColor: '#ff0000',
                            textColor: 'white'
                        },
                    
                    ]
                });
                calendar.render();
            });
        </script>
    </div>
</body>
</html>
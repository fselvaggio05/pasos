<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="entity.Enumeradores.TipoUsuario" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/sidebar.css">
    <style>
      /* Estilos para la imagen de fondo */
      .background-image {
        position: fixed;
        bottom: 0;
        right: 0;
        width: 500px;
        height: 500px;
        opacity: 0.25; /* Opacidad del 65% */
        z-index: -1; /* Enviar al fondo */
      }
    </style>
  </head>
  <body>
    <% TipoUsuario rolUsuario = (TipoUsuario) session.getAttribute("rol"); 
      Integer codigoRolUsuario = rolUsuario != null ? rolUsuario.getCodigo() : null; 
      pageContext.setAttribute("rol", codigoRolUsuario); 
    %>
    <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
      <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
        <svg class="bi me-2" width="80" height="74"></svg>
        <img src="kr2png12.png" width="80" height="80" alt="Logo Clinica">
      </a>
      <ul class="list-unstyled ps-0">
        <li class="mb-1">
          <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#turnos-collapse" aria-expanded="false">Turnos</button>
          <div class="collapse" id="turnos-collapse">
            <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
              <c:if test="${rol=='1' or rol=='2' or rol=='3'}">
                <li><a href="registroTurno" class="link-dark rounded">Registrar turno</a></li>
              </c:if>
              <c:if test="${rol=='1' or rol=='2' or rol=='3'}">
                <li><a href="consultaTurnos" class="link-dark rounded">Consulta turnos</a></li>
              </c:if>
              <c:if test="${rol=='1' or rol=='2'}">
                <li><a href="registroAsistencia" class="link-dark rounded">Registrar asistencia</a></li>
              </c:if>
              <c:if test="${(rol=='1')}">
                <li><a href="prescripcion" class="link-dark rounded">Registrar prescripci�n</a></li>
              </c:if>
              <c:if test="${(rol=='1')}">
                <li><a href="generarAgendas" class="link-dark rounded">Generar agenda</a></li>
              </c:if>
            </ul>
          </div>
        </li>
        <c:if test="${rol=='1' or rol=='2'}">
          <li class="mb-1">
            <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#reportes-collapse" aria-expanded="false">Reportes</button>
            <div class="collapse" id="reportes-collapse">
              <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                <li><a href="listadoambulatorias" class="link-dark rounded">Facturaci�n Ambulatoria</a></li>
                <li><a href="listadodiscapacidad" class="link-dark rounded">Facturaci�n Plan Discapacidad</a></li>
                <li><a href="registroPagos" class="link-dark rounded">Registro pr�cticas abonadas</a></li>
              </ul>
            </div>
          </li>
        </c:if>
        <li class="border-top my-3"></li>
        <c:if test="${(rol=='1')}">
          <li class="mb-1">
            <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#admin-collapse" aria-expanded="false">Administrar</button>
            <div class="collapse" id="admin-collapse">
              <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                <li><a href="practicas" class="link-dark rounded">Gesti�n Pr�cticas Profesionales</a></li>
                <li><a href="equipos" class="link-dark rounded">Gesti�n Equipos</a></li>
                <li><a href="consultorios" class="link-dark rounded">Gesti�n Consultorios</a></li>
                <li><a href="obrassociales" class="link-dark rounded">Gesti�n Obras Sociales</a></li>
                <li><a href="usuarios" class="link-dark rounded">Gesti�n Usuarios</a></li>
                <li><a href="horarios" class="link-dark rounded">Gesti�n Horarios</a></li>
              </ul>
            </div>
          </li>
        </c:if>
      </ul>    
      <div class="border-top my-3">
        <a href="login" class="btn btn-toggle align-items-center rounded collapsed text-decoration-underline">Cerrar Sesi�n</a>
      </div>
    </div>
    
    <!-- Imagen alineada al fondo -->
    <img src="turnos.png" alt="Imagen Turnos" class="background-image">
    
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"></script>
  </body>
</html>

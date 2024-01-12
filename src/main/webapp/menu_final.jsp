<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="/Vistas Proyecto ING/sidebars.css">

</head>


<body>
<div class="flex-shrink-0 p-3 bg-white" style="width: 280px;" >
  <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
    <svg class="bi me-2" width="30" height="24"></svg>
    <img src="kr2png12.png" width="80" height="80" alt="Logo Clinica">
  </a>
  <ul class="list-unstyled ps-0">


    <li class="mb-1">
      <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#turnos-collapse" aria-expanded="false">
        Turnos
      </button>
      <div class="collapse" id="turnos-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="09 - registroTurno.jsp" class="link-dark rounded">Registrar turno</a></li>
          <li><a href="#" class="link-dark rounded">Consulta turnos</a></li>
          <li><a href="#" class="link-dark rounded">Registrar asistencia</a></li>
          <li><a href="#" class="link-dark rounded">Generar agenda</a></li>
        </ul>
      </div>
    </li>



    <li class="mb-1">
      <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#reportes-collapse" aria-expanded="false">
        Reportes
      </button>
      <div class="collapse" id="reportes-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="#" class="link-dark rounded">Facturacion Ambulatoria</a></li>
          <li><a href="#" class="link-dark rounded">Facturacion Plan Discapacidad</a></li>
        </ul>
      </div>
    </li>




    <li class="border-top my-3"></li>

    <li class="mb-1">
      <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#admin-collapse" aria-expanded="false">
        Administrar
      </button>
      <div class="collapse" id="admin-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="altaPractica.jsp" class="link-dark rounded">Gestión Practicas profesionales</a></li>
          <li><a href="altaEquipo.html" class="link-dark rounded">Gestión Equipos</a></li>
          <li><a href="altaConsultorio.html" class="link-dark rounded">Gestión Consultorios</a></li>
          <li><a href="altaUsuario_admin.jsp" class="link-dark rounded">Gestión Usuarios</a></li>
          <li><a href="altaHorario.html" class="link-dark rounded">Gestión Horarios</a></li>
        </ul>
      </div>
    </li>
  </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
  <link rel="stylesheet" href="css/sidebar.css">

</head>


<body>
<div class="flex-shrink-0 p-3 bg-white" style="width: 280px;" >
  <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
    <svg class="bi me-2"  width="80" height="74" ></svg>
    <img src="kr2png12.png" width="80" height="80" alt="Logo Clinica">
  </a>
  <ul class="list-unstyled ps-0">


    <li class="mb-1">
      <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#turnos-collapse" aria-expanded="false">
        Turnos
      </button>
      <div class="collapse" id="turnos-collapse">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">         
          <li><a href="#" class="link-dark rounded">Consulta turnos</a></li>          
        </ul>
      </div>
    </li>

  </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" ></script>
</body>
</html>
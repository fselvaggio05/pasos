
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>

<section class="" style="background-color: #c3e3cd;">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card shadow-2-strong" style="border-radius: 1rem;">
            <div class="card-body p-5 text-center">

              <h3 class="mb-5">Bienvenido</h3>

              <form action="login" method="POST">
              <div class="form-outline mb-3">
                <input type="email" name="mail" class="form-control form-control-lg" placeholder="Usuario@mail.com" />

              </div>

              <div class="form-outline mb-3">
                <input type="password" name="pass" class="form-control form-control-lg" placeholder="Clave"/>

              </div>


              <!-- Checkbox -->
              <div>
                <input class="mr-1 form-check-input" type="checkbox" value="" id="defaultCheck1">Recordar usuario

              </div>

              <button class="btn btn-primary btn-lg btn-block" type="submit">Acceder</button>

              <hr class="my-4">
                  <% if (request.getAttribute("error")!=null)
                  {
                      out.println("<div class='text-danger'>");
                      out.println(request.getAttribute("error"));
                      out.println("</div>");
                  }
                  %>
            </form>



            </div>
          </div>
        </div>
      </div>
    </div>
  </section>


</body>
</html>
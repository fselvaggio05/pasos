document.addEventListener("DOMContentLoaded", function() {
    // Obtener referencias a las tablas
    var tablaAdministradores = document.getElementById("tablaAdministradores");
    var tablaProfesionales = document.getElementById("tablaProfesionales");
    var tablaPacientes = document.getElementById("tablaPacientes");

    // Obtener referencias a los botones de radio
    var administradorRadio = document.getElementById("administrador");
    var profesionalRadio = document.getElementById("profesional");
    var pacienteRadio = document.getElementById("paciente");

    // Obtener referencia al campo oculto de tipoUsuario
    var tipoUsuarioHidden = document.getElementById("tipoUsuarioHidden");

    // Función para mostrar la tabla correspondiente
    function mostrarTabla(tabla) {
        // Ocultar todas las tablas
        tablaAdministradores.style.display = "none";
        tablaProfesionales.style.display = "none";
        tablaPacientes.style.display = "none";
        // Mostrar la tabla específica pasada como argumento
        tabla.style.display = "table";
    }

    // Función para mostrar los campos de alta correspondientes al tipo de usuario seleccionado
    function mostrarCamposAlta() {
        // Mostrar siempre los campos de administrador
        var camposAdministrador = document.getElementById('camposAdministrador');
        camposAdministrador.style.display = 'block';

        // Ocultar campos adicionales
        var camposProfesional = document.getElementById('camposProfesional');
        var camposPaciente = document.getElementById('camposPaciente');
        camposProfesional.style.display = 'none';
        camposPaciente.style.display = 'none';

        // Mostrar campos adicionales según el tipo de usuario seleccionado
        if (profesionalRadio.checked) {
            // Si es profesional, mostrar campos profesionales y cambiar valor de tipoUsuario a 2
            camposProfesional.style.display = 'block';
            tipoUsuarioHidden.value = "2"; // Cambiar valor a 2 para profesional
        } else if (pacienteRadio.checked) {
            // Si es paciente, mostrar campos de paciente y cambiar valor de tipoUsuario a 3
            camposPaciente.style.display = 'block';
            tipoUsuarioHidden.value = "3"; // Cambiar valor a 3 para paciente
        } else {
            // Si es administrador, mantener campos de administrador y valor de tipoUsuario como 1
            tipoUsuarioHidden.value = "1"; // Mantener valor como 1 para administrador
        }
    }

    // Manejar eventos de cambio en los botones de radio
    administradorRadio.addEventListener("change", function() {
        // Cuando cambia a administrador, mostrar la tabla de administradores y los campos de alta
        mostrarTabla(tablaAdministradores);
        mostrarCamposAlta();
    });

    profesionalRadio.addEventListener("change", function() {
        // Cuando cambia a profesional, mostrar la tabla de profesionales y los campos de alta
        mostrarTabla(tablaProfesionales);
        mostrarCamposAlta();
    });

    pacienteRadio.addEventListener("change", function() {
        // Cuando cambia a paciente, mostrar la tabla de pacientes y los campos de alta
        mostrarTabla(tablaPacientes);
        mostrarCamposAlta();
    });

    // Mostrar la tabla inicialmente según el botón de radio seleccionado
    if (administradorRadio.checked) {
        // Si está seleccionado administrador, mostrar tabla de administradores y campos de alta
        mostrarTabla(tablaAdministradores);
        mostrarCamposAlta();
    } else if (profesionalRadio.checked) {
        // Si está seleccionado profesional, mostrar tabla de profesionales y campos de alta
        mostrarTabla(tablaProfesionales);
        mostrarCamposAlta();
    } else if (pacienteRadio.checked) {
        // Si está seleccionado paciente, mostrar tabla de pacientes y campos de alta
        mostrarTabla(tablaPacientes);
        mostrarCamposAlta();
    }

    // Función para mostrar los campos de edición correspondientes al tipo de usuario seleccionado
    function mostrarCamposEdicion(tipoUsuario) {
        // Mostrar campos de administrador siempre
        camposAdministradorUpdate.style.display = "block";

        // Ocultar campos de profesional y paciente
        camposProfesionalUpdate.style.display = "none";
        camposPacienteUpdate.style.display = "none";

        // Mostrar campos de profesional o paciente según el tipo de usuario seleccionado
        if (tipoUsuario === "2") {
            // Profesional
            camposProfesionalUpdate.style.display = "block";
			tipoUsuarioUpdate.value="2";
        } else if (tipoUsuario === "3") {
            // Paciente
            camposPacienteUpdate.style.display = "block";
			tipoUsuarioUpdate.value="3";
        } else{
		tipoUsuarioUpdate.value="1";
		}
    }

    // Manejar evento de cambio en el radio button para actualizar tipo de usuario en el modal de edición
    document.querySelectorAll('input[type="radio"][name="tipoUsuario"]').forEach(function(radio) {
        radio.addEventListener("change", function() {
            tipoUsuarioHidden.value = this.value;
            mostrarCamposEdicion(this.value);
        });
    });

    // Manejar evento de clic en los enlaces de edición en las tablas
document.querySelectorAll('.editarUsuario').forEach(function(enlace) {
    enlace.addEventListener("click", function(event) {
        // Evitar que el enlace siga el enlace
        event.preventDefault();

        // Obtener los valores relevantes del registro seleccionado
        var tipoUsuario = this.getAttribute("tipoUsuario");
        var dni = this.getAttribute("data-dni");
        var apellido = this.getAttribute("data-apellido");
        var nombre = this.getAttribute("data-nombre");
        var fechaNacimiento = this.getAttribute("data-fecha-nacimiento");
        var genero = this.getAttribute("data-genero");
        var telefono = this.getAttribute("data-telefono");
        var email = this.getAttribute("data-email");
        var matricula = this.getAttribute("data-matricula");
        var id_obra_social = this.getAttribute("data-id-obra-social");
        var nro_afiliado = this.getAttribute("data-nro-afiliado");

        // Comprobar los valores obtenidos
        console.log("DNI:", dni);
        console.log("Apellido:", apellido);
        console.log("Nombre:", nombre);
        console.log("Fecha de Nacimiento:", fechaNacimiento);
        console.log("Género:", genero);
        console.log("Teléfono:", telefono);
        console.log("Email:", email);
        console.log("Matricula:", matricula);
        console.log("ID Obra Social:", id_obra_social);
        console.log("Nro. Afiliado:", nro_afiliado);

        // Completar los campos del modal de edición con los valores obtenidos
        document.querySelector("#camposAdministradorUpdate input[name='dni']").value = dni;
        document.querySelector("#camposAdministradorUpdate input[name='apellido']").value = apellido;
        document.querySelector("#camposAdministradorUpdate input[name='nombre']").value = nombre;
        document.querySelector("#camposAdministradorUpdate input[name='fechaNacimiento']").value = fechaNacimiento;
        document.querySelector("#camposAdministradorUpdate select[name='genero']").value = genero;
        document.querySelector("#camposAdministradorUpdate input[name='telefono']").value = telefono;
        document.querySelector("#camposAdministradorUpdate input[name='email']").value = email;
        document.querySelector("#camposProfesionalUpdate input[name='matricula']").value = matricula;
        document.querySelector("#camposPacienteUpdate input[name='nro_afiliado']").value = nro_afiliado;

        // Obtener todas las opciones del desplegable
        var opciones = document.querySelectorAll("#camposPacienteUpdate select[name='id_obra_social'] option");

        // Iterar sobre las opciones
        opciones.forEach(function(opcion) {
            // Comparar el valor de la opción con el ID de la obra social
            if (opcion.value === id_obra_social) {
                // Establecer la opción como seleccionada
                opcion.selected = true;
            }
        });

        // Mostrar el modal de edición
        var modal = new bootstrap.Modal(document.getElementById('actualizarUsuario'));
        modal.show();
    });
});

    // Mostrar los campos de edición inicialmente según el tipo de usuario seleccionado
    mostrarCamposEdicion(tipoUsuarioHidden.value);
});

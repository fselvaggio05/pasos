document.addEventListener("DOMContentLoaded", function() {
    // Obtener referencias a las tablas
    var tablaAdministradores = document.getElementById("tablaAdministradores");
    var tablaProfesionales = document.getElementById("tablaProfesionales");
    var tablaPacientes = document.getElementById("tablaPacientes");
    
    // Obtener referencias a los botones de radio
    var administradorRadio = document.getElementById("administrador");
    var profesionalRadio = document.getElementById("profesional");
    var pacienteRadio = document.getElementById("paciente");

    // Función para mostrar la tabla correspondiente
    function mostrarTabla(tabla) {
        tablaAdministradores.style.display = "none";
        tablaProfesionales.style.display = "none";
        tablaPacientes.style.display = "none";
        tabla.style.display = "table";
    }

    // Función para mostrar los campos de alta correspondientes al tipo de usuario seleccionado
    function mostrarCamposAlta() {
        var tipoUsuarioSeleccionado;

        // Obtener el valor del tipo de usuario seleccionado
        if (administradorRadio.checked) {
            tipoUsuarioSeleccionado = '1';
        } else if (profesionalRadio.checked) {
            tipoUsuarioSeleccionado = '2';
        } else if (pacienteRadio.checked) {
            tipoUsuarioSeleccionado = '3';
        }

        // Mostrar siempre los campos de administrador
        var camposAdministrador = document.getElementById('camposAdministrador');
        camposAdministrador.style.display = 'block';

        // Mostrar campos adicionales según el tipo de usuario seleccionado
        switch (tipoUsuarioSeleccionado) {
            case '1':
                // No se necesita agregar campos adicionales para administrador
                break;
            case '2':
                // Mostrar campos adicionales para profesional
                var camposProfesional = document.getElementById('camposProfesional');
                camposProfesional.style.display = 'block';
                break;
            case '3':
                // Mostrar campos adicionales para paciente
                var camposPaciente = document.getElementById('camposPaciente');
                camposPaciente.style.display = 'block';
                break;
            default:
                // Si no se selecciona ningún tipo de usuario, no hacer nada
                break;
        }
    }

    // Manejar eventos de cambio en los botones de radio
    administradorRadio.addEventListener("change", function() {
        mostrarTabla(tablaAdministradores);
        mostrarCamposAlta();
    });

    profesionalRadio.addEventListener("change", function() {
        mostrarTabla(tablaProfesionales);
        mostrarCamposAlta();
    });

    pacienteRadio.addEventListener("change", function() {
        mostrarTabla(tablaPacientes);
        mostrarCamposAlta();
    });

    // Mostrar la tabla inicialmente según el botón de radio seleccionado
    if (administradorRadio.checked) {
        mostrarTabla(tablaAdministradores);
    } else if (profesionalRadio.checked) {
        mostrarTabla(tablaProfesionales);
    } else if (pacienteRadio.checked) {
        mostrarTabla(tablaPacientes);
    }

    // Mostrar campos de alta al hacer clic en el botón
    var btnMostrarCampos = document.getElementById("btnMostrarCampos");
    if (btnMostrarCampos) {
        btnMostrarCampos.addEventListener("click", function() {
            mostrarCamposAlta();
        });
    }
});

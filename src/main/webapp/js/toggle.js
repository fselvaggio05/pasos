
    document.addEventListener("DOMContentLoaded", function() {
        const toggleSwitch = document.getElementById("toggle");
        
        toggleSwitch.addEventListener("change", function() {
            const estadoToggle = this.checked ? "activos" : "inactivos";
            
            // Mostrar u ocultar las tablas según el estado del toggle
            if (estadoToggle === "activos") {
                document.getElementById("tablaActivos").style.display = "table";
                document.getElementById("tablaInactivos").style.display = "none";
            } else {
                document.getElementById("tablaActivos").style.display = "none";
                document.getElementById("tablaInactivos").style.display = "table";
            }
        });
    });

    document.querySelectorAll('input[name="tipoUsuario"]').forEach(function(radio) {
        radio.addEventListener('change', function() {
            var partePaciente = document.getElementById('partePaciente');
            var parteProfesional = document.getElementById('parteProfesional');

            if (this.value === '3') {
                partePaciente.style.display = 'block';
                parteProfesional.style.display = 'none';
            } else if (this.value === '2') {
                partePaciente.style.display = 'none';
                parteProfesional.style.display = 'block';
            } else {
                partePaciente.style.display = 'none';
                parteProfesional.style.display = 'none';
            }
        });
    });
    
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

    // Manejar eventos de cambio en los botones de radio
    administradorRadio.addEventListener("change", function() {
        mostrarTabla(tablaAdministradores);
    });

    profesionalRadio.addEventListener("change", function() {
        mostrarTabla(tablaProfesionales);
    });

    pacienteRadio.addEventListener("change", function() {
        mostrarTabla(tablaPacientes);
    });

    // Mostrar la tabla inicialmente según el botón de radio seleccionado
    if (administradorRadio.checked) {
        mostrarTabla(tablaAdministradores);
    } else if (profesionalRadio.checked) {
        mostrarTabla(tablaProfesionales);
    } else if (pacienteRadio.checked) {
        mostrarTabla(tablaPacientes);
    }
});


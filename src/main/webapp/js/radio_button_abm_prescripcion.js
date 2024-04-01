    document.addEventListener("DOMContentLoaded", function() {
        const radioAmbulatorias = document.getElementById("radioAmbulatorias");
        const radioDiscapacidad = document.getElementById("radioDiscapacidad");
        const tablaAmbulatorias = document.getElementById("tablaAmbulatorias");
        const tablaDiscapacidad = document.getElementById("tablaDiscapacidad");

        // Función para mostrar las prescripciones ambulatorias
        function mostrarAmbulatorias() {
            tablaAmbulatorias.style.display = "block";
            tablaDiscapacidad.style.display = "none";
        }

        // Función para mostrar las prescripciones de discapacidad
        function mostrarDiscapacidad() {
            tablaAmbulatorias.style.display = "none";
            tablaDiscapacidad.style.display = "block";
        }

        // Event listeners para los radio buttons
        radioAmbulatorias.addEventListener("change", function() {
            if (this.checked) {
                mostrarAmbulatorias();
            }
        });

        radioDiscapacidad.addEventListener("change", function() {
            if (this.checked) {
                mostrarDiscapacidad();
            }
        });

        // Mostrar la tabla inicialmente según el valor seleccionado por defecto
        if (radioAmbulatorias.checked) {
            mostrarAmbulatorias();
        } else {
            mostrarDiscapacidad();
        }
    });
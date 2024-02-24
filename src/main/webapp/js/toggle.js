
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

   


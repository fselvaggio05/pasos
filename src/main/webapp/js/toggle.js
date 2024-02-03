document.addEventListener("DOMContentLoaded", function() {
    const toggleSwitch = document.getElementById("toggle");
    
    toggleSwitch.addEventListener("change", function() {
        const estadoToggle = this.checked ? "activos" : "inactivos";
        
        // Mostrar u ocultar las tablas según el estado del toggle
        if (estadoToggle === "activos") {
            document.getElementById("tablaEquiposActivos").style.display = "table";
            document.getElementById("tablaEquiposInactivos").style.display = "none";
        } else {
            document.getElementById("tablaEquiposActivos").style.display = "none";
            document.getElementById("tablaEquiposInactivos").style.display = "table";
        }
    });
});




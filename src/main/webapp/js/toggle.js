
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

document.addEventListener('DOMContentLoaded', function() {
    formatearMontos('tablaActivos');
    formatearMontos('tablaInactivos');
});

function formatearMontos(tablaId) {
    var montoElements = document.querySelectorAll('#' + tablaId + ' tbody tr td:nth-child(5)');
    montoElements.forEach(function(element) {
        var monto = element.textContent.trim();
        var montoFormateado = formatearMonto(monto);
        element.textContent = montoFormateado;
    });
}

function formatearMonto(monto) {
    return parseFloat(monto).toLocaleString(undefined, { minimumFractionDigits: 2 });
}
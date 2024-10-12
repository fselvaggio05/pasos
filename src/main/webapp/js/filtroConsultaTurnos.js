// Filtro búsqueda turnos 
const filtroTurno = document.getElementById('filtro');

// Función para mostrar el campo correspondiente según el valor del select
function mostrarCampo(valor) {
    ocultarCampos();
    if (valor > -1) {
        document.getElementById(`filtro-${valor}`).style.display = 'block';
        document.getElementById('btnBuscar').style.display = 'block';
    }
}

// Ocultar todos los campos al inicio
function ocultarCampos() {
    document.getElementById('filtro-1').style.display = 'none';
    document.getElementById('filtro-2').style.display = 'none';
    document.getElementById('filtro-3').style.display = 'none';
    document.getElementById('btnBuscar').style.display = 'none';
}

// Verificar el valor seleccionado al cargar la página
window.addEventListener('DOMContentLoaded', () => {
    const valorSeleccionado = filtroTurno.value;
    mostrarCampo(valorSeleccionado);  // Mostrar el campo según el valor inicial
});

// Escuchar el cambio en el select para mostrar el campo correspondiente
filtroTurno.addEventListener('change', () => {
    mostrarCampo(filtroTurno.value);
});



//trae el modal mediante su id 
const actualizarEquipo = document.getElementById('actualizarEquipo')

// verifica que exista el modal, si existe ingresa al if
if (actualizarEquipo) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  actualizarEquipo.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idEquipo = a.getAttribute('idEquipo');
    //console.log(idPractica);
    const tipoEquipo=a.getAttribute('tipoEquipo');
    const descripcion=a.getAttribute('descEquipo');
    
	console.log(descripcion);
        
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdEquipo = actualizarEquipo.querySelector('.modal-body #idEquipo');
    const inputTipoEquipo = actualizarEquipo.querySelector('.modal-body #tipoEquipo');
    const inputDescripcion = actualizarEquipo.querySelector('.modal-body #descEquipo');    

	//asigno los valores capturados para mostrarlos
    inputIdEquipo.value = idEquipo;
    inputTipoEquipo.value = tipoEquipo;
    inputDescripcion.value = descripcion;
    
    
  })
}
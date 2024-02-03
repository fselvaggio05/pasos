

//trae el modal mediante su id 
const actualizarEquipo = document.getElementById('actualizarEquipo')
const actualizarConsultorio = document.getElementById('actualizarConsultorio');

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

if (actualizarConsultorio) {
    // Agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
    actualizarConsultorio.addEventListener('show.bs.modal', event => {
        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idConsultorio = a.getAttribute('idConsultorio');
        const descripcion = a.getAttribute('descConsultorio');
        
        // Traigo el formulario dentro del modal
        const form = actualizarConsultorio.querySelector('form');
        
        // Busco los campos en donde voy a mostrar los datos dentro del formulario
        const inputIdConsultorio = form.querySelector('#idConsultorio');
        const inputDescripcion = form.querySelector('#descConsultorio');    

        // Asigno los valores capturados para mostrarlos
        inputIdConsultorio.value = idConsultorio;
        inputDescripcion.value = descripcion;
    });
}
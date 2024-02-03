//trae el modal mediante su id 
const eliminarEquipo = document.getElementById('eliminarEquipo');

// verifica que exista el modal, si existe ingresa al if
if (eliminarEquipo) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  eliminarEquipo.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idEquipo = a.getAttribute('idEquipo');    
    const descripcion=a.getAttribute('descEquipo');

	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdEquipo = eliminarEquipo.querySelector('.modal-body #idEquipo');
    const inputDescripcion = eliminarEquipo.querySelector('.modal-body #descEquipo');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdEquipo.value = idEquipo;
    inputDescripcion.innerHTML = descripcion;   
  })
}

const eliminarConsultorio = document.getElementById('eliminarConsultorio');
if (eliminarConsultorio) {
    // Agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
    eliminarConsultorio.addEventListener('show.bs.modal', event => {
        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idConsultorio = a.getAttribute('idConsultorio');    
        const descripcion = a.getAttribute('descConsultorio');

        console.log(descripcion);
        
        // Traigo los campos en donde voy a mostrar los datos 
        const inputIdConsultorio = eliminarConsultorio.querySelector('.modal-body #idConsultorio');
        const inputDescripcion = eliminarConsultorio.querySelector('.modal-body #descConsultorio');

        // Asigno los valores capturados para mostrarlos
        inputIdConsultorio.value = idConsultorio;
        inputDescripcion.innerHTML = descripcion;   
    })
}

const revivirEquipo = document.getElementById('revivirEquipo')
if(revivirEquipo){
	revivirEquipo.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idEquipo = a.getAttribute('idEquipo');    
    const descripcion=a.getAttribute('descEquipo');

	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdEquipo = revivirEquipo.querySelector('.modal-body #idEquipo');
    const inputDescripcion = revivirEquipo.querySelector('.modal-body #descEquipo');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdEquipo.value = idEquipo;
    inputDescripcion.innerHTML = descripcion;   
  })
}

const revivirConsultorio= document.getElementById('revivirConsultorio');
if(revivirConsultorio){
	revivirConsultorio.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idConsultorio = a.getAttribute('idConsultorio');    
    const descripcion=a.getAttribute('descConsultorio');

	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdConsultorio = revivirConsultorio.querySelector('.modal-body #idConsultorio');
    const inputDescripcion = revivirConsultorio.querySelector('.modal-body #descConsultorio');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdConsultorio.value = idConsultorio;
    inputDescripcion.innerHTML = descripcion;   
  })
}

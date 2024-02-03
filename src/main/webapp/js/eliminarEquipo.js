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
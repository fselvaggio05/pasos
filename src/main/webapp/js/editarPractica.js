

//trae el modal mediante su id 
const actualizarPractica = document.getElementById('actualizarPractica')

// verifica que exista el modal, si existe ingresa al if
if (actualizarPractica) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  actualizarPractica.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idPractica = a.getAttribute('idPractica')
    //console.log(idPractica);
    const descripcion=a.getAttribute('descPractica');
    const equipo=a.getAttribute('equipo');
    
	console.log(descripcion);
    
   
    
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdPractica = actualizarPractica.querySelector('.modal-body #idPractica');
    const inputDescripcion = actualizarPractica.querySelector('.modal-body #descPractica');
    //const selectEquipo = 
    
    

	//asigno los valores capturados para mostrarlos
    inputIdPractica.value = idPractica;
    inputDescripcion.value = descripcion;
    selectEquipo = equipo;
    
    
  })
}
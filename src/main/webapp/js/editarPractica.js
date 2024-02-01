

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
    const descripcion=a.getAttribute('descPractica');
    const duracion = a.getAttribute('duracion');
    const equipo = a.getAttribute('equipo');
    
    console.log(idPractica);
	console.log(duracion);
	console.log(equipo);
    
   
    
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdPractica = actualizarPractica.querySelector('.modal-body #idPractica');
    const inputDescripcion = actualizarPractica.querySelector('.modal-body #descPractica');
    const inputDuracion = actualizarPractica.querySelector('.modal-body #duracion');
    const selectEquipo = actualizarPractica.querySelector('.modal-body #equipo');
    
    

	//asigno los valores capturados para mostrarlos
    inputIdPractica.value = idPractica;
    inputDescripcion.value = descripcion;
    inputDuracion.value = duracion;
    selectEquipo.value = equipo;
    
    
  })
}
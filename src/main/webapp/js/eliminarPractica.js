

//trae el modal mediante su id 
const eliminarPractica = document.getElementById('eliminarPractica')

// verifica que exista el modal, si existe ingresa al if
if (eliminarPractica) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  eliminarPractica.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idPractica = a.getAttribute('idPractica')
    
    const descripcion=a.getAttribute('descPractica');
    
    
	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdPractica = eliminarPractica.querySelector('.modal-body #idPractica');
    const inputDescripcion = eliminarPractica.querySelector('.modal-body #descPractica');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdPractica.value = idPractica;
    inputDescripcion.innerHTML = descripcion;
   
    
    
  })
}
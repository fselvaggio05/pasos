/**
 script para activar o desactivar 
 */

//trae el modal mediante su id 
const activar = document.getElementById('activar')

// verifica que exista el modal, si existe ingresa al if
if (activar) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  activar.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const id = a.getAttribute('id')
	const descripcion = a.getAttribute('descripcion')
    
	console.log(id);
	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputId = activar.querySelector('.modal-body #idEnviado');
    const divId = activar.querySelector('.modal-body #idMostrar');
       
        
	//asigno los valores capturados para mostrarlos
    inputId.value = id;
    divId.innerHTML = id;    
  })
}
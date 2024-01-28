


//id="${hor.id_horario}" practica="${hor.descripcion}" dia_semana="${hor.dia_semana}" hora_desde="${hor.hora_desde}" hora_hasta="${hor.hora_hasta}"

//trae el modal mediante su id 
const editar = document.getElementById('editar')

// verifica que exista el modal, si existe ingresa al if
if (editar) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  editar.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    
    const id = a.getAttribute('id')
    const practica = a.getAttribute('practica')
    const dia_semana = a.getAttribute('dia_semana')
    const hora_desde = a.getAttribute('hora_desde')
    const hora_hasta = a.getAttribute('hora_hasta')
       
	console.log(id);
	console.log(practica);
	console.log(hora_desde);
    
   
    
	//traigo los campos en donde voy a mostrar los datos 
	const inputIdHorario = editar.querySelector('.modal-body #id_horario')
    const selectPractica = editar.querySelector('.modal-body #id_practica');
    const selectDiaSemana = editar.querySelector('.modal-body #dia_semana');
    const inputHoraDesde = editar.querySelector('.modal-body #hora_desde');
    const inputHoraHasta = editar.querySelector('.modal-body #hora_hasta');
    
    
    console.log(inputHoraDesde)
    

	//asigno los valores capturados para mostrarlos
	inputIdHorario.value = id;
    selectPractica.value = practica;
    selectDiaSemana.value = dia_semana;
    inputHoraDesde.value = hora_desde;
    inputHoraHasta.value = hora_hasta;
    
    
    
  })
}
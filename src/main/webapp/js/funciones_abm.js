//Practicas 
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

const eliminarPractica = document.getElementById('eliminarPractica');
if (eliminarPractica) {
    // Agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
    eliminarPractica.addEventListener('show.bs.modal', event => {
        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idPractica = a.getAttribute('idPractica');    
        const descripcion = a.getAttribute('descPractica');

        console.log(descripcion);
        
        // Traigo los campos en donde voy a mostrar los datos 
        const inputIdPractica = eliminarPractica.querySelector('.modal-body #idPractica');
        const inputDescripcion = eliminarPractica.querySelector('.modal-body #descPractica');

        // Asigno los valores capturados para mostrarlos
        inputIdPractica.value = idPractica;
        inputDescripcion.innerHTML = descripcion;   
    })
}

const revivirPractica= document.getElementById('revivirPractica');
if(revivirPractica){
	revivirPractica.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idPractica = a.getAttribute('idPractica');    
    const descripcion=a.getAttribute('descPractica');

	console.log(descripcion);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdPractica = revivirPractica.querySelector('.modal-body #idPractica');
    const inputDescripcion = revivirPractica.querySelector('.modal-body #descPractica');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdPractica.value = idPractica;
    inputDescripcion.innerHTML = descripcion;   
  })
}

//Equipos
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


//Consultorios
const actualizarConsultorio = document.getElementById('actualizarConsultorio')
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

//Horarios
const eliminarHorario = document.getElementById('eliminarHorario');
if (eliminarHorario) {
    // Agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
    eliminarHorario.addEventListener('show.bs.modal', event => {
        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idHorario = a.getAttribute('idHorario');
        
        // Traigo los campos en donde voy a mostrar los datos 
        const inputIdHorario = eliminarHorario.querySelector('.modal-body #idHorario');

        // Asigno los valores capturados para mostrarlos
        inputIdHorario.value = idHorario;   
    })
}

const revivirHorario = document.getElementById('revivirHorario');
if (revivirHorario) {
    revivirHorario.addEventListener('show.bs.modal', event => {
        const a = event.relatedTarget;
        const idHorario = a.getAttribute('idHorario');
        const inputIdHorario = revivirHorario.querySelector('.modal-body #idHorario');
        inputIdHorario.value = idHorario;   
    });
}


//Registro turno - Recupera ID Turno de la tabla
const registrarTurno = document.getElementById('registrarTurno')
if(registrarTurno)
{
	function seleccionRadioTurno() {
            var e = document.getElementsByName('idTurnoTabla');
            let valorSeleccionado;
 
            for (i = 0; i < e.length; i++) {
                if (e[i].checked)
                {					
					valorSeleccionado = e[i].value
					const idTurno = registrarTurno.querySelector('.modal-body #idTurno');
					idTurno.value = valorSeleccionado; 
					console.log(valorSeleccionado)
					
				}
                    
            }
        }
	
}





//Registro prescripcion
const registroPrescripcion = document.getElementById('registroPrescripcion');
        
if(registroPrescripcion)
{
	function seleccionRadio() {
            var e = document.getElementsByName('seleccion');
            let valorSeleccionado;
 
            for (i = 0; i < e.length; i++) {
                if (e[i].checked)
                {
					valorSeleccionado = e[i].value
					const inputIdPractica = registroPrescripcion.querySelector('.modal-body #idPractica');
					inputIdPractica.value = valorSeleccionado; 
					
				}
                    
            }
        }

	
	registroPrescripcion.addEventListener('show.bs.modal', event => {
//        const idPractica = document.getElementById('seleccion')       
        const inputNroAfiliado = registroPrescripcion.querySelector('.modal-body #nroAfiliado')
       
        });
	
}
// Obra social
const actualizarObraSocial = document.getElementById('actualizarObraSocial')

// verifica que exista el modal de, si existe ingresa al if
if (actualizarObraSocial) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  actualizarObraSocial.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idObraSocial = a.getAttribute('idObraSocial');
    //console.log(idPractica);
    
    const nomObraSocial=a.getAttribute('nomObraSocial');
    
	console.log(nomObraSocial);
        
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdObraSocial = actualizarObraSocial.querySelector('.modal-body #idObraSocial');
   
    const inputNomObraSocial = actualizarObraSocial.querySelector('.modal-body #nomObraSocial');    

	//asigno los valores capturados para mostrarlos
    inputIdObraSocial.value = idObraSocial;
    
    inputNomObraSocial.value = nomObraSocial;    
  })
}


const eliminarObraSocial = document.getElementById('eliminarObraSocial');
// verifica que exista el modal, si existe ingresa al if
if (eliminarObraSocial) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  eliminarObraSocial.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idObraSocial = a.getAttribute('idObraSocial');    
    const nomObraSocial=a.getAttribute('nomObraSocial');

	console.log(nomObraSocial);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdObraSocial = eliminarObraSocial.querySelector('.modal-body #idObraSocial');
    const inputNomObraSocial = eliminarObraSocial.querySelector('.modal-body #nomObraSocial');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdObraSocial.value = idObraSocial;
    inputNomObraSocial.innerHTML = nomObraSocial;   
  })
}

const revivirObraSocial = document.getElementById('revivirObraSocial')
if(revivirObraSocial){
	revivirObraSocial.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idObraSocial = a.getAttribute('idObraSocial');    
    const nomObraSocial=a.getAttribute('nomObraSocial');

	console.log(nomObraSocial);
       
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdObraSocial = revivirObraSocial.querySelector('.modal-body #idObraSocial');
    const inputNomObraSocial = revivirObraSocial.querySelector('.modal-body #nomObraSocial');
    
        
	//asigno los valores capturados para mostrarlos
	inputIdObraSocial.value = idObraSocial;
    inputNomObraSocial.innerHTML = nomObraSocial;   
  })
}

// FUNCIONES SELECCION

function selectAll()
{
	 const all = document.getElementsByName("seleccionados")
  	 all.forEach(item => item.checked = true)
}

function unSelectAll()
{
	 const all = document.getElementsByName("seleccionados")
     all.forEach(item => item.checked = false)

}


function verificarSeleccion()
{
	if (!(document.getElementsByName("seleccionados")).checked)
		{
			alert('Debe seleccionar al menos un horario')
			console.log("paso por aca")						
		}
		return false;

}

//Practicas 
const actualizarPractica = document.getElementById('actualizarPractica');

if (actualizarPractica) {
    actualizarPractica.addEventListener('show.bs.modal', event => {
        const a = event.relatedTarget;
        const idPractica = a.getAttribute('idPractica');
        const descripcion = a.getAttribute('descPractica');
        const duracion = a.getAttribute('duracion');
        const equipo = a.getAttribute('equipo');
        const tipoPractica = a.getAttribute('tipoPractica'); // Captura el tipo de práctica del enlace

        console.log(idPractica);
        console.log(duracion);
        console.log(equipo);
        console.log(tipoPractica);

        const inputIdPractica = actualizarPractica.querySelector('.modal-body #idPractica');
        const inputDescripcion = actualizarPractica.querySelector('.modal-body #descPractica');
        const inputDuracion = actualizarPractica.querySelector('.modal-body #duracion');
        const selectEquipo = actualizarPractica.querySelector('.modal-body #equipo');
        const ambulatoriaRadio = actualizarPractica.querySelector('.modal-body #ambulatoria'); // Obtener el radio de Ambulatoria
        const discapacidadRadio = actualizarPractica.querySelector('.modal-body #discapacidad'); // Obtener el radio de Discapacidad
        const equipoField = actualizarPractica.querySelector('.modal-body #equipoField');

        inputIdPractica.value = idPractica;
        inputDescripcion.value = descripcion;
        inputDuracion.value = duracion;
        selectEquipo.value = equipo;

        if (tipoPractica === 'AMBULATORIA') {
            ambulatoriaRadio.checked = true; // Marcar el radio de Ambulatoria si es tipoPractica 1
            equipoField.style.display = 'block'; // Mostrar el campo de equipo si es Ambulatoria
        } else if (tipoPractica === 'DISCAPACIDAD') {
            discapacidadRadio.checked = true; // Marcar el radio de Discapacidad si es tipoPractica 2
            equipoField.style.display = 'none'; // Ocultar el campo de equipo si es Discapacidad
        }
    });
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


//Registro turno 

const registrarTurno = document.getElementById('registrarTurno')

if(registrarTurno)
{

        registrarTurno.addEventListener('show.bs.modal', event => {

        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idTurno = a.getAttribute('idTurno')
        const fechaTurno = a.getAttribute('fecha_turno');
        const horaTurno = a.getAttribute('hora_turno');
        
        console.log(idTurno)
		console.log(fechaTurno)
        
        
        // Traigo el formulario dentro del modal
        const form = registrarTurno.querySelector('form');
        
        // Busco los campos en donde voy a mostrar los datos dentro del formulario
        const inputHiddenIdTurno = form.querySelector('#idTurno');
        const labelFechaTurno = form.querySelector('#fechaTurno');
        const labelHoraTurno = form.querySelector('#horaTurno');    

        // Asigno los valores capturados para mostrarlos
        inputHiddenIdTurno.value = idTurno;
        labelFechaTurno.innerHTML = fechaTurno;
        labelHoraTurno.innerHTML = horaTurno;
    });
	
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
					const inputIdPractica = registroPrescripcion.querySelector('.modal-body #idTurno');
					inputIdPractica.value = valorSeleccionado; 
					console.log(valorSeleccionado)
					
				}
                    
            }
        }
	
		registroPrescripcion.addEventListener('show.bs.modal', event => {
     
        const inputNroAfiliado = registroPrescripcion.querySelector('.modal-body #nroAfiliado')
       
        });
	
}



//Registro asistencia 
const registroAsistencia = document.getElementById('registroAsistencia');

if(registroAsistencia)
{
	registroAsistencia.addEventListener('show.bs.modal', event => {   
   
    const a = event.relatedTarget
    const idTurno = a.getAttribute('idTurno');
    const apellidoProfesional=a.getAttribute('apellidoProfesional');
    const descPractica=a.getAttribute('descPractica');
    const fechaTurno=a.getAttribute('fecha_turno');
    const horaTurno=a.getAttribute('hora_turno');    
    
	console.log(idTurno);
	
	
    const inputIdTurno = registroAsistencia.querySelector('.modal-body #idTurno');
    const labelDescPractica = registroAsistencia.querySelector('.modal-body #descPractica');
    const labelProfesional = registroAsistencia.querySelector('.modal-body #apellidoProfesional');    
    const labelFechaTurno = registroAsistencia.querySelector('.modal-body #fechaTurno');
    const labelHoraTurno = registroAsistencia.querySelector('.modal-body #horaTurno'); 
     
     
    inputIdTurno.value=idTurno;
    labelDescPractica.innerHTML = descPractica;
    labelProfesional.innerHTML = apellidoProfesional;
    labelFechaTurno.innerHTML = fechaTurno;
    labelHoraTurno.innerHTML = horaTurno
	
      
  })
	
}



//Filtro busqueda turnos 
const filtroTurno = document.getElementById('filtro')

    filtroTurno.addEventListener('change', () => {
      ocultarCampos();        
           
      if (filtroTurno.value > -1) {
        document.getElementById(`filtro-${filtroTurno.value}`).style.display = 'block';
        document.getElementById(`btnBuscar`).style.display = 'block';
        
      }
    });
    
    function ocultarCampos() {
      document.getElementById('filtro-1').style.display = 'none';
      document.getElementById('filtro-2').style.display = 'none';
      document.getElementById('filtro-3').style.display = 'none';
      document.getElementById('btnBuscar').style.display = 'none';
    }

    ocultarCampos();


//Cancela turno

const cancelaTurno = document.getElementById('cancelaTurno')

if(cancelaTurno)
{

        cancelaTurno.addEventListener('show.bs.modal', event => {

        
        // Trae el link que levantó el modal
        const a = event.relatedTarget
        
        // Trae los atributos del link con los valores que se le enviaron 
        const idTurno = a.getAttribute('idTurno');
        const descPractica = a.getAttribute('descPractica');
        const profesional = a.getAttribute('profesional')       
        const datosTurno = a.getAttribute('datosTurno');
        
        console.log(idTurno)
        console.log(profesional)        
        
        // Traigo el formulario dentro del modal
        const form = cancelaTurno.querySelector('form');
        
        // Busco los campos en donde voy a mostrar los datos dentro del formulario
        const inputHiddenIdTurno = form.querySelector('#idTurno');
        const labelDescPractica = form.querySelector('#descPractica');
        const labelProfesional = form.querySelector('#profesional');
        const labelDatosTurno = form.querySelector('#datosTurno');      

        // Asigno los valores capturados para mostrarlos
        inputHiddenIdTurno.value = idTurno;
        labelDescPractica.innerHTML = descPractica;
        labelProfesional.innerHTML = profesional;
        labelDatosTurno.innerHTML = datosTurno;
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

// Montos Practica
//Alta
const altaMontoModal = document.getElementById('altaMonto');

altaMontoModal.addEventListener('show.bs.modal', function (event) {
	const idPractica = event.relatedTarget.getAttribute('idPractica');
    const idPracticaInputReadOnly = document.getElementById('idPractica');
    
    // Establecer el valor de idPractica en los campos oculto y readonly del formulario
    idPracticaInputReadOnly.value = idPractica;
});

//Actualizar
const actualizarMontoPractica = document.getElementById('actualizarMonto')

// verifica que exista el modal de, si existe ingresa al if
if (actualizarMonto) {
	//agrega un evento onload al modal, cuando el modal se levante ejecuta la siguiente función
  actualizarMonto.addEventListener('show.bs.modal', event => {
    
    //trae el link que levanto el modal
    const a = event.relatedTarget
    
    //trae los atributos del link con los valores que se le enviaron 
    const idPractica = a.getAttribute('idPractica');
    console.log(idPractica);
    const idMonto = a.getAttribute('id_monto');
    console.log(idMonto);
    const fechaDesde=a.getAttribute('fechaDesde');
	console.log(fechaDesde);
	const fechaHasta=a.getAttribute('fechaHasta');
	console.log(fechaHasta);
	const monto = a.getAttribute('monto');
	console.log(monto);
        
	//traigo los campos en donde voy a mostrar los datos 
    const inputIdPractica = actualizarMonto.querySelector('.modal-body #idPractica');
    const inputIdMonto = actualizarMonto.querySelector('.modal-body #idMonto');
    const inputFechaDesde = actualizarMonto.querySelector('.modal-body #fechaDesde');
    const inputFechaHasta = actualizarMonto.querySelector('.modal-body #fechaHasta');
    const inputMonto = actualizarMonto.querySelector('.modal-body #monto');    
    
	//asigno los valores capturados para mostrarlos
    inputIdPractica.value = idPractica;
    inputIdMonto.value = idMonto;    
    inputFechaDesde.value = fechaDesde;
    inputFechaHasta.value = fechaHasta;
    inputMonto.value = monto;    
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
								
		}
		return false;

}

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



//function capturarSeleccion() {
//      
//		var seleccionados = document.querySelectorAll("cb1")
//		
//		seleccionados.forEach((e)=>	{
//			if(e.checked == tru)
//			{
//				
//			}
//		
//		
//		
//		)
//			 if(document.getElementById("cb1").checked){
//                var seleccion =[];
//                seleccion =document.getElementById("cb1").value
//               
//            }            
//            window.opener.setValue(seleccion);
//            window.close();
//            return false;
//		 }
//            
//        
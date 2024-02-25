package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import entity.Consultorio;
import repository.ConsultorioRepository;

public class ConsultorioService {
	protected ConsultorioRepository consRep;
	String respuestaOperacion;
	
	public ConsultorioService()
	{
		this.consRep = new ConsultorioRepository();
	}
	
	public List<Consultorio> getAll()
	{
		return consRep.getAll();
	}
	
	public List<Consultorio> getAllActivos()
	{
		return consRep.getAllActivos();
	}

	public List<Consultorio> getAllInactivos() {			
		return consRep.getAllInactivos();
	}
	
	public String insertarConsultorio(String descConsultorio) {	
		return respuestaOperacion = consRep.insertarConsultorio(descConsultorio);
	}
	
	public String actualizarConsultorio(Integer idConsultorio, String descConsultorio) {
		// TODO Auto-generated method stub	
		return respuestaOperacion = consRep.actualizarConsultorio(idConsultorio, descConsultorio);		
	}

	public String eliminarConsultorio(Integer idConsultorio) {
		// TODO Auto-generated method stub
		return respuestaOperacion = consRep.eliminarConsultorio(idConsultorio);
	}
	
	public String revivirConsultorio(Integer idConsultorio) {
		// TODO Auto-generated method stub
		return respuestaOperacion = consRep.revivirConsultorio(idConsultorio);
	}

	public Consultorio getConsultorioDisponible(LocalDate fecha, LocalTime hora_desde, LocalTime hora_hasta) {
		
		
		List<Consultorio> consAsignados = consRep.getConsultoriosAsignados(fecha, hora_desde, hora_hasta);
		
		List<Consultorio> consActivos = consRep.getAllActivos();
		
		Consultorio consDisponible = null;
		
		
		for(Consultorio c : consActivos )
		{
			if(consAsignados.size()==0)
			{
				consDisponible = c;
			}
			
			else
			{
				for(Consultorio cAsig : consAsignados)
				{
					if(c.getId_consultorio()!=cAsig.getId_consultorio())
					{
						consDisponible = c;
					}					
					
				}
				
			}
						
		}
		
		return consDisponible;	
		
		
	}
}
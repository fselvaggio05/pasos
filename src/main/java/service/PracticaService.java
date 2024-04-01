package service;

import java.util.List;

import entity.Enumeradores;
import entity.Practica;
import repository.PracticaRepository;

public class PracticaService {
								protected PracticaRepository prRep;
								String respuestaOperacion;
	
								public PracticaService()
								{
									this.prRep = new PracticaRepository();
								}
	
								public List<Practica> getAllActivas()
								{
									return prRep.getAllActivas();
								}
							
								public String insertarPractica(Integer idPractica, Enumeradores.TipoPractica tipo_practica, String descPractica, Integer duracion, Integer idEquipo) 
								{
									return respuestaOperacion = prRep.insertarPractica(idPractica,tipo_practica,descPractica,duracion,idEquipo);	
								}
	
								public String actualizarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo, Double monto) 
								{									
									return respuestaOperacion = prRep.actualizarPractica(idPractica, descPractica, duracion, idEquipo, monto);	
								}

								public String eliminarPractica(Integer idPractica) {
									return respuestaOperacion = prRep.eliminarPractica(idPractica);
								}
							
								public List<Practica> getAllInactivas() 
								{										
									return prRep.getAllInactivas();
								}
							
								public String revivirPractica(Integer idPractica) 
								{
									return respuestaOperacion = prRep.revivirPractica(idPractica);
								}
							
								public Integer getDuracionPractica(Practica pr) 
								{		
									return 	prRep.getDuracionPractica(pr);
								}
							 }

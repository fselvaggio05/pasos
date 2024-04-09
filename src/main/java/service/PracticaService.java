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
							
								public String insertarAmbulatoria(Integer idPractica, Enumeradores.TipoPractica tipo_practica, String descPractica, Integer duracion, Integer idEquipo) 
								{
									return respuestaOperacion = prRep.insertarAmbulatoria(idPractica,tipo_practica,descPractica,duracion,idEquipo);	
								}
								
								public String insertarDiscapacidad(Integer idPractica, Enumeradores.TipoPractica tipo_practica, String descPractica, Integer duracion) 
								{
									return respuestaOperacion = prRep.insertarDiscapacidad(idPractica,tipo_practica,descPractica,duracion);	
								}
	
								public String actualizarAmbulatoria(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) 
								{									
									return respuestaOperacion = prRep.actualizarAmbulatoria(idPractica, descPractica, duracion, idEquipo);	
								}

								public String actualizarDiscapacidad(Integer idPractica, String descPractica, Integer duracion) 
								{									
									return respuestaOperacion = prRep.actualizarDiscapacidad(idPractica, descPractica, duracion);	
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

								public Practica getPracticaPorID(Integer idPractica) {
									return prRep.getPracticaPorID(idPractica);
								}
							 }

package service;

import java.util.List;
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
							
								public String insertarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) 
								{
									return respuestaOperacion = prRep.insertarPractica(idPractica,descPractica,duracion,idEquipo);	
								}
	
								public String actualizarPractica(Integer idPractica, String descPractica, Integer duracion, Integer idEquipo) 
								{									
									return respuestaOperacion = prRep.actualizarPractica(idPractica, descPractica, duracion, idEquipo);	
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
							
								public List<Practica> getPracticasPorProf(Integer matricula) 
								{	
									List<Practica> practicasProfesional = prRep.getPracticasPorProf(matricula);
									return practicasProfesional;
								}
							 }

package service;

import java.util.List;

import entity.Practica;
import entity.Profesional;
import repository.ProfesionalRepository;
import repository.UsuarioRepository;

public class ProfesionalService {

    private UsuarioRepository usRep;
    private ProfesionalRepository profRep;
    private PracticaService prServ;


    //refactor de esto, para pasarle las variables por parametro
    public ProfesionalService(){
        this.usRep = new UsuarioRepository();
        this.profRep= new ProfesionalRepository();
    }

    public void insertarProfesional(Profesional prof) {
        usRep.insertarUsuario(prof);
        profRep.insertarProfesional(prof);
    }
    
    public List<Profesional> getAll()
    {
    	return profRep.getAll();
    }
    
    
    
    //Metodo creado para traer solo las practicas que realiza un profesional cuando se da de alta en "Agregar horario" - Sin terminar
    //Cuando selecciono el profesional en la lista de profesionales, deberia cargar solo las practicas que tiene habilitadas 
    public List<Practica> getPracticasPorProf(Integer matricula)
    {
		List<Practica> practicasProfesional = prServ.getPracticasPorProf(matricula);
    	
    	return practicasProfesional;
    	
    }
}

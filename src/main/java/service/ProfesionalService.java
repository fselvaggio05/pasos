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

    public String insertarProfesional(Profesional prof) {
       
    	String respuestaOperacion;
    	
    	respuestaOperacion = usRep.insertarUsuario(prof);
        respuestaOperacion = profRep.insertarProfesional(prof);
        
        return respuestaOperacion;
    }
    
    public List<Profesional> getAll()
    {
    	return profRep.getAll();
    }
     
    public List<Practica> getPracticasPorProf(Integer matricula)
    {
		List<Practica> practicasProfesional = prServ.getPracticasPorProf(matricula);
    	
    	return practicasProfesional;
    	
    }

	public boolean validarProfesional(Profesional prof) {
		// TODO Auto-generated method stub
		return this.profRep.validarProfesional(prof);
	}

	public String updateProfesionalSinContraseña(Profesional prof) {
		// TODO Auto-generated method stub
		String rtaOperacion = usRep.updateUsuarioSinContraseña(prof);
		if("OK".equals(rtaOperacion)) {
			rtaOperacion = this.profRep.updateProfesional(prof);
		}
		return rtaOperacion;
	}
	
	public String updateProfesionalConContraseña(Profesional prof) {
		// TODO Auto-generated method stub
		String rtaOperacion = usRep.updateUsuarioConContraseña(prof);
		if("OK".equals(rtaOperacion)) {
			rtaOperacion = this.profRep.updateProfesional(prof);
		}
		return rtaOperacion;
	}

	public List<Profesional> getAllPorDNI(int dniBuscado) {
		// TODO Auto-generated method stub
		return this.profRep.getAllPorDNI(dniBuscado);
	}
}

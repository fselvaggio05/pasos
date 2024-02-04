package service;

import entity.Paciente;
import repository.PacienteRepository;
import repository.UsuarioRepository;

public class PacienteService {

    private UsuarioRepository usRep;
    private PacienteRepository pacRep;

    public PacienteService(){
        this.usRep = new UsuarioRepository();
        this.pacRep = new PacienteRepository();
    }

    public String insertarPaciente(Paciente pac) {
    	
    	String respuestaOperacion;

        respuestaOperacion = usRep.insertarUsuario(pac);
        respuestaOperacion = pacRep.insertarPaciente(pac);
        
        return respuestaOperacion;
    }
}





package service;

import java.util.List;

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

	public Paciente buscarPaciente(Integer dni) {
		
		return pacRep.buscarPaciente(dni);
		
	}

	public List<Paciente> getAll() {
		return pacRep.getAll();
	}

	public boolean validarPaciente(Paciente pac) {
		// TODO Auto-generated method stub
		return this.pacRep.validarPaciente(pac);
	}

	public String updatePacienteSinContraseña(Paciente pac) {
		String rtaOperacion = usRep.updateUsuarioSinContraseña(pac);
		if("OK".equals(rtaOperacion)) 
		{
			rtaOperacion = pacRep.updatePaciente(pac);
		}
		return rtaOperacion;
	}
	
	public String updatePacienteConContraseña(Paciente pac) {
		String rtaOperacion = usRep.updateUsuarioSinContraseña(pac);
		if("OK".equals(rtaOperacion)) 
		{
			rtaOperacion = pacRep.updatePaciente(pac);
		}
		return rtaOperacion;
	}

	public List<Paciente> getAllPorDNI(int dniBuscado) {
		// TODO Auto-generated method stub
		return this.pacRep.getAllPorDNI(dniBuscado);
	}    
}
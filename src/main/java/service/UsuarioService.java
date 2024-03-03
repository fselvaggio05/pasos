package service;

import java.util.List;
import entity.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usRep;
    
    public UsuarioService(){
        this.usRep=new UsuarioRepository();
    }

    public Usuario buscarUsuario(String mail, String pass) {
        return usRep.buscarUsuario(mail,pass);
    }

    public String insertarUsuario(Usuario us) {
        return usRep.insertarUsuario(us);
    }

	public List<Usuario> getAllAdministradores() {
		// TODO Auto-generated method stub
		return usRep.getAllAdministradores();
	}

	public boolean validarAdministrador(Usuario us) {
		// TODO Auto-generated method stub
		return usRep.validarAdministrador(us);
	}

	public String updateUsuarioSinContraseña(Usuario us) {
		// TODO Auto-generated method stub
		return usRep.updateUsuarioSinContraseña(us);
	}
	
	public String updateUsuarioConContraseña(Usuario us) {
		// TODO Auto-generated method stub
		return usRep.updateUsuarioSinContraseña(us);
	}

	public List<Usuario> getAllAdministradoresPorDNI(int dniBuscado) {
		// TODO Auto-generated method stub
		return this.usRep.getAllAdministradoresPorDNI(dniBuscado);
	}
}
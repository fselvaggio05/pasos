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
}

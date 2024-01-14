package service;

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


    public void insertarUsuario(Usuario us) {
        usRep.insertarUsuario(us);
    }
}

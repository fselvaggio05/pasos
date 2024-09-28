package service;

import java.util.List;
import java.util.Random;
import java.util.Properties;

import entity.Usuario;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import repository.UsuarioRepository;

public class UsuarioService {
    private final UsuarioRepository usRep;
    
    public UsuarioService(){
        this.usRep=new UsuarioRepository();
    }

    public Usuario buscarUsuario(Integer dni, String pass) {
        return usRep.buscarUsuario(dni,pass);
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

	public Integer buscarDniUsuario(String mail) {
		// TODO Auto-generated method stub
		return usRep.buscarDniUsuario(mail);
	}
	
	public String generarClave()
	{
		String abc_minuscula="abcdefghijklmnopqrstuvwxyz";
		String abc_mayuscula = abc_minuscula.toUpperCase();
		String numeros="0123456789";
		//String simbolos = "/*-+!·$%&/()=";
		
		String cadena = abc_minuscula.concat(abc_mayuscula).concat(numeros);
		
		Random mRandom = new Random();
		
		String resultado = "";
		
		for(int i = 0; i < 10 ; i++)
		{
			int posicion = mRandom.nextInt(cadena.length());
			char caracter = cadena.charAt(posicion);
			resultado += caracter;
			
		}
		
		return resultado;
	}

	public void setearClaveGenerada(Usuario us) {
		
		usRep.setearClaveGenerada(us);
		
	}

	public void cambiarClave(Usuario us) {
		
		usRep.cambiarClave(us);
		
	}

	


}
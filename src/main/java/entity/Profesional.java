package entity;

import java.text.ParseException;
import java.time.LocalDate;

public class Profesional extends Usuario {

    private Integer matricula;

    public Profesional(Integer dni, String apellido, String nombre, String email, LocalDate fechaNac, String telefono, String clave, String genero, Integer matricula, Integer tipo_usuario) 
    		throws ParseException {
							        this.setDni(dni);
							        this.setNombre(nombre);
							        this.setApellido(apellido);
							        this.setEmail(email);
							        this.setFecha_nacimiento(fechaNac);
							        this.setTelefono(telefono);
							        this.setClave(clave);
							        this.setGenero(genero);
							        this.setMatricula(matricula);
							        this.setTipo_usuario(tipo_usuario);
						         }
    
    public Profesional(Integer dni, Integer matricula) 
    		throws ParseException {
							        this.setDni(dni);
							        this.setMatricula(matricula);
						         }
    
    public Profesional() {
		// TODO Auto-generated constructor stub
	}

	public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
}
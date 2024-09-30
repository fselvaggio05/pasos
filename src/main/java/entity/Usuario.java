package entity;

import java.text.ParseException;
import java.time.LocalDate;
import entity.Enumeradores.TipoUsuario;

public class Usuario {
    private Integer dni;
    private String apellido;
    private String nombre;
    private LocalDate fecha_nacimiento;
//    TODO: Variable genero como ENUM    
    private String genero;
    private String telefono;
    private String email;
    private String clave;
    private TipoUsuario tipo_usuario;
    //private Integer tipo_usuario;
    private Boolean cambio_clave;

    public Usuario(Integer dni, String apellido, String nombre, String email, LocalDate fecha_nac, String telefono, String clave, String genero,Integer tipo_usuario) throws ParseException {
        this.dni=dni;
        this.nombre = nombre;
        this.apellido=apellido;
        this.email=email;
        this.fecha_nacimiento=fecha_nac;
        this.telefono=telefono;
        this.genero=genero;
        this.clave=clave;
        this.tipo_usuario = TipoUsuario.fromCodigo(tipo_usuario);
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;        
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getDni() {
        return dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero()
    {
        return genero;
    } 
    

	public TipoUsuario getTipo_usuario() {
		return tipo_usuario;
	}


	public void setTipo_usuario(Integer tipo_usuario) {
		this.tipo_usuario = TipoUsuario.fromCodigo(tipo_usuario);
	}
	

	public Boolean getCambio_clave() {
		return cambio_clave;
	}


	public void setCambio_clave(Boolean cambio_clave) {
		this.cambio_clave = cambio_clave;
	}


	public Usuario() {

    }
}

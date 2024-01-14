package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {
    private Integer dni;
    private String apellido;

    private String nombre;
    private Date fecha_nacimiento;


//    TODO: Variable genero como ENUM
    
    private String genero;

    private String telefono;

    private String email;
    private String clave;

    public Usuario(Integer dni, String nombre, String apellido, String email, String fechaNac, String telefono, String clave, String genero) throws ParseException {
        this.dni=dni;
        this.nombre = nombre;
        this.apellido=apellido;
        this.email=email;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date fechaNacimiento = sdf.parse(fechaNac);
        this.fecha_nacimiento=fechaNacimiento;
        this.telefono=telefono;
        this.genero=genero;
        this.clave=clave;

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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
    
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//        Date fechaNacimiento = sdf.parse(apellido)(fecha_nacimiento);
        this.fecha_nacimiento = fecha_nacimiento;
        
    }
/*
   // public Enum getGenero() {
        return genero;
    }*/

   /* public void setGenero(Enum genero) {
        this.genero = genero;
    }
*/
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

    public Usuario() {

    }

}

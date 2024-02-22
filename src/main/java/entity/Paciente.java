package entity;

import java.text.ParseException;
import java.time.LocalDate;

public class Paciente extends Usuario{

    private String nro_afiliado;
    private Integer id_obra_social;


    public Paciente()
    {

    }
    
//    MODIFICAR ESTE CONSTRUCTOR INVOCANDO AL DEL USUARIO PRIMERO

    public Paciente(int dni, String apellido, String nombre, String email, LocalDate fechaNac, String telefono, String clave, String genero, Integer obraSocial, String nroAfiliado) throws ParseException {
        this.setDni(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setFecha_nacimiento(fechaNac);
        this.setTelefono(telefono);
        this.setClave(clave);
        this.setGenero(genero);
        this.id_obra_social=obraSocial;
        this.nro_afiliado= nroAfiliado;
    }

    public Integer getId_obra_social() {
        return id_obra_social;
    }

    public void setId_obra_social(Integer id_obra_social) {
        this.id_obra_social = id_obra_social;
    }

    public String getNro_afiliado() {
        return nro_afiliado;
    }

    public void setNro_afiliado(String nro_afiliado) {
        this.nro_afiliado = nro_afiliado;
    }

}

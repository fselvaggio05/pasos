package entity;

import java.text.ParseException;
import java.time.LocalDate;

public class Paciente extends Usuario{

    private String nro_afiliado;
    private ObraSocial obra_social;


    public Paciente()
    {

    }
    
//    MODIFICAR ESTE CONSTRUCTOR INVOCANDO AL DEL USUARIO PRIMERO

    public Paciente(int dni, String apellido, String nombre, String email, LocalDate fechaNac, String telefono, String clave, String genero, Integer tipo_usuario, ObraSocial obraSocial, String nroAfiliado) throws ParseException {
        this.setDni(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setFecha_nacimiento(fechaNac);
        this.setTelefono(telefono);
        this.setClave(clave);
        this.setGenero(genero);
        this.setTipo_usuario(tipo_usuario);
        this.obra_social=obraSocial;
        this.nro_afiliado= nroAfiliado;
    }

    public ObraSocial getObra_social() {
        return obra_social;
    }

    public void setObra_social(ObraSocial obra_social) {
        this.obra_social = obra_social;
    }

    public String getNro_afiliado() {
        return nro_afiliado;
    }

    public void setNro_afiliado(String nro_afiliado) {
        this.nro_afiliado = nro_afiliado;
    }
}

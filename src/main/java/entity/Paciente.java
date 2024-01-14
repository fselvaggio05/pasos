package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente extends Usuario{

    private Integer nro_afiliado;
    private Integer id_obra_social;


    public Paciente()
    {

    }
    
//    MODIFICAR ESTE CONSTRUCTOR INVOCANDO AL DEL USUARIO PRIMERO

    public Paciente(int dni, String nombre, String apellido, String email, String fechaNac, String telefono, String clave, String genero, Integer obraSocial, String nroAfiliado) throws ParseException {
        this.setDni(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date fechaNacimiento = sdf.parse(fechaNac);
        this.setFecha_nacimiento(fechaNacimiento);
        this.setTelefono(telefono);
        this.setClave(clave);
        this.setGenero(genero);
        this.id_obra_social=obraSocial;
        this.nro_afiliado= Integer.parseInt(nroAfiliado);
    }

    public Integer getId_obra_social() {
        return id_obra_social;
    }

    public void setId_obra_social(Integer id_obra_social) {
        this.id_obra_social = id_obra_social;
    }

    public Integer getNro_afiliado() {
        return nro_afiliado;
    }

    public void setNro_afiliado(Integer nro_afiliado) {
        this.nro_afiliado = nro_afiliado;
    }

}

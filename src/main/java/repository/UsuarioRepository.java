package repository;

import conexionDB.FactoryConnection;
import entity.Usuario;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UsuarioRepository {

    //esta clase se encargara de realizar las consultas a la DB,
// haciendo select, update, insert y delete
    ResultSet rs = null;
    PreparedStatement stmt = null;
    Usuario us = new Usuario();

    public Usuario buscarUsuario(String mail, String pass) {


        try {

            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from usuario where email=? and clave=?");
            stmt.setString(1, mail);
            stmt.setString(2, pass);
            rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                us.setApellido(rs.getString("apellido"));
                us.setNombre(rs.getString("nombre"));
                us.setDni((rs.getInt("dni")));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        //una vez ejecutado to,do, cierro la conexion de la base de datos
        finally {

            try {
                //se cierran conexiones abiertas en el orden inverso en que fueron abiertas
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            FactoryConnection.getInstancia().releaseConn(); //reveer esto, no me convene


        }

        return us;
    }



    public void insertarUsuario(Usuario us) {

        try
        {
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("INSERT INTO usuario (dni,apellido, nombre, telefono, clave, fecha_nacimiento, genero, email) VALUES (?,?,?,?,?,?,?,?)"); /**/
            stmt.setInt(1,us.getDni());
            stmt.setString(2, us.getApellido());
            stmt.setString(3, us.getNombre());
            stmt.setString(4, us.getTelefono());
            stmt.setString(5, us.getClave());
            stmt.setDate(6, new java.sql.Date(us.getFecha_nacimiento().getTime()));
            stmt.setString(7, us.getGenero());
            stmt.setString(8, us.getEmail());
            stmt.executeUpdate();
        }

        catch (SQLException e) {
           e.toString();
        }

        finally {
            try {
              
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            FactoryConnection.getInstancia().releaseConn(); 

        }
    }
}

package repository;

import conexionDB.FactoryConnection;
import entity.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;

    Paciente pac = new Paciente();

    public void insertarPaciente(Paciente pac)
    {

    try{
        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into paciente (dni,nro_afiliado,id_obra_social) values (?,?,?)");
        stmt.setInt(1,pac.getDni());
        stmt.setInt(2,pac.getNro_afiliado());
        stmt.setInt(3,pac.getId_obra_social());
        stmt.executeUpdate();

    } catch (SQLException e) {
        e.toString();
    }

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

    }

	

}

package repository;

import conexionDB.FactoryConnection;
import entity.Profesional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfesionalRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;


    public void insertarProfesional(Profesional prof) {

        try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into profesional (dni,matricula) values (?,?)");
            stmt.setInt(1,prof.getDni());
            stmt.setInt(2,prof.getMatricula());

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

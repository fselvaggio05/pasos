package repository;

import conexionDB.FactoryConnection;
import entity.Profesional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;


    public String insertarProfesional(Profesional prof) {
    	
    	String respuestaOperacion;

        try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into profesional (dni,matricula) values (?,?)");
            stmt.setInt(1,prof.getDni());
            stmt.setInt(2,prof.getMatricula());
            respuestaOperacion = "OK";
            

        } catch (SQLException e) {
            respuestaOperacion = e.toString();
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
        
        return respuestaOperacion;

    }


	public List<Profesional> getAll() {
		
		List<Profesional> profesionales = new ArrayList<Profesional>();
		
		try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select apellido, nombre, matricula from profesional p inner join usuario u on p.dni=u.dni order by apellido asc");
            rs=stmt.executeQuery();
            while(rs.next() && rs !=null)
            {
            	Profesional pr = new Profesional();
            	pr.setApellido(rs.getString("apellido"));
            	pr.setNombre(rs.getString("nombre"));
            	pr.setMatricula(rs.getInt("matricula"));
            	profesionales.add(pr);
            }
            

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
		
		
		return profesionales;
		
		
	}
	
	


}

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

    public String insertarPaciente(Paciente pac)
    {   	
    	
    	String respuestaOperacion;
	
	    try
	    {
	    	
	        stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into paciente (dni,nro_afiliado,id_obra_social) values (?,?,?)");
	        stmt.setInt(1,pac.getDni());
	        stmt.setInt(2,pac.getNro_afiliado());
	        stmt.setInt(3,pac.getId_obra_social());
	        stmt.executeUpdate();
	        respuestaOperacion = "OK";
	
	    } 
	    catch (SQLException e) {
	        respuestaOperacion = e.toString();
	    }
	
	    finally {
	
	        try {
	            
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	
	
	        } catch (Exception e) {
	            respuestaOperacion = e.toString();
	        }
	
	        FactoryConnection.getInstancia().releaseConn(); //reveer esto, no me convene
	
	    }
	    
	    return respuestaOperacion;
	
	    }

	

}

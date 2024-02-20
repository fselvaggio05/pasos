package repository;

import conexionDB.FactoryConnection;
import entity.Paciente;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteRepository {

    ResultSet rs = null;
    PreparedStatement stmt = null;
    String respuestaOperacion;
    
    Paciente pac = new Paciente();

    public String insertarPaciente(Paciente pac)
    {   	
    	   	
	
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

	public Paciente buscarPaciente(Integer dni) {
		
		Paciente pac = new Paciente();
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from paciente p inner join usuario u on u.dni=p.dni inner join obra_social os on os.id_obra_social=p.id_obra_social where p.dni=?");
			stmt.setInt(1, dni);
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				
				pac.setApellido(rs.getString("u.apellido"));
				pac.setNombre(rs.getString("nombre"));
				pac.setDni(rs.getInt("dni"));
				pac.setId_obra_social(rs.getInt("id_obra_social"));
				pac.setNro_afiliado(rs.getInt("nro_afiliado"));
				respuestaOperacion = "OK";
				
			}
			
		}
		
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		return pac;
	}

	

}

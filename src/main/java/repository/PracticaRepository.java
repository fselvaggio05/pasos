package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import conexionDB.FactoryConnection;
import entity.Practica;

public class PracticaRepository {
	
	ResultSet rs = null;
	
	PreparedStatement stmt = null;
	
	String respuestaOperacion;
	
	
	public List<Practica> getAll()
	{
		List<Practica> practicas = new ArrayList<>();
		
		
		//colocar try
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from practica");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Practica pr = new Practica ();
					pr.setId_practica(rs.getInt("id_practica"));
					pr.setDescripcion(rs.getString("descripcion"));
					pr.setId_equipo(rs.getInt("id_equipo"));
					pr.setEstado(rs.getInt("estado"));
					pr.setFecha_baja(rs.getDate("fecha_baja"));
					practicas.add(pr);
					
					
				}
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {

		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		        	} 
		        catch (Exception e) {
		            e.printStackTrace();
		        }

		        FactoryConnection.getInstancia().releaseConn(); 
		    }
			
			return practicas;	

	}
	
	
	
	

	public String insertarPractica(Integer idPractica, String descPractica, Integer idEquipo) {
		
					
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, descripcion,id_equipo,estado) values (?,?,?,?)");
			stmt.setInt(1,idPractica);
			stmt.setString(2, descPractica);
			stmt.setInt(3, idEquipo);
			stmt.setInt(4, 1);
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
	        	} 
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }
		
		
		return respuestaOperacion;
		
	}




	public String actualizarPractica(Integer idPractica, String descPractica, Integer idEquipo, Integer estado) {
		// TODO Auto-generated method stub
		
		Date fecha_baja;
		
		try
		{
			if(estado==2)
			{
				Calendar calendar = new GregorianCalendar();
				fecha_baja = new Date(calendar.getTimeInMillis());
				
			}
			
			else 
			{
				fecha_baja=null;
			}
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set descripcion=?, id_equipo=?, estado=?, fecha_baja=? where id_practica=?");
			stmt.setString(1, descPractica);
			stmt.setInt(2, idEquipo);
			stmt.setInt(3, estado);
			stmt.setDate(4, new java.sql.Date(fecha_baja.getTime()));
			stmt.setInt(5, idPractica);	
			stmt.executeUpdate();
			respuestaOperacion= "OK";
						
		}
		
		catch (SQLException e) {
			
			respuestaOperacion = e.toString();
		}
		
		finally {

	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        	} 
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        FactoryConnection.getInstancia().releaseConn(); 
	    }
			
		
		
		return respuestaOperacion;
	}






	public String eliminarPractica(Integer idPractica, Integer estado) {
		// TODO Auto-generated method stub
		
		String respuestaOperacion;
		Date fecha_baja;
		
		try
		{
			if(estado==2)
			{
				Calendar calendar = new GregorianCalendar();
				fecha_baja = new Date(calendar.getTimeInMillis());
				
			}
			
			else 
			{
				fecha_baja=null;
			}
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set estado=?, fecha_baja=? where id_practica=?");
			stmt.setInt(1, estado);
			stmt.setDate(2, new java.sql.Date(fecha_baja.getTime()));
			stmt.setInt(3, idPractica);	
			stmt.executeUpdate();
			respuestaOperacion= "OK";
		}
		
		
		catch (SQLException e) {
			
			respuestaOperacion = e.toString();
		}
		
		
						
		finally {

	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        	} 
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }
		
		
		
		return respuestaOperacion;
	}












}






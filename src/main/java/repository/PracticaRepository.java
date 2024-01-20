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
	
	
	public List<Practica> getAllActivas()
	{
		List<Practica> practicas = new ArrayList<>();
		
		
		//colocar try
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica INNER JOIN equipo ON practica.id_equipo = equipo.id_equipo where practica.estado=1;");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Practica pr = new Practica ();
					pr.setId_practica(rs.getInt("id_practica"));
					pr.setDescripcion(rs.getString("desc_practica"));
					pr.setDesc_equipo(rs.getString("desc_equipo"));
//					pr.setEstado(rs.getInt("estado"));
//					pr.setFecha_baja(rs.getDate("fecha_baja"));
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
	
	
	
	

	public List<Practica> getAllInactivas() {
			
			List<Practica> practicas = new ArrayList<>();
			
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM practica INNER JOIN equipo ON practica.id_equipo = equipo.id_equipo where practica.estado=0");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Practica pr = new Practica ();
					pr.setId_practica(rs.getInt("id_practica"));
					pr.setDescripcion(rs.getString("desc_practica"));
//					pr.setDesc_equipo(rs.getString("desc_equipo"));
//					pr.setEstado(rs.getInt("estado"));
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
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into practica (id_practica, desc_practica, id_equipo,estado) values (?,?,?,?)");
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




	public String actualizarPractica(Integer idPractica, String descPractica, Integer idEquipo) {
		// TODO Auto-generated method stub
		
	
		
		try
		{
						
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set desc_practica=?, id_equipo=? where id_practica=?");
			stmt.setString(1, descPractica);
			stmt.setInt(2, idEquipo);
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
			if(estado==0)
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



	public String habilitarPractica(Integer id_practica)
	{
		
		String respuestaOperacion;
		
		try
		{
						
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update practica set estado=1, fecha_baja=null where id_practica=?");
			stmt.setInt(1, id_practica);
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











}






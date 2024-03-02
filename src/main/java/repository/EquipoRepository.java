package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Equipo;

public class EquipoRepository {
	ResultSet rs = null;
	PreparedStatement stmt = null;
	String respuestaOperacion;
	
	//Create
	public String insertarEquipo(String tipoEquipo, String descEquipo) {					
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into equipo (tipo_equipo,descripcion) values (?,?)");
			stmt.setString(1,tipoEquipo);
			stmt.setString(2, descEquipo);
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

	//Read	
		//Read all
		public List<Equipo> getAll()
	{
		List<Equipo> equipos = new ArrayList<>();		
		//colocar try
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM equipo");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Equipo eq = new Equipo ();
					eq.setId_equipo(rs.getInt("id_equipo"));
					eq.setTipo_equipo(rs.getString("tipo_equipo"));
					eq.setDescripcion(rs.getString("descripcion"));
					equipos.add(eq);					
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
			return equipos;	
	}

		//Read all Activos
		public List<Equipo> getAllActivos() {
		List<Equipo> equipos = new ArrayList<>();		
		//colocar try
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM equipo where estado=1");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Equipo eq = new Equipo ();
					eq.setId_equipo(rs.getInt("id_equipo"));
					eq.setTipo_equipo(rs.getString("tipo_equipo"));
					eq.setDescripcion(rs.getString("descripcion"));
					equipos.add(eq);					
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
			return equipos;	
	}

		//Read all Inactivos
		public List<Equipo> getAllInactivos() {	
			List<Equipo> equipos = new ArrayList<Equipo>();
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM equipo where estado=0");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Equipo eq = new Equipo();
					eq.setId_equipo(rs.getInt("id_equipo"));
					eq.setTipo_equipo(rs.getString("tipo_equipo"));
					eq.setDescripcion(rs.getString("descripcion"));
					eq.setFecha_baja(rs.getDate("fecha_baja"));
					equipos.add(eq);					
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
			return equipos;	
	}

	//Update
		//Update datos equipo
		public String actualizarEquipo(Integer idEquipo, String tipoEquipo, String descEquipo) {
			// TODO Auto-generated method stub		
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update equipo set tipo_equipo=?, descripcion=? where id_equipo=?");
				stmt.setString(1, tipoEquipo);
				stmt.setString(2, descEquipo);
				stmt.setInt(3, idEquipo);	
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
		
		//Rehabilitar Equipo Dado de Baja
		public String revivirEquipo(Integer idEquipo)
		{
			String respuestaOperacion;
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update equipo set estado=1, fecha_baja=null where id_equipo=?");
				stmt.setInt(1, idEquipo);
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
	
	//Delete	
	public String eliminarEquipo(Integer idEquipo) {
		// TODO Auto-generated method stub
		String respuestaOperacion;
		Date fecha_baja;
		try
		{
			Calendar calendar = new GregorianCalendar();
			fecha_baja = new Date(calendar.getTimeInMillis());
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update equipo set estado=0, fecha_baja=? where id_equipo=?");
			stmt.setDate(1, new java.sql.Date(fecha_baja.getTime()));
			stmt.setInt(2, idEquipo);	
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
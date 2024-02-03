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
import entity.Consultorio;

public class ConsultorioRepository {
	ResultSet rs = null;
	PreparedStatement stmt = null;
	String respuestaOperacion;
	
	//Create
	public String insertarConsultorio(String descConsultorio) {					
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into consultorio (descripcion) values (?)");
			stmt.setString(1,descConsultorio);
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
		public List<Consultorio> getAll()
	{
		List<Consultorio> consultorios = new ArrayList<>();		
		//colocar try
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM consultorio");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Consultorio consultorio = new Consultorio ();
					consultorio.setId_consultorio(rs.getInt("id_consultorio"));
					consultorio.setDescripcion(rs.getString("descripcion"));
					consultorio.setEstado(rs.getBoolean("estado"));
					consultorio.setFecha_baja(rs.getDate("fecha_baja"));
					consultorios.add(consultorio);					
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
			return consultorios;	
	}

		//Read all Activos
		public List<Consultorio> getAllActivos() {
		List<Consultorio> consultorios = new ArrayList<>();		
		//colocar try
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM consultorio where estado=1");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Consultorio consultorio = new Consultorio();
					consultorio.setId_consultorio(rs.getInt("id_consultorio"));
					consultorio.setDescripcion(rs.getString("descripcion"));
					consultorio.setEstado(rs.getBoolean("estado"));
					consultorio.setFecha_baja(rs.getDate("fecha_baja"));
					consultorios.add(consultorio);						
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
			return consultorios;	
	}

		//Read all Inactivos
		public List<Consultorio> getAllInactivos() {	
			List<Consultorio> consultorios = new ArrayList<>();
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM consultorio where estado=0");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					Consultorio consultorio = new Consultorio();
					consultorio.setId_consultorio(rs.getInt("id_consultorio"));
					consultorio.setDescripcion(rs.getString("descripcion"));
					consultorio.setEstado(rs.getBoolean("estado"));
					consultorio.setFecha_baja(rs.getDate("fecha_baja"));
					consultorios.add(consultorio);						
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
			return consultorios;	
	}

	//Update
		//Update datos consultorio
		public String actualizarConsultorio(Integer idConsultorio, String descConsultorio) {
			// TODO Auto-generated method stub		
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update consultorio set descripcion=? where id_consultorio=?");
				stmt.setString(1, descConsultorio);
				stmt.setInt(2, idConsultorio);	
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
		
		//Rehabilitar Consultorio Dado de Baja
		public String revivirConsultorio(Integer idConsultorio)
		{
			String respuestaOperacion;
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update consultorio set estado=1, fecha_baja=null where id_consultorio=?");
				stmt.setInt(1, idConsultorio);
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
	public String eliminarConsultorio(Integer idConsultorio) {
		// TODO Auto-generated method stub
		String respuestaOperacion;
		Date fecha_baja;
		try
		{
			Calendar calendar = new GregorianCalendar();
			fecha_baja = new Date(calendar.getTimeInMillis());
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update consultorio set estado=0, fecha_baja=? where id_consultorio=?");
			stmt.setDate(1, new java.sql.Date(fecha_baja.getTime()));
			stmt.setInt(2, idConsultorio);	
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
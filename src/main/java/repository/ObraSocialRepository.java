package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.ObraSocial;

public class ObraSocialRepository {
	ResultSet rs = null;
	PreparedStatement stmt = null;
	String respuestaOperacion;
	
	//Create
	public String insertarObraSocial(int id_obra_social, String nombre) {					
		try
		{
			stmt= FactoryConnection.getInstancia().getConn().prepareStatement("insert into obra_social (id_obra_social,nombre) values (?,?)");
			stmt.setInt(1,id_obra_social);
			stmt.setString(2, nombre);
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
		public List<ObraSocial> getAll()
	{
		List<ObraSocial> obrasSociales = new ArrayList<>();		
		//colocar try
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM obra_social");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					ObraSocial unaOS = new ObraSocial();
					unaOS.setId_obra_social(rs.getInt("id_obra_social"));
					unaOS.setNombre(rs.getString("nombre"));
					unaOS.setEstado(rs.getBoolean("estado"));
					unaOS.setFecha_baja(rs.getObject("fecha_baja", LocalDate.class));
					obrasSociales.add(unaOS);					
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
			return obrasSociales;	
	}

		//Read all Activos
		public List<ObraSocial> getAllActivas() {
		List<ObraSocial> obrasSociales = new ArrayList<>();		
		//colocar try
		try {
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM obra_social WHERE estado = 1");
			rs = stmt.executeQuery();
			while (rs!=null && rs.next())
			{
				ObraSocial unaOS = new ObraSocial();
				unaOS.setId_obra_social(rs.getInt("id_obra_social"));
				unaOS.setNombre(rs.getString("nombre"));
				unaOS.setEstado(rs.getBoolean("estado"));
				unaOS.setFecha_baja(rs.getObject("fecha_baja", LocalDate.class));
				obrasSociales.add(unaOS);					
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
		return obrasSociales;	
	}

		//Read all Inactivos
		public List<ObraSocial> getAllInactivas() {	
			List<ObraSocial> obrasSociales = new ArrayList<>();
			try {
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("SELECT * FROM obra_social WHERE estado = 0");
				rs = stmt.executeQuery();
				while (rs!=null && rs.next())
				{
					ObraSocial unaOS = new ObraSocial();
					unaOS.setId_obra_social(rs.getInt("id_obra_social"));
					unaOS.setNombre(rs.getString("nombre"));
					unaOS.setEstado(rs.getBoolean("estado"));
					unaOS.setFecha_baja(rs.getObject("fecha_baja", LocalDate.class));
					obrasSociales.add(unaOS);					
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
			return obrasSociales;		
	}

	//Update
		//Update datos consultorio
		public String actualizarObraSocial(Integer id_obra_social, String nombre) {
			// TODO Auto-generated method stub		
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update obra_social set nombre=? where id_obra_social=?");
				stmt.setString(1, nombre);
				stmt.setInt(2, id_obra_social);	
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
		public String revivirConsultorio(Integer id_obra_social)
		{
			String respuestaOperacion;
			try
			{
				stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update obra_social set estado=1, fecha_baja=null where id_obra_social=?");
				stmt.setInt(1, id_obra_social);
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
	public String eliminarObraSocial(Integer id_obra_social) {
		// TODO Auto-generated method stub
		String respuestaOperacion;
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update obra_social set estado=0, fecha_baja=current_timestamp() where id_obra_social=?");
			stmt.setInt(1, id_obra_social);	
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
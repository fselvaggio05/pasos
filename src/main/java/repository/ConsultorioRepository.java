package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
					consultorio.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
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
					consultorio.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
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
					consultorio.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
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
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update consultorio set estado=0, fecha_baja=current_timestamp() where id_consultorio=?");
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
			        FactoryConnection.getInstancia().releaseConn(); //es correcta esta forma de cerrar la conexion?
	    }			
		return respuestaOperacion;
	}

	
	public List<Consultorio> getConsultoriosAsignados(LocalDate fecha, LocalTime hora_desde, LocalTime hora_hasta) {
		
			
		
		List<Consultorio> consDisponible = new ArrayList<Consultorio>();
		
		Time desde = null;
		Time hasta = null;
		desde = Time.valueOf(hora_desde);
		hasta = Time.valueOf(hora_hasta);
		Date fecha_turno = Date.valueOf(fecha);
		
		
		
		try {
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select id_consultorio from turno where fecha_turno=? and ((hora_turno>=? and hora_hasta<=?) or (hora_turno>? and hora_hasta<=?) or (hora_turno>=? and hora_hasta<?))");			
			stmt.setDate(1, fecha_turno);
			stmt.setTime(2, desde);
			stmt.setTime(3, hasta);
			stmt.setTime(4, desde);
			stmt.setTime(5, hasta);
			stmt.setTime(6, desde);
			stmt.setTime(7, hasta);
			rs = stmt.executeQuery();
			
			
			while (rs!=null && rs.next())
			{
				Consultorio cons = new Consultorio();
				cons.setId_consultorio(rs.getInt("id_consultorio"));				
				consDisponible.add(cons);						
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
		return consDisponible;
	}
}
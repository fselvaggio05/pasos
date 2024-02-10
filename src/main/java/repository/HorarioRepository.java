package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import conexionDB.FactoryConnection;
import entity.Horario;
import entity.Profesional;

public class HorarioRepository {
	
	ResultSet rs = null;
	PreparedStatement stmt= null;
	String respuesta = null;

	public List<Horario> getAllActivos() {
		
		List<Horario> horarios = new ArrayList<Horario>();		
		try
		{		
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on p.dni=u.dni inner join practica pr on pr.id_practica=h.id_practica where h.fecha_baja is null order by u.apellido asc, h.dia_semana" );
			rs= stmt.executeQuery();			
			while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setDesc_practica(rs.getString("descripcion"));
				hr.setId_practica(rs.getInt("id_practica"));
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				hr.setMatricula(rs.getInt("matricula"));
				hr.setApellido_profesional(rs.getString("apellido"));	
				horarios.add(hr);
			
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}		
		finally
		{
			
			 try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		        	} 
		        catch (Exception e) {
		            e.printStackTrace();
		        }			
		}		
		
		return horarios;
	}
	
	
	public List<Horario> getAllInactivos() {
		
		List<Horario> horarios = new ArrayList<Horario>();		
		try
		{		
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on p.dni=u.dni inner join practica pr on pr.id_practica=h.id_practica where h.fecha_baja is null order by u.apellido asc, h.dia_semana" );
			rs= stmt.executeQuery();			
			while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setDesc_practica(rs.getString("pr.descripcion"));
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				hr.setMatricula(rs.getInt("matricula"));
				hr.setApellido_profesional(rs.getString("apellido"));	
				hr.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
				horarios.add(hr);
			
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}		
		finally
		{
			
			 try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		        	} 
		        catch (Exception e) {
		            e.printStackTrace();
		        }			
		}		
		
		return horarios;
		
		
	}
	
	
	public List<Horario> getHorariosActivosProfesional(Integer matricula) {
		
		List<Horario> horariosProf = new ArrayList<Horario>();
		
		try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on u.dni=p.dni inner join practica pr on h.id_practica=pr.id_practica where h.fecha_baja is null and h.matricula=?");
            stmt.setInt(1, matricula);
            rs=stmt.executeQuery();
            while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setId_practica(rs.getInt("pr.id_practica"));
				hr.setDesc_practica(rs.getString("pr.descripcion"));
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				hr.setMatricula(rs.getInt("matricula"));
				hr.setApellido_profesional(rs.getString("apellido"));	
				horariosProf.add(hr);
			
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
		
		
		return horariosProf;
		
		
	}


	public List<Horario> getHorariosInactivosProfesional(Integer matricula) {
			
		List<Horario> horariosProf = new ArrayList<Horario>();
		
		try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on u.dni=p.dni inner join practica pr on pr.id_practica=h.id_practica where h.fecha_baja is not null and h.matricula=?");
            stmt.setInt(1, matricula);
            rs=stmt.executeQuery();
            while(rs.next() && rs != null)
			{

				Horario hr = new Horario();
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setDesc_practica(rs.getString("pr.descripcion"));
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				hr.setMatricula(rs.getInt("matricula"));
				hr.setApellido_profesional(rs.getString("apellido"));	
				hr.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
				horariosProf.add(hr);
			
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
		
		
		return horariosProf;
		
		
		
		
	}


	public String insertarHorario(Horario hr) {
		
	
		Date fecha_alta;	
		Time desde = null;
		Time hasta = null ;
		desde = Time.valueOf(hr.getHora_desde());
		hasta = Time.valueOf(hr.getHora_hasta());
	
	
		
		
		try 
		{
			Calendar calendar = new GregorianCalendar();
			fecha_alta = new Date(calendar.getTimeInMillis());
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into horario (fecha_alta, matricula, dia_semana, hora_desde, hora_hasta,id_practica) values (?,?,?,?,?,?)");
			stmt.setDate(1, new java.sql.Date (fecha_alta.getTime()));
			stmt.setInt(2, hr.getMatricula());
			stmt.setString(3, hr.getDia_semana());
			stmt.setTime(4, desde);
			stmt.setTime(5, hasta);
			stmt.setInt(6, hr.getId_practica());
			stmt.executeUpdate();
			respuesta = "OK";
			
			
		}
		
		catch(SQLException e)
		{
			respuesta = e.toString();
			
		}
		
		finally
		{
			
			 try {
	              
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();


	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            FactoryConnection.getInstancia().releaseConn();
			
			
			
		}		
		
		return respuesta;
	}

	
	
	

	public String activarHorario(Integer idHorario) {
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update horario h set fecha_baja = null where idHorario=?");
			stmt.setInt(1, idHorario);
			stmt.executeUpdate();
			respuesta = "OK";
			
			
		}
		
		catch (SQLException e)
		{
			respuesta = e.toString();
		}
		
		finally
		{
			 try {
	              
	                if (rs != null) rs.close();
	                if (stmt != null) stmt.close();


	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	            FactoryConnection.getInstancia().releaseConn();
			
			
			
		}
		
		return respuesta;
		
	}


	
	
	public String inactivarHorario(Integer idHorario) {
		
		try 
		{
			
			Date fecha_baja;
			Calendar calendar = new GregorianCalendar();
			fecha_baja = new Date(calendar.getTimeInMillis());
				
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update horario h set fecha_baja=? where idHorario=?");
			stmt.setDate(1, new java.sql.Date(fecha_baja.getTime()));
			stmt.setInt(2, idHorario);	
			stmt.executeUpdate();
			respuesta= "OK";
		}
		
		
		catch (SQLException e) {
			
			respuesta = e.toString();
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
		
		
		
		return respuesta;
	}


	public String actualizarHorario(Horario hr) {
		
		Time desde = null;
		Time hasta = null;
		desde = Time.valueOf(hr.getHora_desde());
		hasta = Time.valueOf(hr.getHora_hasta());
		
		

		try
		{
						
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update horario set dia_semana=?, id_practica=?, hora_desde=?, hora_hasta=? where idHorario=?");
		
			stmt.setString(1, hr.getDia_semana());
			stmt.setInt(2, hr.getId_practica());	
			stmt.setTime(3, desde);
			stmt.setTime(4, hasta);
			stmt.setInt(5, hr.getId_horario());
			stmt.executeUpdate();
			respuesta= "OK";
						
		}
		
		catch (SQLException e) {
			
			respuesta = e.toString();
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

		
		
		return respuesta;
	}



	

	
	

}

package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import conexionDB.FactoryConnection;
import entity.Horario;
import entity.Practica;
import entity.Profesional;

public class HorarioRepository {
	ResultSet rs = null;
	PreparedStatement stmt= null;
	String respuesta = null;

	public List<Horario> getAllActivos() {
		
		List<Horario> horarios = new ArrayList<Horario>();		
		try
		{		
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on p.dni=u.dni inner join practica pr on pr.id_practica=h.id_practica where h.fecha_baja is null order by h.dia_semana asc, h.hora_desde asc, u.apellido asc, pr.descripcion" );
			rs= stmt.executeQuery();			
			while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				Practica pr = new Practica();
				Profesional prof = new Profesional();
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				hr.setDia_semana(rs.getInt("dia_semana"));				
				pr.setId_practica(rs.getInt("id_practica"));
				pr.setDescripcion(rs.getString("descripcion"));
				hr.setPractica(pr);			
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());				
				prof.setMatricula(rs.getInt("matricula"));
				prof.setApellido(rs.getString("apellido"));
				hr.setProfesional(prof);				
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
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on p.dni=u.dni inner join practica pr on pr.id_practica=h.id_practica where h.fecha_baja is not null order h.dia_semana asc, h.hora_desde asc, u.apellido asc, pr.descripcion");
			rs= stmt.executeQuery();			
			while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				Practica pr = new Practica();
				Profesional prof = new Profesional();
				
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setDia_semana(rs.getInt("dia_semana"));
				
				pr.setId_practica(rs.getInt("pr.id_practica"));
				pr.setDescripcion(rs.getString("pr.descripcion"));
				
				hr.setPractica(pr);
				
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				
				prof.setMatricula(rs.getInt("matricula"));
				prof.setApellido(rs.getString("apellido"));
				
				hr.setProfesional(prof);
			
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
				Practica pr = new Practica();
				Profesional prof = new Profesional();
				
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				hr.setDia_semana(rs.getInt("dia_semana"));
				
				pr.setId_practica(rs.getInt("pr.id_practica"));
				pr.setDescripcion(rs.getString("pr.descripcion"));
				
				hr.setPractica(pr);
				
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				
				prof.setMatricula(rs.getInt("matricula"));
				prof.setApellido(rs.getString("apellido"));
				
				hr.setProfesional(prof);
				
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
				Practica pr = new Practica();
				Profesional prof = new Profesional();
				
				hr.setId_horario(rs.getInt("idHorario"));
				hr.setDia_semana(rs.getInt("dia_semana"));
				
				pr.setId_practica(rs.getInt("pr.id_practica"));
				pr.setDescripcion(rs.getString("pr.descripcion"));
				
				hr.setPractica(pr);
				
				hr.setHora_desde(rs.getTime("hora_desde").toLocalTime());
				hr.setHora_hasta(rs.getTime("hora_hasta").toLocalTime());
				hr.setFecha_baja(rs.getDate("fecha_baja").toLocalDate());
				
				prof.setMatricula(rs.getInt("matricula"));
				prof.setApellido(rs.getString("apellido"));
				
				hr.setProfesional(prof);	
			
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
		Time desde = null;
		Time hasta = null ;
		desde = Time.valueOf(hr.getHora_desde());
		hasta = Time.valueOf(hr.getHora_hasta());		
		try 
		{
			//Eliminé la fecha de alta, en la base está como valor default current timestamp
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into horario (matricula, dia_semana, hora_desde, hora_hasta,id_practica) values (?,?,?,?,?)");
			stmt.setInt(1, hr.getProfesional().getMatricula());
			stmt.setInt(2, hr.getDia_semana().getDia());
			stmt.setTime(3, desde);
			stmt.setTime(4, hasta);
			stmt.setInt(5, hr.getPractica().getId_practica());
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

	public String revivirHorario(Integer idHorario) {
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
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update horario h set fecha_baja=current_timestamp() where idHorario=?");
			stmt.setInt(1, idHorario);	
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



	public Integer obtenerHorariosCreados(Horario hr) {
		
		Time desde = null;
		Time hasta = null;
		desde = Time.valueOf(hr.getHora_desde());
		hasta = Time.valueOf(hr.getHora_hasta());
		Integer cantHorarios = null;	
		
		
		try
		{		
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select count(*) from horario where fecha_baja is null and dia_semana=? and ((hora_desde>? and hora_hasta<?) or (hora_desde>? and hora_desde<?) or (hora_hasta>? and hora_hasta<?))" );
			stmt.setInt(1, hr.getDia_semana().getDia());
			stmt.setTime(2, desde);
			stmt.setTime(3, hasta);
			stmt.setTime(4, desde);
			stmt.setTime(5, hasta);
			stmt.setTime(6, desde);
			stmt.setTime(7, hasta);
			
			rs= stmt.executeQuery();	
			
			while(rs.next() && rs != null)
			{
				cantHorarios = Integer.parseInt(rs.getString(1));
			
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
		
		return cantHorarios;


	
	}
	
	
	
	
public List<Profesional> getProfesionales(Integer id_practica) {
		
		List<Profesional> profesionales = new ArrayList<Profesional>();
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select distinct us.apellido, us.nombre, prof.matricula from horario h inner join profesional prof on prof.matricula=h.matricula inner join usuario us on us.dni=prof.dni where id_practica=? and h.fecha_baja is null");
			stmt.setInt(1, id_practica);
			rs = stmt.executeQuery();
			
			while(rs!=null && rs.next())
			{
				Profesional pr = new Profesional();
				pr.setMatricula(rs.getInt("matricula"));
				pr.setApellido(rs.getString("apellido"));
				pr.setNombre(rs.getString("nombre"));
				profesionales.add(pr);
				
			}
			
		}
		
		catch (SQLException e)
		{
			respuesta = e.toString();
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		return profesionales;
	}
}
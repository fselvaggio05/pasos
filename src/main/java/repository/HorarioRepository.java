package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionDB.FactoryConnection;
import entity.Horario;
import entity.Profesional;

public class HorarioRepository {
	
	ResultSet rs = null;
	PreparedStatement stmt= null;

	public List<Horario> getAllActivos() {
		
		List<Horario> horarios = new ArrayList<Horario>();
		
		
		try
		{
		
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on p.dni=u.dni where fecha_baja is null order by u.apellido asc" );
			rs= stmt.executeQuery();
			
			while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				hr.setFecha_alta(rs.getDate("fecha_alta"));
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setHora_desde(rs.getTime("hora_desde"));
				hr.setHora_hasta(rs.getTime("hora_hasta"));
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
	
	
	
	public List<Horario> getHorariosProfesional(Integer matricula) {
		
		List<Horario> horariosProf = new ArrayList<Horario>();
		
		try{
            stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from horario h inner join profesional p on h.matricula=p.matricula inner join usuario u on u.dni=p.dni where fecha_baja is null and h.matricula=?");
            stmt.setInt(1, matricula);
            rs=stmt.executeQuery();
            while(rs.next() && rs != null)
			{
				Horario hr = new Horario();
				hr.setFecha_alta(rs.getDate("fecha_alta"));
				hr.setDia_semana(rs.getString("dia_semana"));
				hr.setHora_desde(rs.getTime("hora_desde"));
				hr.setHora_hasta(rs.getTime("hora_hasta"));
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

	
	

}

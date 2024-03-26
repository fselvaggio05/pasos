package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionDB.FactoryConnection;
import entity.Paciente;
import entity.Practica;
import entity.Prescripcion;

public class PrescripcionRepository {
	
	 ResultSet rs = null;
	 PreparedStatement stmt = null;
	 String respuestaOperacion = null;
	 
	 
	
	public Integer insertarPrescripcion(Prescripcion pr)
	{
		
		Integer id_prescripcion=null;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into prescripcion (fecha_prescripcion,nro_afiliado,cod_practica,cant_sesiones,sesiones_asistidas,fecha_alta_presc,estado) values (?,?,?,?,?,?,?)", stmt.RETURN_GENERATED_KEYS);
			stmt.setDate(1, Date.valueOf(pr.getFecha_prescripcion()));
			stmt.setString(2, pr.getNro_afiliado());
			stmt.setInt(3, pr.getCod_practica());
			stmt.setInt(4, pr.getCant_sesiones());
			stmt.setInt(5, pr.getSesiones_asistidas());
			stmt.setDate(6, Date.valueOf(pr.getFecha_alta_prescripcion()));
			stmt.setInt(7,1);
			stmt.executeUpdate();
			
			rs = (ResultSet) stmt.getGeneratedKeys();
			if(rs.next())
			{
				id_prescripcion = rs.getInt(1);
			}						
			
		}
		
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();			 
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
		return id_prescripcion;	
	}



	public Prescripcion buscarPrescrionesPaciente(Paciente pac, Prescripcion pr) {
		
		
		Prescripcion presc = null;
		
		try
		{
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion pr inner join paciente pa on pa.nro_afiliado = pr.nro_afiliado inner join usuario us on us.dni=pa.dni where pr.fecha_prescripcion=? and pa.dni=? and pr.nro_afiliado=? and pr.cod_practica=? and estado=1");
			stmt.setDate(1, Date.valueOf(pr.getFecha_prescripcion()));
			stmt.setInt(2, pac.getDni()); //traigo paciente para buscar tmb por dni y nro de afiliado
			stmt.setString(3, pr.getNro_afiliado());
			stmt.setInt(4, pr.getCod_practica());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				presc = new Prescripcion();
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setCod_practica(rs.getInt("cod_practica"));
				presc.setFecha_alta_prescripcion(rs.getDate("fecha_alta_presc").toLocalDate());
				presc.setFecha_prescripcion(rs.getDate("fecha_prescripcion").toLocalDate());
				presc.setNro_afiliado(rs.getString("nro_afiliado"));
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				
			}
			
		}
		
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		
		
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		return presc;
	}



	//19/03
	public Prescripcion buscarPrescripcionActiva(Paciente pac, Practica pr) {
		
		Prescripcion presc =  null;
		
		try
		{
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion pr where pr.cod_practica=? and nro_afiliado=? and estado=1");
			
			stmt.setInt(1, pr.getId_practica()); 
			stmt.setString(2, pac.getNro_afiliado());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				presc = new Prescripcion();
				presc.setId_Prescripcion(rs.getInt("id_ambulatoria"));
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setCod_practica(rs.getInt("cod_practica"));
				presc.setFecha_alta_prescripcion(rs.getDate("fecha_alta_presc").toLocalDate());
				presc.setFecha_prescripcion(rs.getDate("fecha_prescripcion").toLocalDate());
				presc.setNro_afiliado(rs.getString("nro_afiliado"));
				presc.setSesiones_asistidas(rs.getInt("sesiones_asistidas"));
				
			}
			
		}
		
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		
		
		finally {
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		return presc;	
		
	}

	
	public void incrementarSesionesAsistidas(Prescripcion prescripcion) {
		
		
		Integer sesionesAsistidas = prescripcion.getSesiones_asistidas()+1;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update prescripcion set sesiones_asistidas=? where id_ambulatoria=?");
			stmt.setInt(1, sesionesAsistidas);
			stmt.setInt(2,prescripcion.getId_Prescripcion());			
			stmt.executeUpdate();
			
		}
		
		catch(SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
		
	}



	public void desactivarVigenciaPrescripcion(Prescripcion prescripcion) {
		
		Integer sesionesAsistidas = prescripcion.getSesiones_asistidas()+1;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("update prescripcion set sesiones_asistidas=?, estado=0 where id_ambulatoria=?");
			stmt.setInt(1, sesionesAsistidas);
			stmt.setInt(2,prescripcion.getId_Prescripcion());			
			stmt.executeUpdate();
			
		}
		
		catch(SQLException e)
		{
			respuestaOperacion = e.toString();
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
	}
}

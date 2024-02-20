package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionDB.FactoryConnection;
import entity.Paciente;
import entity.Prescripcion;

public class PrescripcionRepository {
	
	 ResultSet rs = null;
	 PreparedStatement stmt = null;
	 String respuestaOperacion = null;
	 
	 
	
	public String insertarPrescripcion(Prescripcion pr)
	{
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("insert into prescripcion (fecha_prescripcion,nro_afiliado,cod_practica,cant_sesiones,sesiones_asistidas,fecha_alta_presc,estado) values (?,?,?,?,?,?,?)");
			stmt.setDate(1, Date.valueOf(pr.getFecha_prescripcion()));
			stmt.setInt(2, pr.getNro_afiliado());
			stmt.setInt(3, pr.getCod_practica());
			stmt.setInt(4, pr.getCant_sesiones());
			stmt.setInt(5, pr.getSesiones_asistidas());
			stmt.setDate(6, Date.valueOf(pr.getFecha_alta_prescripcion()));
			stmt.setInt(7,0);
			stmt.executeUpdate();
			respuestaOperacion = "OK";			
			
		}
		
		catch (SQLException e)
		{
			respuestaOperacion = e.toString();
			 
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
		return respuestaOperacion;
	}



	public Prescripcion buscarPrescrionesPaciente(Paciente pac, Prescripcion pr) {
		
		
		Prescripcion presc = null;
		
		try
		{
			
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select * from prescripcion pr inner join paciente pa on pa.nro_afiliado = pr.nro_afiliado inner join usuario us on us.dni=pa.dni where pr.fecha_prescripcion=? and pa.dni=? and pr.nro_afiliado=? and pr.cod_practica=? and estado=1");
			stmt.setDate(1, Date.valueOf(pr.getFecha_prescripcion()));
			stmt.setInt(2, pac.getDni()); //traigo paciente para buscar tmb por dni y nro de afiliado
			stmt.setInt(3, pr.getNro_afiliado());
			stmt.setInt(4, pr.getCod_practica());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				presc = new Prescripcion();
				presc.setCant_sesiones(rs.getInt("cant_sesiones"));
				presc.setCod_practica(rs.getInt("cod_practica"));
				presc.setFecha_alta_prescripcion(rs.getDate("fecha_alta_presc").toLocalDate());
				presc.setFecha_prescripcion(rs.getDate("fecha_prescripcion").toLocalDate());
				presc.setNro_afiliado(rs.getInt("nro_afiliado"));
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

}

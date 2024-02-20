package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionDB.FactoryConnection;
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

}

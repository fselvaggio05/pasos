package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import conexionDB.FactoryConnection;
import entity.Horario;
import entity.Turno;

public class TurnoRepository {
	
	PreparedStatement stmt;
	ResultSet rs;

	public void abrirAgenda(List<Turno> turnos) {
		
		
		
		
	}

	public LocalDate getUltimaFechaGeneracion() {
		
		LocalDate ult_fecha = null;
		
		try
		{
			stmt = FactoryConnection.getInstancia().getConn().prepareStatement("select max(fecha_generacion) from turno ");
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				ult_fecha = rs.getDate("max(fecha_generacion)").toLocalDate();				
			}
			
			
			else 
			{
				return null;
			}
		}
		
		catch (SQLException e)
		{
			e.toString();
		}
		
		finally
		{
			FactoryConnection.cerrarConexion(rs, stmt);
		}
		
		
		return ult_fecha;
		
	}

	
	
}
